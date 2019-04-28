<template>
    <v-card id="listitempanel">
        <div id="auctionimgcontainer">
            <span v-if="imagesExist">
                <router-link :to="auctionLink">
                    <v-img id="auctionimg" :src="auction.images[0].filepath" alt="no image"/>
                </router-link>
            </span>
            <span v-else>no image</span>
        </div>

        <div id="auctiontextcontent">
            <h1 id="auctiontitle">
                <router-link :to="auctionLink" style="color: black">
                    {{auction.title}} <span v-if="closed">(Closed)</span>
                </router-link>
            </h1>
            <span id="auctiondescription">{{description}}</span>
            <h3 id="auctionprice">Current price: {{auction.startUpPrice}} SEK</h3>
            <h3 id="auctiontime">Ends at: {{closingTime}}</h3>

        </div>
        <v-btn id="buybutton"
               color="primary"
               absolute bottom right fab>
            <v-icon>attach_money</v-icon>
        </v-btn>
    </v-card>
</template>

<script>
    export default {
        name: "AuctionListItem",
        props: {
            auction: {},
        },
        methods: {},
        computed: {
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
                let description = this.auction.description.substring(0, 200);
                if (this.auction.description.length > 200) {
                    description = description + "...";
                }
                return description
            },
            closed() {
                let time = new Date(this.auction.closingTime);

                return time < new Date();
            }
        }
    }
</script>

<style scoped>

    #listitempanel {
        box-sizing: border-box;
        display: flex;
        flex-direction: row;
        background-color: whitesmoke;
        box-shadow: 5px 5px black;
        height: 120px;
        width: 90%;
        margin-top: 20px;
        margin-bottom: 20px;
        margin-left: 5%;
        margin-right: 5%;
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
    }

    #auctiontitle {
        font-family: "Segoe UI";
        text-align: left;
        font-size: medium;
    }

    #auctiondescription {
        font-family: "Segoe UI";
        text-align: left;
        font-size: small;
        padding-bottom: 5px;
    }

    #auctionprice {
        font-family: "Segoe fUI";
        text-align: left;
        font-size: small;
        padding-bottom: 5px;
    }

    #auctiontime {
        font-family: "Segoe UI";
        text-align: left;
        font-size: small;
        padding-bottom: 5px;
    }

    #buybutton {
        color: black;
        margin-bottom: 35px;
    }


</style>