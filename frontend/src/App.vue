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
import auth from './services/authentication';

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
            console.log(auth.login("john69", "password1234"));
        }
    }
</script>

<style>
    * {
        font-family: "Segoe UI", arial, sans-serif;
    }
    .v-content{
        background-color: #BDBDBD;
    }
    #bottomspacer {
        height: 10vh;
        background-color: #BDBDBD;
    }
</style>
