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
        }
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
        }
    },
    actions: {
        async getAuctions(context, params) {
            await AuctionService().getFilteredAuctions(params)
                .then(response => {
                    context.commit('setAuctions', response.data);
                });
        },
        async checkIfLoggedIn(context, params) {
            let response = await auth.checkIfLoggedIn();
            this.commit("setLoggedIn", response)
        },
        async getCategories(context, params) {
            await CategoryService().getCategories(params)
                .then(response => {
                    context.commit('setCategories', response.data)
                });
        }
    }
});
