<template>
    <div>
        <Logo/>
        <AuctionList :key="+this.$route.query"/>
    </div>
</template>

<script>
    import Logo from '../components/Logo'
    import AuctionList from "../components/AuctionList";

    export default {
        components: {
            AuctionList,
            Logo,
        },
        data(){
            return {
            }
        },
        mounted() {

        }
    }
</script>
