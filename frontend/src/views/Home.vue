<template>
    <div>
        <HelloWorld/>
        <AuctionList/>
    </div>
</template>

<script>
    import HelloWorld from '../components/HelloWorld'
    import AuctionList from "../components/AuctionList";

    export default {
        components: {
            AuctionList,
            HelloWorld,
        },
        beforeMount() {
            this.$store.dispatch('getAuctions', this.$route.query);
        }
    }
</script>
