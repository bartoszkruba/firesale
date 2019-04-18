import Vue from 'vue'
import Vuex from 'vuex'
import AuctionService from '@/services/auctionsService'


Vue.use(Vuex);


export default new Vuex.Store({
    state: {
        auctions: [],
        filterParams: {
            searchText: null,
            selectedCategory: 'All',
            maxPrice: null,
            showAllAuctions: false,
            showFilters: false
        }
    },
    mutations: {
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
            console.log(this.state.auctions);
        },
    }
})
