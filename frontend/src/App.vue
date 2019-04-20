<template>
    <v-app>
        <v-content>
            <router-view/>
            <Toolbar></Toolbar>
            <div id="bottomspacer"></div>
        </v-content>

    </v-app>
</template>

<script>
    import Toolbar from "./components/Toolbar";

    export default {
        name: 'App',
        components: {
            Toolbar,
        },
        data() {
            return {
                //
            }
        },
        async created() {
            this.$store.dispatch("checkIfLoggedIn");
        }
    }
</script>

<style>
    * {
        font-family: "Segoe UI", arial, sans-serif;
    }

    .v-content {
        background-color: #BDBDBD;
    }

    #bottomspacer {
        height: 10vh;
        background-color: #BDBDBD;
    }
</style>
