<template>
    <v-app id="inspire">
        <v-content>
            <v-container fluid fill-height>
                <v-layout align-center justify-center>
                    <v-flex xs12 sm8 md4 style="height: 100%">
                        <v-card class="elevation-12" style="height: 92%">
                            <v-toolbar>
                                <v-toolbar-title>Log in to your account</v-toolbar-title>
                                <v-spacer></v-spacer>
                            </v-toolbar>
                            <v-container fluid grid-list-lg>
                                <v-card-text>
                                    <v-img :src="require('../assets/logo.svg')"
                                           class="my-3"
                                           contain
                                           height="100"></v-img>
                                    <v-form>
                                        <v-text-field prepend-icon="person" name="login" label="Login"
                                                      type="text" color="primary"></v-text-field>
                                        <v-text-field prepend-icon="lock" name="password" label="Password"
                                                      type="password" color="primary" id="password"></v-text-field>
                                    </v-form>
                                </v-card-text>
                                <v-card-actions>
                                    <!--                                    <v-spacer></v-spacer>-->
                                    <v-layout align-center justify-center>
                                        <v-btn color="primary" style="width: 100%">Login</v-btn>
                                    </v-layout>
                                </v-card-actions>
                                <v-toolbar class="mt-3">
                                    <v-layout align-center justify-center>
                                        <v-toolbar-item>
                                            New to FireSale? <a href="">Sign In</a>
                                        </v-toolbar-item>
                                    </v-layout>
                                </v-toolbar>
                            </v-container>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    export default {
        name: "Login"
    }
</script>

<style scoped>

    .main-color {
        background-color: #ffc400;
    }

</style>