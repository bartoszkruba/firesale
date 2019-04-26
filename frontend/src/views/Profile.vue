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
                    md8
                    v-show="editProfile"
            >
                    <v-form>
                        <v-container py-0>
                            <v-layout wrap>
                                <v-flex
                                        xs12
                                        md4
                                >
                                    <v-text-field
                                            class="purple-input"
                                            label="User Name"
                                    />
                                </v-flex>
                                <v-flex
                                        xs12
                                        md4
                                >
                                    <v-text-field
                                            label="Email Address"
                                            class="purple-input"/>
                                </v-flex>
                                <v-flex
                                        xs12
                                        md6
                                >
                                    <v-text-field
                                            label="Phone number"
                                            />
                                </v-flex>
                                <v-flex
                                        xs12
                                        md12
                                >
                                    <v-text-field
                                            label="Adress"
                                            class="purple-input"/>
                                </v-flex>

                                <v-flex
                                        xs12
                                        text-xs-right
                                >
                                    <v-btn
                                            @click="editProfile = !editProfile"
                                            color="success"
                                    >
                                        Save
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-container>
                    </v-form>
                </v-card>
            </v-flex>
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
                        <v-spacer></v-spacer>
                    </v-card-text>
                    <v-btn color="primary" @click="editProfile=!editProfile" v-show="!editProfile">Edit</v-btn>
                </v-card>
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
            }
        },
        computed: {
            getCurrentuser() {
                return this.$store.state.currentUser;
            },

        }
    }

    /*  "id": 2,
        "username": "mary111",
        "email": "mary323@gmail.com",
        "phoneNumber": "(277)-505-2351",
        "auctions": []
    */

</script>

<style scoped>

</style>