<template>
    <div>
        <HelloWorld/>
        <AuctionListItem/>
        <AuctionListItem/>
        <AuctionListItem/>
        <AuctionListItem/>
    </div>
</template>

<script>
    import HelloWorld from '../components/HelloWorld'
    import AuctionListItem from '../components/AuctionListItem'

    export default {
        components: {
            HelloWorld,
            AuctionListItem
        }
    }
</script>
