<template>
        <v-card  id="auction" >
            <v-img id="auctionimages" :src="mainimage"></v-img>
            <v-container id="auctioncontent">
                <v-layout>
                    <v-flex>
                        <v-card-title id="auctiontitle"><h1 class="headline, font-weight-bold">{{title}}</h1></v-card-title>
                        <v-card-text id="auctiondates"><span class="caption">Auction time: {{openedAt}} - {{closingTime}}</span></v-card-text>
                        <v-card-text><p class="body-2">{{description}}</p></v-card-text>

                        <h2 id="currentprice" align="center" class="subheading">Current price: {{startupprice}}</h2>
                        <h2 id="currentbid" align="center" class="subheading, , font-weight-bold">Your bid: {{buyoutprice}}</h2>

                        <v-slider
                                :min="startUpPrice"
                                id="priceslider"
                                v-model="buyoutprice"
                        >
                        </v-slider>

                        <v-card-actions >
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
        data() {
            return {
                id: 1,
                title: 'Just another angry cat',
                mainimage: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbozarqhYwvS-OSbLzKxxN94AWICbxlYXK4W9io5Q6pS0G-3bjkw',
                description: "Evil cat, killed it's owner. Probably cursed or a wizard in cats-form or something...",
                openedAt: '2019-03-01',
                closingTime: '2019-04-30',
                startupprice: 1,
                buyoutprice: 20,
                status: 'active',

            }
        },
        /*props: {
            id: Number,
            title: String,
            description: String,
            openedAt: String,
            closingTime: String,
            startUpPrice: Number,
            buyOutPrice: Number,
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
</style>