<template>
    <v-container
            fill-height
            fluid
            grid-list-xl>
        <v-layout
                justify-center
                wrap
        >
            <v-flex
                    xs12
                    md4
            >

                <v-card class="text-xs-center pa-5">
                    <v-icon large color="primary" class="pa-3">account_circle</v-icon>
                    <v-card-text class="text-xs-center pa-3">
                        <h3 class="title">{{getCurrentuser.username}}</h3>
                        <h4 class="subheading">{{getCurrentuser.firstName}} {{getCurrentuser.lastName}}</h4>
                        <h4 class="subheading">{{getCurrentuser.phoneNumber}}</h4>
                        <h4 class="subheading">{{getCurrentuser.email}}</h4>

                        <v-spacer></v-spacer>
                    </v-card-text>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>

    export default {
        name: "Profile",
        data: function () {
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
        },
        methods: {},
        validateFields() {
            this.usernameError = "";
            this.firstNameError = "";
            this.lastNameError = "";
            this.emailError = "";
            this.phoneError = "";

            let validation = true;
            if (this.username === "") {
                this.usernameError = "Field cannot be empty";
                validation = false;
            }
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
    }
</script>

<style scoped>

</style>