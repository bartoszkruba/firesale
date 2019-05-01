<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">

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
                        <v-carousel-item :key="i.filepath" v-for="i in getViewedAuction.images">
                            <v-img :src="i.filepath" alt=""></v-img>
                        </v-carousel-item>
                    </v-carousel>

                    <v-card-text>
                        <h3>Posted By: <b>
                            <router-link :to="getUserUrl">{{getViewedAuction.user.username}}</router-link>
                        </b></h3>
                        <br>
                        <h4>Created At: {{createdTime}} </h4>
                        <h4>Closes At: {{closingTime}}</h4>
                    </v-card-text>
                    <v-card-text>
                        <h3>Description:</h3>
                        <p class="body-2">{{getViewedAuction.description}}</p>
                    </v-card-text>
                    <v-card-text>
                        <h3>Current Price: {{currentPrice}} SEK</h3>
                    </v-card-text>

                    <h2 id="currentbid"
                        v-show="loggedIn"
                        class="subheading,
                            font-weight-bold"></h2>

                    <v-card-text>
                        <h4 v-show="!loggedIn && !closed && !ownAuction">
                            <router-link to="/login">Log in</router-link>
                            to place your bid
                        </h4>
                        <div v-show="loggedIn && !closed && !ownAuction">
                            <h3>Your Bid (SEK): </h3>
                            <v-text-field type="text" @keydown="allowOnlyNumber"
                                          prepend-icon="money" name="Amount" label="Amount"
                                          v-model="bidField" :error-messages="bidFieldError"
                                          @keydown.enter="bid"></v-text-field>
                            <v-btn center color="primary" id="bidbutton" @click="bid">BID</v-btn>
                        </div>
                    </v-card-text>
                </v-card>
                <v-card>
                    <v-toolbar>
                        <v-toolbar-title>Latest Bids:</v-toolbar-title>
                        <v-spacer></v-spacer>
                    </v-toolbar>
                    <template>
                        <v-data-table
                                :headers="headers"
                                :items="viewedAuctionBids"
                                class="elevation-1"
                                :pagination.sync="paginationConfig">
                            <template v-slot:items="bid">
                                <td>{{bid.item.value}} SEK</td>
                                <td>
                                    <router-link :to="getLinkToProfile(bid.item.userId)" style="color: black">
                                        {{bid.item.username}}
                                    </router-link>
                                </td>
                                <td class="text-xs-right">{{formatDate(bid.item.creationTime)}}</td>
                            </template>
                        </v-data-table>
                    </template>
                    <v-btn color="primary" @click="loadMoreBids">Show More</v-btn>
                </v-card>
            </v-container>
        </div>
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
    import bidService from '../services/bid'

    export default {
        name: "Auction",
        data() {
            return {
                bidField: "",
                bidFieldError: "",

                headers: [
                    {text: 'Value: (SEK)', value: 'value'},
                    {text: 'Placed By: ', value: 'placedBy'},
                    {text: 'Time: ', value: 'time', align: 'right'}
                ],
                paginationConfig: {
                    descending: true,
                    rowsPerPage: -1,
                    sortBy: "value",
                }
            }
        },
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
            getUserUrl() {
                return `/user?id=${this.$store.state.currentViewedAuction.user.id}`
            },
            closingTime() {

                var options = {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour12: false,
                    hour: "numeric",
                    minute: "numeric",
                    second: "numeric"
                };
                let time = new Date(this.$store.state.currentViewedAuction.closingTime);

                if (time < new Date()) {
                    return "Closed"
                }
                return time.toLocaleDateString('en-EN', options)
            },
            createdTime() {
                var options = {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour12: false,
                    hour: "numeric",
                    minute: "numeric",
                    second: "numeric"
                };
                let time = new Date(this.$store.state.currentViewedAuction.openedAt);
                return time.toLocaleDateString('en-EN', options)
            },
            currentPrice() {
                return this.$store.state.currentViewedAuction.currentPrice;

                // let highestBid = this.$store.state.currentViewedAuction.highestBid;
                // if (highestBid) {
                //     return highestBid.value;
                // } else {
                //     return this.$store.state.currentViewedAuction.startUpPrice;
                // }
            },
            highestBid() {
                if (this.$store.state.currentViewedAuction) {
                    return this.$store.state.currentViewedAuction.highestBid;
                }
                return null;
            },
            startupPrice() {
                if (this.$store.state.currentViewedAuction) {
                    return this.$store.state.currentViewedAuction.startUpPrice
                } else {
                    return null;
                }
            },
            viewedAuctionBids() {
                return this.$store.state.viewedAuctionBids;
            },
            closed() {
                let time = new Date(this.$store.state.currentViewedAuction.closingTime);

                return time < new Date();
            },
            ownAuction() {
                if (!this.$store.state.currentUser) {
                    return false;
                }
                return this.$store.state.currentViewedAuction.user.id === this.$store.state.currentUser.id;
            }
        },
        methods: {
            async bid() {
                this.bidFieldError = "";
                let currPrice = this.$store.state.currentViewedAuction.highestBid ?
                    this.$store.state.currentViewedAuction.highestBid.value :
                    this.$store.state.currentViewedAuction.startUpPrice;

                if (parseFloat(this.bidField) <= parseFloat(currPrice)) {
                    this.bidFieldError = "Must be higher than current bid"
                } else {
                    let response = await bidService().placeBid(this.$store.state.currentViewedAuction.id,
                        parseFloat(this.bidField));
                    if (response.status === 201) {
                        this.bidField = "";
                        alert("Bid placed")
                    } else {
                        alert("Something went wrong, please refresh site and try again")
                    }
                }
            },
            clearBidFieldError(e) {
                if (e.key.toString() !== "enter") {
                    this.bidFieldError = "";
                }
            },
            allowOnlyNumber(e) {
                this.clearBidFieldError(e);
                let re = /[0-9]|Backspace/;
                if (!e.key.toString().match(re)) {
                    e.preventDefault()
                }
            },
            formatDate(date) {
                let options = {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour12: false,
                    hour: "numeric",
                    minute: "numeric",
                    second: "numeric"
                };
                let time = new Date(date);
                return time.toLocaleDateString('en-EN', options)
            },
            loadMoreBids() {
                this.$store.dispatch("loadBidPage");
            },
            getLinkToProfile(id) {
                return `/user?id=${id}`
            }
        },
        beforeMount() {
            this.$store.dispatch('getCurrentViewedAuction', this.$route.query.id);
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