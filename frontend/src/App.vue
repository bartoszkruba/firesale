<template>
    <v-app id="background">
        <Tester></Tester>
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

    #background {
        background-image: url("assets/auction.jpg");
        background-size: cover;
    }

    #bottomspacer {
        height: 10vh;
        /*background-color: ;*/
    }
</style>
