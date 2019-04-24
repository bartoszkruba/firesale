<template>
    <div>
    <div v-for="auction in this.$store.state.auctions" v-bind:key=auction.id>
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
        }
    }


</script>

<style scoped>

</style>