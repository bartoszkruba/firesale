<template>
    <div>
        <HelloWorld/>
        <AuctionsFilter/>
        <AuctionListItem/>
        <AuctionListItem/>
        <AuctionListItem/>
        <AuctionListItem/>
    </div>
</template>

<script>
    import HelloWorld from '../components/HelloWorld'
    import AuctionsFilter from '../components/AuctionsFilter'
    import AuctionListItem from '../components/AuctionListItem'

    export default {
        components: {
            HelloWorld,
            AuctionsFilter,
            AuctionListItem
        }
    }
</script>
