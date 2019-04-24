<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-toolbar id="mainmenu" color="primary">
        <v-spacer></v-spacer>
        <v-btn icon>
            <router-link to="/" style="color: black; text-decoration: none">
                <v-icon>home</v-icon>
            </router-link>
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn icon>
            <v-icon>search</v-icon>
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn icon>
            <v-icon>message</v-icon>
        </v-btn>
        <v-spacer></v-spacer>
        <v-menu :nudge-width="100">
            <template v-slot:activator="{ on }">
                <v-toolbar-title v-on="on">
                    <v-btn icon>
                        <v-icon>account_circle</v-icon>
                    </v-btn>
                </v-toolbar-title>
            </template>
            <v-list>
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
            </v-list>
        </v-menu>
        <v-spacer></v-spacer>
    </v-toolbar>
</template>

<script>
    import auth from '../services/authentication'

    export default {
        name: "Toolbar",
        computed: {
            loggedIn() {
                return this.$store.state.loggedIn;
            }
        },
        methods: {
            async logout() {
                let response = await auth.logout();
                if (response === true) {
                    this.$store.commit("setLoggedIn", false);
                }
            }
        }
    }
</script>

<style scoped>
    #mainmenu {
        width: 100%;
        z-index: 100;
        position: fixed;
        bottom: 0px;
        right: 0px;
    }
</style>