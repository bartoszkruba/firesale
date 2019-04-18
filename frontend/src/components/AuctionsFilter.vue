<template>
    <v-container id="filter-container" xs12 sm6 pa-0>
        <v-form v-model="valid">
            <v-layout column justify-center>
                <v-container id="search-container" xs12 pt-0>
                    <v-container pa-0>
                        <v-flex>
                            <v-text-field
                                    v-model="filterParams.searchText"
                                    :rules="searchTextRules"
                                    label="Search"
                            ></v-text-field>
                        </v-flex>
                    </v-container>
                    <transition name="fade">
                        <v-container id="filters-container" xs12 pa-0
                                     v-show="this.$store.state.showFiltersOnHome">
                            <v-layout row wrap>
                                <v-container id="filter-slider" xs12 pa-0>
                                    <v-flex xs8 sm10 md11>
                                        <v-slider
                                                v-model="filterParams.maxPrice"
                                                :max="10000"
                                                :step="100"

                                        ></v-slider>
                                    </v-flex>
                                    <v-flex xs4 sm2 md1 pl-2 pr-2>
                                        <v-text-field
                                                v-model="filterParams.maxPrice"
                                                class="mt-0"
                                                hide-details
                                                single-line
                                                type="number"
                                                placeholder="Price"
                                        ></v-text-field>
                                    </v-flex>
                                </v-container>
                                <v-container id="filter-categories" xs12 md5 pa-0>
                                    <v-layout wrap justify-space-between>
                                        <v-flex xs12 sm6>
                                            <v-select
                                                    v-model="filterParams.selectedCategory"
                                                    :items="items"
                                                    label="Category"

                                            ></v-select>
                                        </v-flex>
                                        <v-flex xs12 sm4>
                                            <v-switch
                                                    v-model="filterParams.showAllAuctions"
                                                    :label="`${filterParams.showAllAuctions ? 'Hide ended auctions' : 'Show all auctions'}`"
                                                    color="primary"
                                            ></v-switch>
                                        </v-flex>
                                    </v-layout>
                                </v-container>
                            </v-layout>
                        </v-container>
                    </transition>
                    <v-container pa-0 id="container-buttons">
                        <v-flex>
                            <v-layout justify-center>
                                <v-btn @click="toggleFilters" color="primary">
                                    <v-icon>filter_list</v-icon>
                                </v-btn>
                                <v-btn @click="clickSearch" color="primary">
                                    <v-icon>search</v-icon>
                                </v-btn>
                            </v-layout>
                        </v-flex>
                    </v-container>
                </v-container>
            </v-layout>
        </v-form>
    </v-container>
</template>

<script>
    export default {
        name: "AuctionsFilter",
        data() {
            return {
                valid: false,
                filterParams: this.$store.state.filterParams,
                searchTextRules: [
                    v => v != null && v.length >= 3 || 'Enter at least 3 characters'
                ],
                items: ['All', 'Electronics', 'Cars'], //Fetch from database
                showFilters: this.$store.state.showFiltersOnHome,
            };
        },
        methods: {
            clickSearch() {
                this.$store.commit('setFilterParams', this.filterParams);
                this.$store.dispatch('setAuctions', this.generateFilterUrl());
                this.$router.push(this.generateFilterUrl());
            },
            toggleFilters() {
                this.$store.commit('toggleShowFiltersOnHome');
            },
            generateFilterUrl() {
                let url = ['auctions?'];
                let urlParams = [];
                if (this.filterParams.searchText != null) {
                    urlParams.push('title=' + this.filterParams.searchText);
                }
                if (this.filterParams.maxPrice > 0) {
                    urlParams.push('price=' + this.filterParams.maxPrice);
                }
                if (this.filterParams.selectedCategory !== 'All') {
                    urlParams.push('category=' + this.filterParams.selectedCategory);
                }
                if (this.filterParams.showAllAuctions === true) {
                    urlParams.push('showAll=' + this.filterParams.showAllAuctions);
                }
                urlParams = urlParams.join('&');
                return url + urlParams;
            }
        }
    }
</script>

<style scoped>
    #filter-slider {
        display: flex;
        flex-direction: row;
    }

    .fade-enter-active {
        transition: height 1s;
    }

    .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */
    {
        opacity: 0;
    }
</style>