<template>
    <v-app id="app">
        <Notification></Notification>
        <router-view/>
        <Toolbar></Toolbar>
        <div id="bottomspacer"></div>
    </v-app>
</template>

<script>
    import Toolbar from "./components/Toolbar";
    import Notification from "./components/Notification"

    export default {
        name: 'App',
        components: {
            Toolbar,
            Notification,
        },
        data() {
            return {
                snackbar: false,
                timeout: 6000
            }
        },
        async created() {
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
