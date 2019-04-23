<template>
    <!--    <v-app id="inspire">-->
    <v-content>
        <v-container fluid fill-height>
            <v-layout align-center justify-center>
                <v-flex xs12 sm8 md4 style="height: 100%">
                    <v-card class="elevation-12" style="height: 92%">
                        <v-toolbar>
                            <v-toolbar-title>Create account</v-toolbar-title>
                            <v-spacer></v-spacer>
                        </v-toolbar>
                        <v-container fluid grid-list-lg>
                            <v-card-text>
                                <v-form>
                                    <v-text-field prepend-icon="person" name="usename" label="Username*"
                                                  type="text" color="primary" v-model="username"
                                                  :error-messages="usernameError"
                                                  @keydown.enter="register"
                                                  @keydown="clearUsernameError"
                                                  @keydown.space="(event) => event.preventDefault()"></v-text-field>

                                    <v-text-field prepend-icon="lock" name="password" label="Password*"
                                                  type="password" color="primary" v-model="password"
                                                  :error-messages="passwordError"
                                                  @keydown.enter="register"
                                                  @keydown="clearPasswordError"
                                                  @keydown.space="(event) => event.preventDefault()"></v-text-field>

                                    <v-text-field prepend-icon="lock" name="password" label="Repeat password*"
                                                  type="password" color="primary"
                                                  v-model="repeatedPassword"
                                                  :error-messages="repeatedPasswordError"
                                                  @keydown.enter="register"
                                                  @keydown="clearRepeatedPasswordError"
                                                  @keydown.space="(event) => event.preventDefault()"></v-text-field>

                                    <v-text-field prepend-icon="face" name="firstName" label="First Name*"
                                                  type="text" color="primary" v-model="firstName"
                                                  :error-messages="firstNameError"
                                                  @keydown.enter="register"
                                                  @keydown="clearFirstNameError"></v-text-field>

                                    <v-text-field prepend-icon="face" name="lastName" label="Last Name*"
                                                  type="text" color="primary" v-model="lastName"
                                                  :error-messages="lastNameError"
                                                  @keydown.enter="register"
                                                  @keydown="clearLastNameError"></v-text-field>

                                    <v-text-field prepend-icon="email" name="email" label="Email*"
                                                  type="email" color="primary" v-model="email"
                                                  :error-messages="emailError"
                                                  @keydown.enter="register"
                                                  @keydown="clearEmailError"
                                                  @keydown.space="(event) => event.preventDefault()"></v-text-field>

                                    <v-text-field prepend-icon="phone" name="phone" label="Contact Number*"
                                                  type="enter" color="primary" v-model="phone"
                                                  :error-messages="phoneError"
                                                  @keydown.enter="register"
                                                  @keydown="preventUnwantedCharacters"></v-text-field>
                                </v-form>
                            </v-card-text>
                            <v-card-actions>
                                <v-layout align-center justify-center>
                                    <v-btn color="primary" style="width: 100% " @click="register">Register</v-btn>
                                </v-layout>
                            </v-card-actions>
                        </v-container>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>
    </v-content>
    <!--    </v-app>-->
</template>

<script>
    import userService from '../services/user'
    import auth from '../services/authentication'

    export default {
        name: "Register",
        data() {
            return {
                username: "",
                password: "",
                repeatedPassword: "",
                firstName: "",
                lastName: "",
                email: "",
                phone: "",

                usernameError: "",
                passwordError: "",
                repeatedPasswordError: "",
                firstNameError: "",
                lastNameError: "",
                emailError: "",
                phoneError: ""
            }
        },
        methods: {
            async register() {
                if (this.validateFields()) {
                    try {
                        let response = await userService.registerAccount({
                            username: this.username,
                            password: this.password,
                            firstName: this.firstNamei,
                            lastName: this.lastName,
                            email: this.email,
                            phoneNumber: this.phoneNumber
                        });
                        if (response.status === 201) {
                            if (await auth.login(this.username, this.password)) {
                                this.$router.push("/");
                            }
                        }
                    } catch (e) {
                        if (e.response.status === 409) {
                            this.usernameError = "Username already exists"
                        }
                    }
                }
            },
            validateFields() {
                this.usernameError = "";
                this.passwordError = "";
                this.repeatedPasswordError = "";
                this.firstNameError = "";
                this.lastNameError = "";
                this.emailError = "";
                this.phoneError = "";

                let validation = true;
                if (this.username === "") {
                    this.usernameError = "Field cannot be empty";
                    validation = false;
                }
                if (this.password === "") {
                    this.passwordError = "Field cannot be empty";
                    validation = false;
                } else if (this.password !== this.repeatedPassword) {
                    this.repeatedPasswordError = "Password doesn't match";
                    validation = false;
                }

                // todo add regex to replace all double space with sinngle one
                this.firstName = this.firstName.trim();
                if (this.firstName === "") {
                    this.firstNameError = "Field cannot be empty";
                    validation = false;
                }

                this.lastName = this.lastName.trim();
                if (this.lastName === "") {
                    this.lastNameError = "Field cannot be empty";
                    validation = false;
                }
                if (this.email === "") {
                    this.emailError = "Field cannot be empty";
                    validation = false;
                } else {
// eslint-disable-next-line
                    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                    if (!re.test(String(this.email).toLowerCase())) {
                        this.emailError = "Invalid email";
                        validation = false;
                    }
                }
                this.phone.trim();
                if (this.phone === "") {
                    this.phoneError = "Field cannot be empty";
                    validation = false;
                }
                return validation;
            },
            preventUnwantedCharacters(e) {
                this.clearPhoneError(e);
                let re = /[0-9 ()-]|Backspace/;
                if (!e.key.toString().match(re)) {
                    e.preventDefault()
                }
            },
            clearUsernameError(e) {
                if (e.key !== 'enter') {
                    this.usernameError = "";
                }
            },
            clearPasswordError(e) {
                if (e.key !== 'enter') {
                    this.passwordError = "";
                }
            },
            clearRepeatedPasswordError(e) {
                if (e.key !== 'enter') {
                    this.repeatedPasswordError = "";
                }
            },
            clearFirstNameError(e) {
                if (e.key !== 'enter') {
                    this.firstNameError = "";
                }
            },
            clearLastNameError(e) {
                if (e.key !== 'enter') {
                    this.lastNameError = "";
                }
            },
            clearEmailError(e) {
                if (e.key !== 'enter') {
                    this.emailError = "";
                }
            },
            clearPhoneError(e) {
                if (e.key !== 'enter') {
                    this.phoneError = "";
                }
            }
        }
    }
</script>

<style scoped>

</style>