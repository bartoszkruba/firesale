<template>
    <div>
        <HelloWorld/>
        <AuctionList :key="+this.$route.query"/>
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
        data(){
            return {
            }
        },
        mounted() {

        }
    }
</script>
