<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div id="mainmenu">
        <AuctionsFilter v-show="showFilters"></AuctionsFilter>
        <v-toolbar color="primary">

            <v-spacer></v-spacer>
            <router-link to="/">
            <v-btn icon @click="goToHome">
                <v-icon>home</v-icon>
            </v-btn>
            </router-link>
            <v-spacer></v-spacer>
            <v-btn icon @click="searchClick">
                <v-icon>search</v-icon>
            </v-btn>
            <v-spacer></v-spacer>
                <router-link to="/new_auction">
                    <v-btn icon>
                        <v-icon>add_circle</v-icon>
                    </v-btn>
                </router-link>
            <v-spacer></v-spacer>
            <v-menu :nudge-width="100">
                <template v-slot:activator="{ on }">
                    <v-toolbar-title v-on="on">
                        <router-link to="/" style="color: black; text-decoration: none">
                        <v-btn icon>
                            <v-icon>account_circle</v-icon>
                        </v-btn>
                        </router-link>
                    </v-toolbar-title>
                </template>
                <v-list>

                    <v-list-tile v-if="loggedIn">
                        <router-link to="/profile" style="color: black; text-decoration: none">
                            <v-list-tile-title>
                                Profile
                            </v-list-tile-title>
                        </router-link>
                    </v-list-tile>
                    <v-list-tile v-if="!loggedIn">
                        <router-link to="/login" style="color: black; text-decoration: none">
                            <v-list-tile-title>
                                Log In
                            </v-list-tile-title>
                        </router-link>
                    </v-list-tile>
                    <v-list-tile v-if="!loggedIn">
                        <router-link to="/register" style="color: black; text-decoration: none">
                            <v-list-tile-title>
                                Register
                            </v-list-tile-title>
                        </router-link>
                    </v-list-tile>
                    <v-list-tile v-if="loggedIn">
                        <router-link to="/new_auction" style="color: black; text-decoration: none">
                            <v-list-tile-title>
                                New Auction
                            </v-list-tile-title>
                        </router-link>
                    </v-list-tile>
                    <v-list-tile v-if="loggedIn" @click="logout">
                        <router-link to="/" style="color: black; text-decoration: none">
                            <v-list-tile-title>
                                Log Out
                            </v-list-tile-title>
                        </router-link>
                    </v-list-tile>
                    <v-list-tile>
                        <router-link to="/about" style="color: black; text-decoration: none">
                            <v-list-tile-title>
                                About
                            </v-list-tile-title>
                        </router-link>
                    </v-list-tile>
                </v-list>
            </v-menu>
            <v-spacer></v-spacer>
        </v-toolbar>
    </div>
</template>

<script>
    import auth from '../services/authentication'
    import AuctionsFilter from "../components/AuctionsFilter";

    export default {
        name: "Toolbar",
        components: {
            AuctionsFilter
        },
        computed: {
            loggedIn() {
                return this.$store.state.loggedIn;
            },
            showFilters() {
                return this.$store.state.showFilters;
            }
        },
        methods: {
            goToHome() {
                this.$store.commit('setAuctions', []);
                this.$store.commit('setPageNumber', 0);
                if(this.$store.state.showFilters === true){
                    this.$store.commit('flipShowFilters');
                }

            },
            searchClick() {
                this.$store.commit('flipShowFilters');
            },

            async logout() {
                let response = await auth.logout();

                if (response === true)
                    this.$store.commit("setCurrentUser", null);
                    this.$store.commit("setListItemBidFieldSwtich", null);
                    this.$store.commit("setLoggedIn", false);
                }
            }
    }
</script>

<style scoped>
    #mainmenu {
        width: 100%;
        z-index: 100;
        position: fixed;
        bottom: 0;
        right: 0;
    }
</style>