import Vue from 'vue'
import Vuex from 'vuex'
import AuctionService from '@/services/auctionsService'
import auth from '@/services/authentication'
import CategoryService from '@/services/categoryService';
import authentication from "./services/authentication";

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
        currentUser: { }
    },
    mutations: {
        flipShowFilters(state){
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
            console.log(this.state.auctions);
        },
        setCurrentViewedAuction(state, params) {
            state.currentViewedAuction = params;
        },
        setCurrentUser(state, params) {
            state.currentUser = params;
        }

    },
    actions: {
        showFilters(context){
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
            this.commit('setCurrentViewedAuction', response.data)
        },
        async getCurrentUser() {
            let response = await auth().getCurrentUser();
            console.log(response.data);
            this.commit('setCurrentUser', response.data)
        }

    }
});
