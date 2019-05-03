<template>
    <v-container id="filter-container" xs12 sm6 pa-0 v-on:keyup.enter="clickSearch" >
        <v-form v-model="valid">
            <v-layout column justify-center>
                <v-container id="search-container" xs12 pt-0>
                    <v-container pa-0>
                        <v-flex>
                            <v-text-field
                                    v-model="filterParams.searchText"
                                    :rules="searchTextRules"
                                    :autofocus=true
                                    clearable
                                    label="Search"
                            ></v-text-field>
                        </v-flex>
                    </v-container>
                    <transition name="fade">
                        <v-container id="filters-container" xs12 pa-0
                                v-show="this.$store.state.showExtendedFilters">
                            <v-layout row wrap>
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
                                                    :label="`${filterParams.showAllAuctions ? 'Showing all auctions' : 'Hiding ended auctions'}`"

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
                    // v => v != null && v.length >= 3 || 'Enter at least 3 characters',
                ],
                items: this.$store.state.categories,
                showFilters: this.$store.state.showFiltersOnHome,
            };
        },
        methods: {
            clickSearch() {
                this.$store.commit('setAuctions', []);
                this.$store.commit('setPageNumber', 0);
                this.$store.commit('setFilterParams', this.filterParams);
                let generatedQuery = this.generateFilterQuery();
                this.$router.push({path: '/auctions', query: generatedQuery});
                generatedQuery.page = this.$store.state.page;
                this.$store.dispatch('getMoreAuctionsOnScroll', generatedQuery);
                this.$store.commit('flipShowFilters');
            },
            toggleFilters() {
                this.$store.commit('toggleExtendedFilters');
            },
            generateFilterQuery() {
                let urlQuery = {};
                if (this.filterParams.searchText != null) {
                    urlQuery.title = this.filterParams.searchText;
                }
                if (this.filterParams.selectedCategory !== 'All') {
                    urlQuery.category = this.filterParams.selectedCategory;
                }
                if (this.filterParams.showAllAuctions === true) {
                    urlQuery.showAll = this.filterParams.showAllAuctions;
                }
                // urlQuery.page = 0;
                return urlQuery;
            }
        },
        created() {
            this.$store.dispatch('getCategories');
        }
    }
</script>

<style scoped>
    #filter-container {
        font-weight: 700;
        color: white;
        background-color: rgba(255, 255, 255, 1);
        padding-top: 20px;
    }

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