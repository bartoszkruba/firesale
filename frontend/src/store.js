import Vue from 'vue'
import Vuex from 'vuex'
import AuctionService from '@/services/auctionsService'
import auth from '@/services/authentication'
import CategoryService from '@/services/categoryService';
import socketService from '@/services/socket';
import bidService from '@/services/bid';
import conversationService from "./services/conversationService";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        subscribedAuctions: [],
        loggedIn: false,
        showFilters: false,
        showExtendedFilters: false,
        auctions: [],
        categories: [],
        filterParams: {
            searchText: null,
            selectedCategory: 'All',
        },
        listItemBidFieldSwitch: null,
        urlQuery: {},
        numberOfAuctionsOnHome: 5,
        currentViewedAuction: null,
        page: 0,
        viewedAuctionBids: [
        ],
        currentUser: null,
        conversations: [],
        currentConversationId: null,
        messages: [],
        showNotification: false,
        currentNotification: null,
        notifications: [
        ]

    },
    mutations: {
        setCurrentConversationId(state, value) {
            this.state.currentConversationId = value;
        },
        addNewMessage(state, value) {
            this.state.messages.push(value);
        },
        setMessages(state, value) {
            this.state.messages = value;
        },
        addConversation(state, value) {
            if (!this.state.conversations.includes(con => con.id === value.id)) {
                this.state.conversations.push(value);
            }
        },
        setConversations(state, value) {
            this.state.conversations = value;
        },
        setUrlQuery(state, value) {
            this.state.urlQuery = value;
        },
        flipShowFilters() {
            this.state.showFilters = !this.state.showFilters;
        },
        setLoggedIn(state, value) {
            this.state.loggedIn = value;
        },
        setCategories(state, value) {
            for (let category of value) {
                this.state.categories.push(category.name);
            }
            state.categories.sort();
        },
        setFilterParams(state, params) {
            state.filterParams = params;
        },
        toggleExtendedFilters(state) {
            state.showExtendedFilters = !state.showExtendedFilters;
        },
        setAuctions(state, params) {
            state.auctions = params;
        },
        loadMoreAuctionsOnScroll(state, params) {
            state.auctions = state.auctions.concat(params);

            let messageHandler = payload => {
                let bid = JSON.parse(payload.body);
                let auctionId = bid.auctionId;
                let auctions = this.state.auctions;
                auctions.filter(a => a.id === auctionId).forEach(a => a.currentPrice = bid.value);
                this.commit("setAuctions", auctions);
            };

            socketService().unsubscribeAllAuctionBids();
            this.state.auctions.forEach(a => {
                socketService().subscribeToAuctionBids(a.id, messageHandler);
                state.subscribedAuctions.push(a.id);
            });

        },
        setCurrentViewedAuction(state, params) {
            state.currentViewedAuction = params;
        },
        setPageNumber(state, value) {
            state.page = value;
        },
        setViewedAuctionBids(state, value) {
            this.state.viewedAuctionBids = value;
        },
        setListItemBidFieldSwtich(state, value) {
            this.state.listItemBidFieldSwitch = value;
        },
        setCurrentUser(state, params) {
            state.currentUser = params;
        },
        setCurrentNotification(state, value) {
            this.state.currentNotification = value;
        },
        setNotification(state, value) {
            this.state.showNotification = value;
        }
    },
    actions: {
        async getConversations(context) {

            await conversationService.getConversations().then(response => {
                context.commit('setConversations', response.data);
            })
        },
        showFilters(context) {
            this.commit('showFilters')
        },
        async getAuctions(context, params) {
            await AuctionService().getFilteredAuctions(params)
                .then(response => {
                    context.commit('setAuctions', response.data);
                });
        },
        async checkIfLoggedIn() {
            this.state.conversations = [];
            let response = await auth.checkIfLoggedIn();
            if (response) {
                this.commit("setLoggedIn", response);
                this.dispatch("getCurrentUser");
                this.dispatch('getConversations');
                this.dispatch("subscribeToNotifications");
                this.dispatch("subscribeChat");
            }
        },
        async getCategories(context) {
            await CategoryService().getCategories()
                .then(response => {
                    context.commit('setCategories', response.data)
                });
        },
        async getMoreAuctionsOnScroll(context, params) {
            params.page = this.state.page;
            await AuctionService().getFilteredAuctions(params)
                .then(response => {
                    if (response.data.currentPage < response.data.totalPages) {
                        context.commit('loadMoreAuctionsOnScroll', response.data.list);
                        context.commit('setPageNumber', response.data.currentPage + 1);
                    }
                }).catch(error => {
                    //Error caused multiple auctions loading on homepage
                    this.commit('setAuctions', []);
                    this.commit('setPageNumber', 0);
                    console.log(error)
                })

        },
        async getCurrentViewedAuction(context, id) {
            socketService().unsubscribeAllAuctionBids();

            socketService().subscribeToAuctionBids(id, payload => {

                let bid = JSON.parse(payload.body);

                let viewedBids = this.state.viewedAuctionBids;

                if (!viewedBids) {
                    viewedBids = []
                }
                viewedBids.push(bid);

                let currentViewedAuction = this.state.currentViewedAuction;

                currentViewedAuction.highestBid = bid;
                currentViewedAuction.currentPrice = bid.value;

                this.commit("setCurrentViewedAuction", currentViewedAuction);
                this.commit("setViewedAuctionBids", viewedBids);

            });
            let response = await AuctionService().getAuctionById(id);

            this.commit('setCurrentViewedAuction', response.data);
            this.dispatch("loadBidPage");
        },
        async getCurrentUser() {
            let response = await auth.getCurrentUser();
            this.commit('setCurrentUser', response);
        },
        async loadBidPage() {
            if (this.state.currentViewedAuction != null) {

                let incompletePageBids = this.state.viewedAuctionBids.length % 5;
                let bids = this.state.viewedAuctionBids;
                for (let i = 0; i < incompletePageBids; i++) {
                    bids.shift();
                }

                let pageToLoad = (this.state.viewedAuctionBids.length / 5) | 0;

                let response = await bidService().loadBids(this.state.currentViewedAuction.id, pageToLoad);
                let page = response.data;
                page.forEach(p => this.state.viewedAuctionBids.unshift(p));
            }
        },
        closeNotification() {
            this.commit("setNotification", false);
            if (this.state.notifications.length > 0) {
                let notification = this.state.notifications.shift();
                setTimeout(() => {
                    this.commit("setCurrentNotification", notification);
                    this.commit("setNotification", true);
                }, 500)
            } else {
                this.commit("setCurrentNotification", null);
            }
        },
        subscribeToNotifications() {
            socketService().subscribeNotifications((payload) => {
                let notification = JSON.parse(payload.body);
                notification.type = "bid";
                let currentNotification = this.state.currentNotification;
                if (!currentNotification) {
                    this.commit("setCurrentNotification", notification);
                    this.commit("setNotification", true)
                } else {
                    this.state.notifications.unshift(currentNotification);
                    this.state.notifications.unshift(notification);
                    this.dispatch("closeNotification");
                }
            })
        },
        subscribeChat() {
            socketService().subscribeChat((payload) => {
                let message = JSON.parse(payload.body);

                if (this.state.currentConversationId === message.conversationId) {
                    this.state.messages.push(message);
                }

                if (this.state.conversations.filter(a => a.id === message.conversationId).length === 0) {
                    conversationService.newConversation(message.username).then(response => this.state.conversations.push(response.data));
                }

                if (message.username === this.state.currentUser.username) return;

                message.type = "message";

                let currentNotification = this.state.currentNotification;

                if (!currentNotification) {
                    this.commit("setCurrentNotification", message);
                    this.commit("setNotification", true)
                } else {
                    this.state.notifications.unshift(currentNotification);
                    this.state.notifications.unshift(message);
                    this.dispatch("closeNotification");
                }

            })
        }
    }
});
