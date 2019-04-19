<template>
    <v-card id="listitempanel">
        <div id="auctionimgcontainer">
            <img id="auctionimg" :src="auctionimgurl"/>
        </div>

        <div id="auctiontextcontent">
            <h3 id="auctiontitle">{{title}}</h3>
            <span id="auctiondescription">{{description}}</span>
            <h3 id="auctionprice">Current price: {{price}}</h3>
            <h3 id="auctiontime">Time left: {{timeleft}}</h3>

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
        data() {
            return {
                title: "Angry cat",
                description: "Hates hooomans. Also eats babies.",
                auctionimgurl: "https://media.istockphoto.com/photos/angry-cat-picture-id936176546?k=6&m=936176546&s=612x612&w=0&h=OS1vU65NKXVXJEsbsru7x-DG47v42NtEehXsc-WaOQU=",
                price: "$10",
                timeleft: "7 hours",
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