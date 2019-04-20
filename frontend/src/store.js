import Vue from 'vue'
import Vuex from 'vuex'
import AuctionService from '@/services/auctionsService'
import auth from '@/services/authentication'


Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        loggedIn: false,
        showFiltersOnHome: false,
        auctions: [],
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
        async checkIfLoggedIn(context, params) {
            let response = await auth.checkIfLoggedIn();
            this.commit("setLoggedIn", response)
        }
    }
})
