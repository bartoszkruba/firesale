<template>
    <div>
        <HelloWorld/>
        <AuctionsFilter/>
        <AuctionList/>
    </div>
</template>

<script>
    import HelloWorld from '../components/HelloWorld'
    import AuctionList from "../components/AuctionList";
    import AuctionsFilter from "../components/AuctionsFilter";

    export default {
        components: {
            AuctionsFilter,
            AuctionList,
            HelloWorld,
        }
    }
</script>
