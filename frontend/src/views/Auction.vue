<template>
        <v-card  id="auction" >
            <v-img id="auctionimages" :src="getViewedAuction.images[0].filePath"></v-img>
            <v-container id="auctioncontent">
                <v-layout>
                    <v-flex>
                        <v-card-title id="auctiontitle"><h1 class="headline, font-weight-bold">{{getViewedAuction.title}}</h1></v-card-title>
                        <v-card-text id="auctiondates"><span class="caption">Auction time: {{getViewedAuction.openedAt}} - {{getViewedAuction.closingtime}}</span></v-card-text>
                        <v-card-text><p class="body-2">{{getViewedAuction.description}}</p></v-card-text>

                        <h2 id="currentprice" align="center" class="subheading">Current price: {{getViewedAuction.startUpPrice}}</h2>
                        <h2 id="currentbid" align="center" class="subheading, , font-weight-bold">Your bid: {{getViewedAuction.buyOutPrice}}</h2>

                        <v-slider
                                :min="getViewedAuction.startUpPrice"
                                id="priceslider"
                                v-model="getViewedAuction.buyOutPrice"
                        >
                        </v-slider>

                        <v-card-actions id="auctionactions" >
                            <v-btn center color="primary" id="bidbutton" @click="bid">BID</v-btn>
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
            }
        },
        methods: {
            bid () {
               // this.userbid = this.buyoutprice;
                alert("You just made a bids on " + this.title)

            },
        },
        created: {
        }
        /*props: {
            userbid: Number,
            id: Number,
            title: String,
            description: String,
            openedat: String,
            closingtime: String,
            startupprice: Number,
            buyoutprice: Number,
            status: String,
            mainimage: String,
        }*/
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
        bottom-margin: 2px;
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