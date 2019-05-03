<template>
    <v-card class="text-xs-center pa-5" style="height: 100%">
        <v-icon large color="primary" class="pa-3">account_circle</v-icon>
        <v-card-text class="text-xs-center pa-3">
            <h3 class="title">{{currentUser.username}}</h3>
            <h4 class="subheading">{{currentUser.phoneNumber}}</h4>
            <h4 class="subheading">{{currentUser.email}}</h4>
            <v-btn @click="redirectToChat">Start Chat</v-btn>
            <v-spacer></v-spacer>
        </v-card-text>
    </v-card>
</template>

<script>
    import UserService from '@/services/user'
    import AuctionService from '@/services/auctionsService'
    import conversationService from '@/services/conversationService'


    export default {
        name: "UserProfile",
        components: {},
        data() {
            return {
                currentUser: {},
            }
        },
        methods: {
            async getUser() {
                let u = await UserService.getUserById(this.$route.query);
                this.currentUser = u.data;
            },
            async getAuctionById(id) {
                let auction = await AuctionService().getAuctionById(id);
                console.log(auction.data);
                return auction.data;
            },
            toAuctionDetails(id) {
                this.$router.push({path: 'auction', query: {id}});
            },
            async redirectToChat() {
                if (this.$store.state.loggedIn) {
                    let response = await conversationService.newConversation(this.currentUser.username);

                    let conversations = this.$store.state.conversations;

                    if (conversations.filter((a) => a.id === response.data.id).length === 0) {
                        this.$store.commit("addConversation", response.data)
                    }

                    this.$router.push("/chat/conversation");
                } else {
                    this.$router.push("/login")
                }
            }
        },

        beforeMount() {
            this.getUser();
        }
    }
</script>

<style scoped>

</style>