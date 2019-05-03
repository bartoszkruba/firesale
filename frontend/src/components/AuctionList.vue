
<template>
    <div v-infinite-scroll="loadMore" infinite-scroll-disabled="busy" infinite-scroll-distance="10">
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
        },
        beforeCreate(){

        },
        beforeMount() {
            this.$store.commit('setAuctions', []);
            this.$store.commit('setPageNumber', 0);
        }
    }


</script>

<style scoped>

</style>