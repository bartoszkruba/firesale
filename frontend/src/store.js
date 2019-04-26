import Vue from 'vue'
import Vuex from 'vuex'
import AuctionService from '@/services/auctionsService'
import auth from '@/services/authentication'
import CategoryService from '@/services/categoryService';

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
        urlQuery: {},
        numberOfAuctionsOnHome: 5,
        currentViewedAuction: null,
        page: 0
    },
    mutations: {
        setUrlQuery(state, value) {
            this.state.urlQuery = value;
        },
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
        loadMoreAuctionsOnScroll(state, params) {
            state.auctions = state.auctions.concat(params);
        },
        setCurrentViewedAuction(state, params) {
            state.currentViewedAuction = params;
        },
        setPageNumber(state, value){
            state.page = value;
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
        async getMoreAuctionsOnScroll(context, params) {
            let numberOfTotalAuctions = await AuctionService().countAuctionsBasedOnTitle(params).then(response => response.data);
            if (numberOfTotalAuctions > this.state.auctions.length) {
                params.page = this.state.page;
                await AuctionService().getFilteredAuctions(params)
                    .then(response => {
                        context.commit('loadMoreAuctionsOnScroll', response.data);
                    });
                this.state.page++;
            }
        },
        async getCurrentViewedAuction(context, id) {
            let response = await AuctionService().getAuctionById(id);
            this.commit('setCurrentViewedAuction', response.data)
        }
    }
});
