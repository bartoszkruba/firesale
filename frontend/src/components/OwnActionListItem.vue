<template>
    <div>
        <h1 id="test"> HEJJEJKJ D D </h1>
        <v-layout justify-center wrap>
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
            </v-card>
        </v-layout>
    </div>
</template>

<script>
    export default {
        name: "OwnActionListItem",
        data(){
          return;
        }
        ,props: {
            auction: {},
        },methods:{

        },computed:{
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
            ownAuction() {
                return this.auction.user.id === this.$store.state.currentUser.id;
            }
        },
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
        margin-top: 20px;
        /*margin-bottom: 20px;*/
        margin-left: 5%;
        margin-right: 5%;
        padding-right: 10px;
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
        text-align: left;
        margin-top: 10px;
    }

    #auctiondescription {
        text-align: left;
        font-size: small;
        padding-bottom: 5px;
        margin-top: 10px;
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


    #test{
        background-color: white;
    }
    *{
        background-color: white;
    }
</style>