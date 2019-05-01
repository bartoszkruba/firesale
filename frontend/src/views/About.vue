<template>
            <v-card text-xs-center id="aboutCard">
                <h3 class="headline font-weight-black text-xs-center">About Firesale</h3>
                <v-card-text text-xs-center >
                    <p class="text-xs-center">Adress: <a href="https://goo.gl/maps/x7HKJhayCFRd3ZLj6">Mauna Loa, USA</a></p>
                    <p class="text-xs-center">Phone: <a href="tel:+468-786 40 00">+468-786 40 00</a></p>
                    <p class="text-xs-center">Email: <a href="mailto: info@firesale.com">info@firesale.com</a></p>
                </v-card-text>
            </v-card>
</template>

<script>
    export default {
        name: "About"
    }
</script>

<style scoped>

    #aboutCard{
        align-self: center;
        padding-top: 30px;
        padding-bottom: 10px;
        width: 90%;
        margin-top: 15vh;
        box-shadow: 5px 5px black;
    }
</style>