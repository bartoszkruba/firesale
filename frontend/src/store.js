import Vue from 'vue'
import Vuex from 'vuex'
import AuctionService from '@/services/auctionsService'
import auth from '@/services/authentication'
import CategoryService from '@/services/categoryService';
import bidService from '@/services/bid'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        loggedIn: false,
        showFilters: false,
        auctions: [],
        categories: [],
        filterParams: {
            searchText: null,
            selectedCategory: 'All',
            maxPrice: 0,
        },
        currentViewedAuction: null,
        viewedAuctionBids: [
            // {
            //     "id": 4,
            //     "value": 400,
            //     "creationTime": "2019-04-26T11:05:57",
            //     "auctionId": 4,
            //     "userId": 4,
            //     "username": "Morenorator"
            // },
            // {
            //     "id": 3,
            //     "value": 300,
            //     "creationTime": "2019-04-26T11:05:57",
            //     "auctionId": 4,
            //     "userId": 5,
            //     "username": "Cindirella"
            // },
            // {
            //     "id": 2,
            //     "value": 250,
            //     "creationTime": "2019-04-26T11:05:57",
            //     "auctionId": 4,
            //     "userId": 3,
            //     "username": "ChrisL"
            // }
        ]
    },
    mutations: {
        flipShowFilters(state) {
            this.state.showFilters = !this.state.showFilters;
        },
        setLoggedIn(state, value) {
            this.state.loggedIn = value;
        },
        setCategories(state, value) {
            for (let category of value) {
                this.state.categories.push(category.name);
            }
        },
        setFilterParams(state, params) {
            state.filterParams = params;
        },
        toggleShowFiltersOnHome(state) {
            state.showFiltersOnHome = !state.showFiltersOnHome;
        },
        setAuctions(state, params) {
            state.auctions = params;
        },
        setCurrentViewedAuction(state, params) {
            state.currentViewedAuction = params;
        },
        setViewedAuctionBids(state, value) {
            this.state.viewedAuctionBids = value;
        }
    },
    actions: {
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
            let response = await auth.checkIfLoggedIn();
            this.commit("setLoggedIn", response)
        },
        async getCategories(context) {
            await CategoryService().getCategories()
                .then(response => {
                    context.commit('setCategories', response.data)
                });
        },
        async getCurrentViewedAuction(context, id) {
            let response = await AuctionService().getAuctionById(id);
            this.commit('setCurrentViewedAuction', response.data);
            this.commit("setViewedAuctionBids", []);
            this.dispatch("loadBidPage");
        },
        async loadBidPage() {
            if (this.state.currentViewedAuction != null) {

                let incompletePageBids = this.state.viewedAuctionBids.length % 5;
                let bids = this.state.viewedAuctionBids;
                for (let i = 0; i < incompletePageBids; i++) {
                    bids.pop();
                }

                let pageToLoad = (this.state.viewedAuctionBids.length / 5) | 0;

                let response = await bidService().loadBids(this.state.currentViewedAuction.id, pageToLoad);
                let page = response.data;
                page.forEach(p => this.state.viewedAuctionBids.push(p));

                // this.commit("setViewedAuctionBids", bids);
            }
        }
    }
});
