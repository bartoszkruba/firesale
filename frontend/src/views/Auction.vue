<template>
        <v-card  id="auction" >
<!--             <v-img id="auctionimages" src="https://static.boredpanda.com/blog/wp-content/uploads/2016/02/japanese-grumpy-cat-angry-koyuki-moflicious-22.jpg"></v-img>-->
            <v-container id="auctioncontent">
                <v-layout>
                    <v-flex>
                        <v-card-title id="auctiontitle"><h1 class="headline, font-weight-bold">{{getViewedAuction.title}}</h1></v-card-title>
                        <v-card-text id="auctiondates"><span class="caption">Auction time: {{getViewedAuction.openedAt}} - {{getViewedAuction.closingTime}}</span></v-card-text>
                        <v-card-text><p class="body-2">{{getViewedAuction.description}}</p></v-card-text>
                        <h2 id="currentprice"
                            align="center"
                            class="subheading">
                            Current price: {{getViewedAuction.startUpPrice}}</h2>

                        <h2 id="currentbid"
                            v-show="loggedIn"
                            align="center"
                            class="subheading,
                            font-weight-bold">
                            Your bid: {{getViewedAuction.buyOutPrice}}</h2>

                        <v-btn id="loginMessage"
                            v-show="!loggedIn"
                            color="red"
                            align="center"
                            small
                            class="subheading,font-weight-bold"
                            @click="routeToLogin"
                        >Log in to place your bid</v-btn>
                        <v-slider
                                v-show="loggedIn"
                                :min="getViewedAuction.startUpPrice"
                                id="priceslider"
                                v-model="getViewedAuction.buyOutPrice"
                        >
                        </v-slider>

                        <v-card-actions v-show="loggedIn"
                                        id="auctionactions" >
                            <v-btn center
                                   color="primary"
                                   id="bidbutton"
                                   @click="bid">BID</v-btn>
                        </v-card-actions>

                    </v-flex>
                </v-layout>
            </v-container>
        </v-card>
</template>

<script>
    export default {
        name: "Auction",
        computed: {
            getViewedAuction(){
                return this.$store.state.currentViewedAuction
            },
            loggedIn() {
                return this.$store.state.loggedIn
            }
        },
        methods: {
            bid () {
               // this.userbid = this.buyoutprice;
                alert("You just made a bid on " + this.getViewedAuction.title)
            },
            routeToLogin() {
                this.$router.push({path: 'login'});
            }
        },
        beforeMount () {
           this.$store.dispatch('getCurrentViewedAuction', this.$route.query.id)
        },
    }

</script>

<style scoped>
    #auction {
        background-color: whitesmoke;
        box-shadow: 5px 5px black;
        height: 100%;
        max-width: 400px;
        align-self: center;
        margin: 0;

    }
    #auctionimages {
        max-height: 35vh;
        object-fit: cover;
    }
    #auctioncontent {

    }
    #auctiontitle {
        margin-bottom: 2px;
        padding: 0;
    }
    #auctiondates{
        padding-top: 0;
        padding-bottom: 5px;
        font-size: 12px;
        font-weight: 200;
    }
    #bidbutton {
    }
    #auctionactions {
        justify-content: center;
    }
</style>