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
                <v-text-field label="Title" id="title" v-model="title"></v-text-field>
                <v-textarea label="Description" id="description" v-model="description"
                            hint="Describe your product/products" outline></v-textarea>
                <v-datetime-picker label="Closing At:" :datetime="closingTime"
                                   @input="updateDateTime"></v-datetime-picker>
                <v-text-field type="number" label="Startup Price (SEK)"
                              v-model="startUpPrice"></v-text-field>
                <v-text-field type="number" label="Buyout Price (SEK)"
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
            postNewAuction() {
                console.log(this.title);
                console.log(this.description);
                console.log(this.startUpPrice);
                console.log(this.buyOutPrice);
                console.log(this.category);
                console.log(this.closingTime);
                console.log(this.pickedImages);
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