<template>
    <div>
        <HelloWorld/>
        <AuctionList/>
    </div>
</template>

<script>
    import HelloWorld from '../components/HelloWorld'
    import AuctionList from '../components/AuctionList'

    export default {
        components: {
            HelloWorld,
            AuctionList
        }
    }
</script>
