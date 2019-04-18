import Vue from 'vue'
import Vuex from 'vuex'
import AuctionService from '@/services/auctionsService'
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
            maxPrice: null,
        }
    },
    mutations: {
        setLoggedIn(state, value) {
            this.state.loggedIn = value;
        },
        setCategories(state, value){
            for(let category of value) {
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
        async setAuctions(context, params) {
            await AuctionService().getFilteredAuctions(params)
                .then(response => context.commit('setAuctions', response.data));
        },
        async getCategories(context, params){
            await CategoryService().getCategories(params)
                .then(response => {
                    context.commit('setCategories', response.data)
                });
        }
    }
})
