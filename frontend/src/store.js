import Vue from 'vue'
import Vuex from 'vuex'
import AuctionService from '@/services/auctionsService'


Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        loggedIn: false,
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
        setFilterParams(state, params){
            state.filterParams = params;
        },
        setAuctions(state, params){
            state.auctions = params;
        }
    },
    actions: {
        async setAuctions(context, params) {
            await AuctionService().getFilteredAuctions(params)
                .then(response => context.commit('setAuctions', response.data));
        },
    }
})
