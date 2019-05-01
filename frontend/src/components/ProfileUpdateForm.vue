<template>
    <!--    <v-app id="inspire">-->
    <v-content>
        <v-container fluid fill-height>
            <v-layout align-center justify-center>
                <v-flex xs12 sm8 md4 style="height: 100%">
                    <v-card class="elevation-12" style="height: 92%">
                        <v-toolbar>
                            <v-toolbar-title>Update account</v-toolbar-title>
                            <v-spacer></v-spacer>
                        </v-toolbar>
                        <v-container fluid grid-list-lg>
                            <v-card-text>
                                <v-form>

                                    <v-text-field prepend-icon="face" name="firstName" label="First Name*"
                                                  type="text" color="primary" v-model="firstName"
                                                  :error-messages="firstNameError"
                                                  @keydown.enter="postAccountUpdate"
                                    >
<!--                                                  @keydown="clearFirstNameError">-->

                                    </v-text-field>

                                    <v-text-field prepend-icon="face" name="lastName" label="Last Name*"
                                                  type="text" color="primary" v-model="lastName"
                                                  :error-messages="lastNameError"
                                                  @keydown.enter="postAccountUpdate"
                                    >
<!--                                                  @keydown="clearLastNameError"-->

                                    </v-text-field>

                                    <v-text-field prepend-icon="email" name="email" label="Email*"
                                                  type="email" color="primary" v-model="email"
                                                  :error-messages="emailError">
<!--                                                  @keydown.enter="register"-->
<!--                                                  @keydown="clearEmailError"-->
                                                  @keydown.space="(event) => event.preventDefault()"></v-text-field>

                                    <v-text-field prepend-icon="phone" name="phone" label="Contact Number*"
                                                  type="enter" color="primary" v-model="phone"
                                                  :error-messages="phoneError"
                                                  @keydown.enter="postAccountUpdate"></v-text-field>
<!--                                                  @keydown="preventUnwantedCharacters"></v-text-field>-->

                                    <v-btn color="success" style="width: 100% " @click=postAccountUpdate>Update</v-btn>
                                    <v-spacer></v-spacer>
                                </v-form>
                            </v-card-text>
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
        name: "ProfileUpdateForm",
        created() {
            this.getUserData();
        },
        data() {
            return {
                userId: "",
                userName: "",
                firstName: "",
                lastName: "",
                email: "",
                phone: "",
                //ERRORS:
                firstNameError: "",
                lastNameError: "",
                emailError: "",
                phoneError: ""
            }
        },
        methods: {
            getUserData() {

                let userData = this.$store.state.currentUser;
                console.log(userData);
                this.userId = userData.userId;
                this.userName = userData.userName;
                this.firstName = userData.firstName;
                this.lastName = userData.lastName;
                this.email = userData.email;
                this.phone = userData.phone;
            },
        async postAccountUpdate() {
            if (this.validateFields()) {
                console.log('Fields validated >>> broadcasting');
                try {
                    let response = await userService.updateAccount({
                        userId: this.userId,
                        userName: this.userName,
                        firstName: this.firstName,
                        lastName: this.lastName,
                        email: this.email,
                        phoneNumber: this.phone
                    });
                } catch (e) {
                    console.log(e);
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

            // todo add regex to replace all double space with single one
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