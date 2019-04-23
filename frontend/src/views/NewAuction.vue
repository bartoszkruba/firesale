<template>
    <v-app class="mt-4">
        <v-content>
            <v-container style="background-color: white; color:black">
                <input type="file" @input="loadImages"
                       style="display: none"
                       ref="image"
                       accept="image/*"
                       multiple="multiple">
                <h1>Create New Auction</h1>
                <v-text-field label="Title" id="title" v-model="title" :error-messages="titleError"></v-text-field>
                <br>
                <v-textarea label="Description" id="description" v-model="description"
                            hint="Describe your product/products" outline
                            :error-messages="descriptionError"></v-textarea>
                <v-datetime-picker label="Closing At:" :datetime="closingTime"
                                   @input="updateDateTime" :error-messages="closingTimeError"></v-datetime-picker>
                <v-text-field type="number" label="Startup Price (SEK)" :error-messages="startupPriceError"
                              v-model="startUpPrice"></v-text-field>
                <v-text-field type="number" label="Buyout Price (SEK)" :error-messages="buyoutPriceError"
                              v-model="buyOutPrice"></v-text-field>
                <v-select :items="categories" label="Category" v-model="category"></v-select>
                <h2>Images: ({{imagesCount}} selected)</h2>
                <v-text-field id="imagepicker" label="Select Images" @click="pickFile"
                              prepend-icon='attach_file'></v-text-field>
                <v-layout align-center justify-center>
                    <v-btn color="primary" @click="postNewAuction">Create Auction</v-btn>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import auctionService from '../services/auctionsService'

    export default {
        name: "NewAuction",
        mounted() {
            this.$store.dispatch("getCategories");
        },
        data() {
            return {
                pickedImages: [],
                title: "",
                description: "",
                closingTime: null,
                startUpPrice: "",
                buyOutPrice: "",
                category: "All",

                titleError: "Field cannot be empty",
                descriptionError: "Field cannot be empty",
                closingTimeError: "Choose date",
                startupPriceError: "Field cannot be empty",
                buyoutPriceError: "Field cannot be empty"
            }
        },
        computed: {
            categories() {
                return this.$store.state.categories
            },
            imagesCount() {
                return this.pickedImages.length;
            }
        },
        methods: {
            pickFile() {
                this.$refs.image.click()
            },
            async postNewAuction() {
                let response = await auctionService().postNewAuction({
                    title: this.title,
                    description: this.description,
                    closingTime: this.closingTime,
                    startUpPrice: parseFloat(this.startUpPrice),
                    buyOutPrice: parseFloat(this.buyOutPrice),
                    category: this.category,
                    images: this.pickedImages
                });

                if (response.status === 201) {
                    console.log(`auction created`);
                } else {
                    console.log(`something went wrong`);
                }
            },
            updateDateTime(e) {
                this.closingTime = e;
            },
            loadImages(e) {
                this.pickedImages = [];
                for (let i = 0; i < e.target.files.length; i++) {
                    let reader = new FileReader();
                    let image = e.target.files[i];
                    reader.readAsDataURL(image);
                    reader.onload = e => {
                        this.pickedImages.push(e.target.result);
                    };
                }
            }
        }
    }
</script>

<style scoped>

</style>