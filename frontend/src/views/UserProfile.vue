<template>
    <v-card class="text-xs-center pa-5">
        <v-icon large color="primary" class="pa-3">account_circle</v-icon>
        <v-card-text class="text-xs-center pa-3">
            <h3 class="title">{{currentUser.username}}</h3>
            <h4 class="subheading">{{currentUser.phoneNumber}}</h4>
            <h4 class="subheading">{{currentUser.email}}</h4>
<!--            <div v-for="auctionId in currentUser.auctions" v-bind:key=auctionId @click=toAuctionDetails(auctionId)>-->
<!--                <AuctionListItem :auction=getAuctionById(auctionId) />-->
<!--            </div>-->
            <v-spacer></v-spacer>
            </v-card-text>
    </v-card>
</template>

<script>
    import UserService from '@/services/user'
    import AuctionService from '@/services/auctionsService'
    // import AuctionListItem from '../components/AuctionListItem.vue'
    export default {
        name: "UserProfile",
        components: {
            // AuctionListItem,
        },
        data(){
            return {
                currentUser: {},
            }
        },
        methods: {
          async getUser(){
            this.currentUser = await UserService.getUserById(this.$route.query);
            this.currentUser = this.currentUser.data;
          },
            async getAuctionById(id){
                let auction = await AuctionService().getAuctionById(id);
                //console.log(auction.data);
                return auction.data;
            },
            async getAuctionById(id){
                let auction = await AuctionService().getAuctionById(id);
                //console.log(auction.data);
                return auction.data;
            },
        toAuctionDetails(id) {
            this.$router.push({path: 'auction', query: {id}});
        }
        },

        beforeMount() {
            this.getUser();
        }
    }
</script>

<style scoped>

</style>