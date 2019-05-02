<template>
    <div>
        <v-layout
                justify-center
                wrap
        >
        <v-card class="listitempanel">
            <div id="auctionimgcontainer">
            <span v-if="imagesExist">
                <router-link :to="auctionLink">
                    <v-img id="auctionimg" :src="auction.images[0].filepath" alt="no image"/>
                </router-link>
            </span>
                <span v-else>no image</span>
            </div>

            <div id="auctiontextcontent" class="text-truncate">
                <h1 id="auctiontitle" class="title font-weight-black">
                    <router-link :to="auctionLink" style="color: black">
                        {{auction.title}} <span v-if="closed">(Closed)</span>
                    </router-link>
                </h1>
                <p id="auctiondescription" class="body-1 text-truncate">{{description}}</p>
                <h3 id="auctionprice" class="body-2">Current price: {{currentPrice}} SEK</h3>
                <h3 id="auctiontime" class="body-2">Ends at: {{closingTime}}</h3>
            </div>
            <v-btn id="buybutton"
                   color="primary"
                   absolute bottom right fab
                   v-if="!closed && loggedIn && !ownAuction"
                   @click="switchBidBar">
                <v-icon>attach_money</v-icon>
            </v-btn>
        </v-card>
        <v-card id="bid_panel"
                v-if="showBidBar"
                justify-center
                wrap
        >
            <v-flex>

            <v-text-field name="Amount (SEK)" label="Amount (SEK)" @keydown="allowOnlyNumbers"
                          @keydown.enter="bid" :error-messages="bidFieldError" v-model="bidField"></v-text-field>

                <v-card-actions>
                    <v-btn color="primary" @click="bid">Bid</v-btn>
                </v-card-actions>

            </v-flex>
        </v-card>
        </v-layout>
    </div>
</template>

<script>
    import bidService from '../services/bid'

    export default {
        data() {
            return {
                bidField: "",
                bidFieldError: ""
            }
        },
        name: "AuctionListItem",
        props: {
            auction: {},
        },
        methods: {
            async bid() {
                this.bidFieldError = "";
                let currPrice = this.auction.highestBid ?
                    this.auction.highestBid.value :
                    this.auction.startUpPrice;

                if (this.bidField === null || this.bidField === "") {
                    this.bidFieldError = "Must be higher than current bid"
                } else if (parseFloat(this.bidField) <= parseFloat(currPrice)) {
                    this.bidFieldError = "Must be higher than current bid"
                } else {
                    let response = await bidService().placeBid(this.auction.id,
                        parseFloat(this.bidField));

                    if (response.status === 201) {
                        this.bidField = "";
                        alert("Bid placed")
                        this.switchBidBar()
                    } else {
                        alert("Something went wrong, please refresh site and try again")
                    }
                }
            },
            clearBidFieldError(e) {
                if (e.key !== "Enter") {
                    this.bidFieldError = "";
                }
            },
            switchBidBar() {
                if (this.$store.state.listItemBidFieldSwitch === this.auction.id) {
                    this.$store.commit("setListItemBidFieldSwtich", false);
                } else {
                    this.$store.commit("setListItemBidFieldSwtich", this.auction.id)
                }
            },
            allowOnlyNumbers(e) {
                this.clearBidFieldError(e);
                this.bidFieldError = "";
                let re = /[0-9]|Backspace/;
                if (!e.key.toString().match(re)) {
                    e.preventDefault()
                }
            }
        },
        computed: {
            loggedIn() {
                return this.$store.state.loggedIn;
            },
            imagesExist() {
                return this.auction.images.length > 0;
            },
            closingTime() {

                let time = new Date(this.auction.closingTime);

                var options = {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour12: false,
                    hour: "numeric",
                    minute: "numeric",
                    second: "numeric"
                };

                return time.toLocaleDateString('en-EN', options)
            },
            currentPrice() {
                let highestBid = this.auction.highestBid;
                if (highestBid) {
                    return highestBid.value;
                } else {
                    return this.auction.startUpPrice;
                }
            },
            auctionLink() {
                return `/auction?id=${this.auction.id}`
            },
            description() {
                let description = this.auction.description.substring(0, 50);
                if (this.auction.description.length > 50) {
                    description = description + "...";
                }
                return description
            },
            closed() {
                let time = new Date(this.auction.closingTime);

                return time < new Date();
            },
            showBidBar() {
                return this.$store.state.listItemBidFieldSwitch === this.auction.id;
            },
            ownAuction() {
                return this.auction.user.id === this.$store.state.currentUser.id;
            }
        }
    }
</script>

<style scoped>

    .listitempanel {
        box-sizing: border-box;
        display: flex;
        flex-direction: row;
        background-color: whitesmoke;
        box-shadow: 5px 5px black;
        height: 120px;
        width: 90%;
        max-width: 500px;
        margin-top: 20px;
        /*margin-bottom: 20px;*/
        margin-left: 5%;
        margin-right: 5%;
        padding-right: 10px;
    }

    #bid_panel {
        margin-left: 30%;
        margin-right: 30%;
        max-width: 500px;

    }

    #auctionimgcontainer {
        height: 100%;
        width: 100%;
        max-width: 25%;
    }

    #auctionimg {
        object-fit: cover;
        height: 100%;
        width: auto;
        max-width: 100%;
    }

    #auctiontextcontent {
        display: flex;
        flex-direction: column;
        padding: 5px;
        margin-left: 15px;
    }

    #auctiontitle {
        text-align: left;
        margin-top: 10px;
    }

    #auctiondescription {
        text-align: left;
        font-size: small;
        padding-top: 5px;
    }

    #auctionprice {
        text-align: left;
        font-size: small;
        padding-bottom: 5px;
    }

    #auctiontime {
        text-align: left;
        padding-bottom: 5px;
    }

    #buybutton {
        color: black;
        margin-bottom: 15px;
    }

    #bid_panel{
        padding: 15px;

        box-shadow: 5px 5px black;
        width: 90%;
    }


</style>