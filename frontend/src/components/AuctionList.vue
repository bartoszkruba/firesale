<template>
    <div>
    <div v-for="auction in this.$store.state.auctions" v-bind:key=auction.id @click=toAuctionDetails(auction.id)>
        <AuctionListItem
                :auction=auction

            >

        </AuctionListItem>

    </div></div>
</template>

<script>
    import AuctionListItem from '../components/AuctionListItem.vue'

    export default {
        namne: "AuctionList",
        components: {
            AuctionListItem,
        },
        data() {
            return {

                // auctions: this.$store.state.auctions
            }
        },
        methods: {
            toAuctionDetails(id) {
                this.$router.push({path: 'auction', query: {id}});
            }
        }
    }


</script>

<style scoped>

</style>