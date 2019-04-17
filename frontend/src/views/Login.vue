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
                                        <v-alert v-bind:value="showError" type="error">
                                            Username and password doesn't match
                                        </v-alert>
                                        <v-text-field prepend-icon="person" name="usename" label="Username"
                                                      type="text" color="primary" v-model="username"
                                                      @keydown.enter="login"></v-text-field>
                                        <v-text-field prepend-icon="lock" name="password" label="Password"
                                                      type="password" color="primary"
                                                      v-model="password" @keydown.enter="login"></v-text-field>
                                    </v-form>
                                </v-card-text>
                                <v-card-actions>
                                    <v-layout align-center justify-center>
                                        <v-btn color="primary" style="width: 100% " @click="login">Login</v-btn>
                                    </v-layout>
                                </v-card-actions>
                                <v-toolbar class="mt-3">
                                    <v-layout align-center justify-center>
                                        <v-toolbar-items name="signup">
                                            <span>New to Firesale? <a href="">Register</a></span>
                                        </v-toolbar-items>
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
    import auth from '../services/authentication'

    export default {


        name: "Login",
        data() {
            return {
                username: "",
                password: "",
                usernameError: "Username and password doesn't match",
                showError: false
            }
        }
        ,
        methods: {
            async login() {
                let status = await auth.login(this.username, this.password);
                this.showError = !status;
                if (status) {
                    this.$store.commit("setLoggedIn", true);
                    this.$router.push("/")
                }
            }
        }
    }
</script>

<style scoped>

</style>