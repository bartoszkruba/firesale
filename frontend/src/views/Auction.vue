<template>
    <!--        <v-card  id="auction" >-->
    <!--             <v-img id="auctionimages" src="https://static.boredpanda.com/blog/wp-content/uploads/2016/02/japanese-grumpy-cat-angry-koyuki-moflicious-22.jpg"></v-img>-->
    <!--            <v-container id="auctioncontent">-->
    <!--                <v-layout>-->
    <!--                    <v-flex>-->
    <!--                        -->

    <!--                    </v-flex>-->
    <!--                </v-layout>-->
    <!--            </v-container>-->
    <!--        </v-card>-->

    <v-content v-if="getViewedAuction !== null">
        <!--        <v-layout align-center>-->
        <div id="auction" class="mr-auto ml-auto">
            <v-container>
                <v-card>
                    <v-toolbar>
                        <v-toolbar-title>{{getViewedAuction.title}}</v-toolbar-title>
                        <v-spacer></v-spacer>
                    </v-toolbar>

                    <v-carousel v-if="amountImages > 0">
                        <v-carousel-item :key="i" v-for="i in getViewedAuction.images">
                            <v-img :src="i.filepath" alt=""></v-img>
                        </v-carousel-item>
                    </v-carousel>

                    <v-card-text>
                        <h3>Posted By: <b>
                            <router-link :to="getUserUrl">{{getViewedAuction.user.username}}</router-link>
                        </b></h3>
                        <br>
                        <h4>Created At: {{closingTime}}</h4>
                        <h4>Closes At: {{getViewedAuction.closingTime}}</h4>
                    </v-card-text>
                    <v-card-text>
                        <h3>Description:</h3>
                        <p class="body-2">{{getViewedAuction.description}}</p>
                    </v-card-text>
                    <v-card-text>
                        <h3>Current highest bid: {{getViewedAuction.startUpPrice}} SEK</h3>
                    </v-card-text>
                    <h2 id="currentbid"
                        v-show="loggedIn"
                        class="subheading,
                            font-weight-bold">
                        Your bid: {{getViewedAuction.buyOutPrice}}</h2>

                    <v-btn id="loginMessage"
                           v-show="!loggedIn"
                           color="red"
                           small
                           class="subheading,font-weight-bold"
                           @click="routeToLogin">
                        Log in to place your bid
                    </v-btn>
                    <v-slider v-show="loggedIn" :min="getViewedAuction.startUpPrice" id="priceslider"
                              v-model="getViewedAuction.buyOutPrice">
                    </v-slider>

                    <v-card-actions v-show="loggedIn"
                                    id="auctionactions">
                        <v-btn center color="primary" id="bidbutton" @click="bid">BID</v-btn>
                    </v-card-actions>
                </v-card>
            </v-container>
        </div>
        <!--        </v-layout>-->
    </v-content>
    <v-content v-else>
        <v-container fluid fill-height>
            <v-layout align-center justify-center>
                <v-flex xs12 sm8 md4 style="height: 100%">
                    <v-card class="elevation-12">
                        <h1>Auction doesn't exist</h1>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>
    </v-content>
</template>

<script>
    // let dateFormat = require('dateformat');
    export default {
        name: "Auction",
        computed: {
            getViewedAuction() {
                return this.$store.state.currentViewedAuction
            },
            loggedIn() {
                return this.$store.state.loggedIn
            },
            amountImages() {
                if (!this.$store.state.currentViewedAuction.images) {
                    return null;
                }
                return this.$store.state.currentViewedAuction.images.length;
            },
            getUrl() {
                return `http://${window.location.host}`
            },
            auctionExists() {
                return !!this.$store.state.currentViewedAuction;
            },
            getUserUrl() {
                return `/user?id=${this.$store.state.currentViewedAuction.user.id}`
            },
            closingTime() {
                var options = {
                    year: 'numeric', month: 'long', day: 'numeric', hour: "numeric"
                };
                let time = new Date(this.$store.state.currentViewedAuction.closingTime);
                return time.toLocaleDateString('de-DE', options)
            }
        },
        methods: {
            bid() {
                // this.userbid = this.buyoutprice;
                alert("You just made a bid on " + this.getViewedAuction.title)
            },
            routeToLogin() {
                this.$router.push({path: 'login'});
            }
        },
        beforeMount() {
            this.$store.dispatch('getCurrentViewedAuction', this.$route.query.id)
        }
    }

</script>

<style scoped>
    #auction {
        /*background-color: whitesmoke;*/
        /*box-shadow: 5px 5px black;*/
        height: 100%;
        max-width: 1000px;
        /*align-self: center;*/
        /*margin: 0;*/

    }

    #auctionimages {
        max-height: 35vh;
        object-fit: cover;
    }

    /*#auctioncontent {*/

    /*}*/
    /*#auctiontitle {*/
    /*    margin-bottom: 2px;*/
    /*    padding: 0;*/
    /*}*/
    /*#auctiondates{*/
    /*    padding-top: 0;*/
    /*    padding-bottom: 5px;*/
    /*    font-size: 12px;*/
    /*    font-weight: 200;*/
    /*}*/
    /*#bidbutton {*/
    /*}*/
    /*#auctionactions {*/
    /*    justify-content: center;*/
    /*}*/
</style>