<template>
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
                                <v-img :src="require('../assets/logoNoName.png')"
                                       class="my-3"
                                       contain
                                       height="100"></v-img>
                                <v-form>
                                    <v-alert v-bind:value="showError" type="error">
                                        Username and password doesn't match
                                    </v-alert>
                                    <br>
                                    <v-text-field prepend-icon="person" name="usename" label="Username"
                                                  type="text" color="primary" v-model="username"
                                                  @keydown="clearUsernameError"
                                                  @keydown.enter="login"
                                                  :error-messages="usernameError"
                                                  @keydown.space="(event) => event.preventDefault()"></v-text-field>
                                    <v-text-field prepend-icon="lock" name="password" label="Password"
                                                  type="password" color="primary" :error-messages="passwordError"
                                                  v-model="password"
                                                  @keydown="clearPasswordError"
                                                  @keydown.enter="login"
                                                  @keydown.space="(event) => event.preventDefault()"></v-text-field>
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
                                        <span>New to Firesale?
                                            <router-link to="/register">Register</router-link>
                                        </span>
                                    </v-toolbar-items>
                                </v-layout>
                            </v-toolbar>
                        </v-container>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>
    </v-content>
</template>

<script>
    import auth from '../services/authentication'
    import socketService from '../services/socket'

    export default {


        name: "Login",
        data() {
            return {
                username: "",
                password: "",
                usernameError: "",
                passwordError: "",
                showError: false
            }
        }
        ,
        methods: {
            async login() {
                this.showError = false;
                this.usernameError = "";
                this.passwordError = "";
                if (this.validateUsername() && this.validatePassword()) {
                    let status = await auth.login(this.username, this.password);
                    this.showError = !status;
                    if (status) {
                        this.$store.commit("setLoggedIn", true);
                        this.$store.dispatch('getCurrentUser');
                        socketService().reconnect(() => {
                            this.$store.dispatch("subscribeChat");
                            this.$store.dispatch('subscribeToNotifications');
                            this.$router.push("/");
                        });
                    }
                }
            },
            validateUsername() {
                if (this.username === "") {
                    this.usernameError = "Enter your username";
                    return false;
                }
                return true;
            },
            validatePassword() {
                if (this.password === "") {
                    this.passwordError = "Enter your password";
                    return false;
                }
                return true;
            },
            clearUsernameError(e) {
                if (e.key !== "enter") {
                    this.usernameError = ""
                }
            },
            clearPasswordError(e) {
                if (e.key !== "enter") {
                    this.passwordError = ""
                }
            }
        }
    }
</script>

<style scoped>

</style>