<template>
    <v-app id="app">
        <Notification></Notification>
        <!--        <v-content>-->
        <!--            <v-container fluid fill-height>-->
        <!--                <v-layout align-center justify-center>-->
        <!--                    <v-flex xs12 sm8 md4>-->
        <router-view/>
        <Toolbar></Toolbar>
        <div id="bottomspacer"></div>
        <!--                    </v-flex>-->
        <!--                </v-layout>-->
        <!--            </v-container>-->
        <!--        </v-content>-->

    </v-app>
</template>

<script>
    import Toolbar from "./components/Toolbar";
    import Notification from "./components/Notification"
    import Tester from './components/Tester'

    export default {
        name: 'App',
        components: {
            Toolbar,
            Notification,
            Tester
        },
        data() {
            return {
                snackbar: false,
                timeout: 6000
            }
        },
        async created() {
            // console.log(this.$route.query.id);
            this.$store.dispatch("checkIfLoggedIn");

        }
    }
</script>

<style>
    a {
        text-decoration: none;
    }

    #app {
        background-color: black;
        background-image: linear-gradient(black, lightgrey) ;
        background-image: url("assets/bkg6.jpg");
        background-position: center;
        background-size: cover;
        background-attachment: fixed;
        background-repeat: no-repeat;

    }

    #bottomspacer {
        height: 20vh;
        /*background-color: ;*/
    }
</style>
