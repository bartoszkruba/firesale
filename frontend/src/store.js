import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
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
        }
    },
    actions: {}
})
