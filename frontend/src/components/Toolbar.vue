<template>
        <v-toolbar id="mainmenu">
            <v-spacer></v-spacer>
            <v-btn icon>
            <v-icon>home</v-icon>
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
            <v-btn icon>

                <v-icon>account_circle</v-icon>
            </v-btn>
            <v-spacer></v-spacer>
        </v-toolbar>
</template>

<script>
    export default {
        name: "Toolbar"
    }
</script>

<style scoped>
    #mainmenu{
        width: 100%;
        z-index: 100;
        position: fixed;
        bottom: 0px;
        right: 0px;
        background-color: #ffc400;

    }
</style>