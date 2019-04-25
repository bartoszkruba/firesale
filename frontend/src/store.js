import Vue from 'vue'
import Vuex from 'vuex'
import AuctionService from '@/services/auctionsService'
import auth from '@/services/authentication'
import CategoryService from '@/services/categoryService';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        loggedIn: false,
        showFiltersOnHome: false,
        auctions: [],
        categories: [],
        filterParams: {
            searchText: null,
            selectedCategory: 'All',
            maxPrice: 0,
        },
        currentViewedAuction: {
            "id": 1,
            "title": "A RED CAR",
            "description": "BRUM BRUM",
            "openedAt": "2019-04-25T09:45:29",
            "closingTime": "2019-04-18T10:30:00",
            "startUpPrice": 10000,
            "buyOutPrice": 80000,
            "category": "Cars",
            "user": null,
            "images": [
                {
                filePath: 'https://i.imgur.com/e0u37lt.jpg'
            }]
        },
    },
    mutations: {
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
            console.log(this.state.auctions);
        },
        setCurrentViewedAuction(state, params) {
            state.currentViewedAuction = params;
        }
    },
    actions: {
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
            this.commit('setCurrentViewedAuction', response.data)
        }
    }
});
