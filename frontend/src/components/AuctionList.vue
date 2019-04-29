
<template>
    <div v-infinite-scroll="loadMore" infinite-scroll-disabled="busy" infinite-scroll-distance="10">
<!--        <div v-if="this.$store.state.auctions.length === 0"><h2 class="text-xs-center">No auctions found</h2></div>-->
        <div class="text-xs-center" v-if="this.$store.state.auctions.length === 0">
            <v-progress-circular
                    :size="50"
                    color="primary"
                    indeterminate
            ></v-progress-circular>
        </div>
        <AuctionListItem v-for="auction in this.$store.state.auctions" v-bind:key=auction.id :auction=auction />
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
                    let query = this.$route.query;
                    console.log('query before', query);
                    this.$store.dispatch('getMoreAuctionsOnScroll', query);
                    console.log('query after', query);
                    this.busy = false;
                }, 1000);
            },
            // toAuctionDetails(id) {
            //     this.$router.push({path: 'auction', query: {id}});
            // }
        },
        beforeCreate(){

        },
        beforeMount() {
            this.$store.commit('setAuctions', []);
            this.$store.commit('setPageNumber', 0);
            // this.loadMore();
            //
            // console.log(this.$route.query);
            // console.log(this.$store.state.auctions);
        }
    }


</script>

<style scoped>

</style>