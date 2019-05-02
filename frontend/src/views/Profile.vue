<template>
    <v-container
            fill-height
            fluid
            grid-list-xl>
        <v-layout
                justify-center
                wrap
        ><!--
                <v-form xs12 md6 v-show="editProfile">
                    <v-text-field prepend-icon="person" name="usename" label="Username*"
                                  type="text" color="primary" v-model="username"
                                  :error-messages="usernameError"
                                  @keydown.enter="register"
                                  @keydown="clearUsernameError"
                                  @keydown.space="(event) => event.preventDefault()"></v-text-field>

                    &lt;!&ndash;<v-text-field prepend-icon="lock" name="password" label="Password*"
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
                                  @keydown.space="(event) => event.preventDefault()"></v-text-field>&ndash;&gt;

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
                    <v-btn color="success" @click="submitEditedProfile">Submit</v-btn>
                </v-form>-->
            <v-flex
                    xs12
                    md4
            >

                <v-card class="text-xs-center pa-5">
                    <v-icon large color="primary" class="pa-3">account_circle</v-icon>
                    <v-card-text class="text-xs-center pa-3">
                        <h3 class="title">{{getCurrentuser.username}}</h3>
                        <h4 class="subheading">{{getCurrentuser.phoneNumber}}</h4>
                        <h4 class="subheading">{{getCurrentuser.email}}</h4>
                        <h4 class="subheading">{{getCurrentuser.firstName}}</h4>
                        <v-spacer></v-spacer>
                    </v-card-text>
<!--                    <v-btn color="primary" @click="editProfile=!editProfile" v-show="!editProfile">Edit</v-btn>-->
                </v-card>
<!--

                <v-card class="text-xs-center pa-5">
                    <v-icon large color="primary" class="pa-3">myAuctions</v-icon>
                    <v-card-text class="text-xs-center pa-3">
                        {{getOwneAuctions}}
                        <v-spacer></v-spacer>
                    </v-card-text>
                </v-card>
-->


            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>

    export default {
        name: "Profile",
        data: function() {
            return {
                editProfile: false,
                editProfileSuccess: false,
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
        beforeMount() {
            this.$store.dispatch('getCurrentUser')
        },
        computed: {
            getCurrentuser() {
                return this.$store.state.currentUser;
            },
            /*getOwneAuctions(){///TODO
                return this.$store.state.getOwendAuctionByUser;
            }*/
        },
        methods: {
           /* async submitEditedProfile() {
                if(this.validateFields()){
                    this.editProfile = false;
                    try {
                        let response = await userService.updateAccount({
                            userid: this.userid
                            username: this.username,
                            /*password: this.password
                            firstName: this.firstName,
                            lastName: this.lastName
                            email: this.email,
                            phoneNumber: this.phone
                        });
                        if (response.status === 204) {

                        }
                    } catch (e) {
                        if (e.response.status === 409) {
                            this.usernameError = "Username already exists"
                        }
                    }
                }*/
            },
            validateFields() {
                this.usernameError = "";
                //this.passwordError = "";
                //this.repeatedPasswordError = "";
                this.firstNameError = "";
                this.lastNameError = "";
                this.emailError = "";
                this.phoneError = "";

                let validation = true;
                if (this.username === "") {
                    this.usernameError = "Field cannot be empty";
                    validation = false;
                }
               /* if (this.password === "") {
                    this.passwordError = "Field cannot be empty";
                    validation = false;
                } else if (this.password !== this.repeatedPassword) {
                    this.repeatedPasswordError = "Password doesn't match";
                    validation = false;
                }
                */


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
                    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
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
            /*
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
            */
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
            },
            /*async register() {
                if (this.validateFields()) {

                    try {
                        let response = await userService.registerAccount({
                            username: this.username,
                            password: this.password,
                            firstName: this.firstName,
                            lastName: this.lastName,
                            email: this.email,
                            phoneNumber: this.phone
                        });
                        if (response.status === 201) {
                                this.editProfileSuccess = true;
                            }
                        }
                         catch (e) {
                        if (e.response.status === 409) {
                            this.usernameError = "Username already exists"
                        }
                    }
                }
            },*/

    /*  "id": 2,
        "username": "mary111",
        "email": "mary323@gmail.com",
        "phoneNumber": "(277)-505-2351",
        "auctions": []
    */
    }
</script>

<style scoped>

</style>