<template>
    <v-card id="listitempanel">
        <div id="auctionimgcontainer">
            <img id="auctionimg" :src="mainimage"></img>
        </div>

        <div id="auctiontextcontent">
            <h3 id="auctiontitle">{{title}}</h3>
            <span id="auctiondescription">{{description}}</span>
            <h3 id="auctionprice">Current price: {{startUpPrice}}</h3>
            <h3 id="auctiontime">Ends at: {{closingTime}}</h3>

        </div>
        <v-btn id="buybutton"
               color="primary"
               absolute
               bottom
               right
               fab

        >
            <v-icon>attach_money</v-icon>
        </v-btn>

    </v-card>
</template>

<script>
    export default {
        name: "AuctionListItem",
        props: {
            id: Number,
            title: String,
            description: String,
            openedAt: String,
            closingTime: String,
            startUpPrice: Number,
            buyOutPrice: Number,
            status: String,
            mainimage: String,
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
        width: auto;
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
        font-family: "Segoe UI";
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
        margin-bottom: 10px;
    }


</style>