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
                    <h1 class="title">PROFILEPAGE</h1>
                    <br>
                    <br>
                    <v-card-text class="text-xs-center pa-3">
                        <h1 class="heading">{{getCurrentuser.userName}}</h1>
                        <br>
                        <h4 class="subheading">{{getCurrentuser.firstName}} {{getCurrentuser.lastName}}</h4>
                        <h4 class="subheading">{{getCurrentuser.phoneNumber}}</h4>
                        <h4 class="subheading">{{getCurrentuser.email}}</h4>
                    </v-card-text>
                    <v-btn color="primary" @click="editProfile=!editProfile" v-show="!editProfile">Edit</v-btn>

                </v-card>
            </v-flex>
            <ProfileUpdateForm v-show="editProfile"></ProfileUpdateForm>
        </v-layout>
    </v-container>
</template>

<script>
    import userService from '../services/user'
    import auth from '../services/authentication'
    import ProfileUpdateForm from '../components/ProfileUpdateForm'

    export default {
        name: "Profile",
        components: {ProfileUpdateForm},
        data: function() {
            return {
                editProfile: false,
            }
        },
        beforeCreate() {
            this.$store.dispatch('getCurrentUser')
        },
        computed: {
            getCurrentuser() {
               return this.$store.state.currentUser;
            },

        },
        methods: {

            },
    }
</script>

<style scoped>

</style>