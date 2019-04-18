<template>
    <v-app>
        <v-content>
            <router-view/>
            <Toolbar></Toolbar>
        </v-content>
    </v-app>
</template>

<script>
    import Toolbar from "./components/Toolbar";

    export default {
        name: 'App',
        components: {
            Toolbar

        },
        data() {
            return {
                //
            }
        },
        async created() {
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
</style>
