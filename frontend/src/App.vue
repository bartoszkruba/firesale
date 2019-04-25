<template>
    <v-app>
        <!--        <v-content>-->
        <!--            <v-container fluid fill-height>-->
        <!--                <v-layout align-center justify-center>-->
        <!--                    <v-flex xs12 sm8 md4>-->
        <router-view/>
        <Toolbar></Toolbar>
        <About></About>
        <div id="bottomspacer"></div>
        <!--                    </v-flex>-->
        <!--                </v-layout>-->
        <!--            </v-container>-->
        <!--        </v-content>-->

    </v-app>
</template>

<script>
    import Toolbar from "./components/Toolbar";
    import About from "./components/About";

    export default {
        name: 'App',
        components: {
            Toolbar,
            About,
        },
        data() {
            return {
                //
            }
        },
        async created() {
            console.log(this.$route.query.id);
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
        /*background-color: ;*/
    }
</style>
