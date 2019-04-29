<template>
    <div v-infinite-scroll="loadMore" infinite-scroll-disabled="busy" infinite-scroll-distance="1">
<!--        <div v-for="auction in this.$store.state.auctions" v-bind:key=auction.id @click=toAuctionDetails(auction.id)>-->
            <AuctionListItem v-for="auction in this.$store.state.auctions" :auction=auction />
<!--        </div>-->
    </div>
</template>

<script>
    import AuctionListItem from '../components/AuctionListItem.vue'

    export default {
        name: "AuctionList",
        components: {
            AuctionListItem,
        },
        data() {
            return {
                busy: false,
                // auctions: this.$store.state.auctions
            }
        },
        methods: {
            //Runs at bottom
            async loadMore() {
                this.busy = true;
                setTimeout(() => {
                    this.$store.dispatch('getMoreAuctionsOnScroll', this.$route.query);
                    this.busy = false;
                }, 1000);
            },
            // toAuctionDetails(id) {
            //     this.$router.push({path: 'auction', query: {id}});
            // }
        },
        beforeMount() {
            this.$store.commit('setAuctions', []);
            this.$store.commit('setPageNumber', 0);
        }
    }


</script>

<style scoped>

</style>