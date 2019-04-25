<template>
    <div v-infinite-scroll="loadMore" infinite-scroll-disabled="busy" infinite-scroll-distance="10">
        <div v-for="auction in this.$store.state.auctions" v-bind:key=auction.id @click=toAuctionDetails(auction.id)>
            <AuctionListItem :auction=auction />
        </div>
    </div>
</template>

<script>
    import AuctionListItem from '../components/AuctionListItem.vue'
    import auctionsService from "../services/auctionsService";

    export default {
        namne: "AuctionList",
        components: {
            AuctionListItem,
        },
        data() {
            return {
                busy: false
                // auctions: this.$store.state.auctions
            }
        },
        methods: {
            //Runs at bottom
            async loadMore() {
                this.busy = true;
                //counts number of auctions
                let numberOfTotalAuctions = await auctionsService().countAuctionsBasedOnTitle(this.$route.query).then(response => response.data);
                setTimeout(() => {
                    //Compares number of auctions in database with how many that are loaded on frontend
                    if (numberOfTotalAuctions > this.$store.state.auctions.length) {
                        this.$store.dispatch('getMoreAuctionsOnScroll', this.$route.query);
                        this.$router.push({path: 'auctions', query: this.$route.query});
                    }
                    this.busy = false;
                }, 1000);
            },
            toAuctionDetails(id) {
                this.$router.push({path: 'auction', query: {id}});
            }
        },
        beforeMount() {
            // this.$store.dispatch('getAuctions', this.$route.query);
        }
    }


</script>

<style scoped>

</style>