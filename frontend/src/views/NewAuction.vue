<template>
    <!--    <v-app class="mt-4">-->
    <v-content>
        <v-container>
            <v-card>
                <v-toolbar>
                    <v-layout align-center justify-center>
                        <v-toolbar-title>Create Your account</v-toolbar-title>
                    </v-layout>
                </v-toolbar>
                <v-container>
                    <input type="file" @input="loadImages"
                           style="display: none"
                           ref="image"
                           accept="image/*"
                           multiple="multiple">
                    <v-alert :value="showAlert"
                             type="error">Something went wrong on server
                    </v-alert>
                    <br>
                    <v-text-field label="Title" id="title" v-model="title"
                                  :error-messages="titleError"
                                  @keydown="clearTitleError"
                                  @keydown.enter="postNewAuction"></v-text-field>
                    <br>
                    <v-textarea label="Description" id="description" v-model="description"
                                hint="Describe your product/products" outline
                                :error-messages="descriptionError"
                                @keydown="clearDescriptionError"
                                @keydown.enter="postNewAuction"></v-textarea>
                    <v-datetime-picker label="Closing At:" :datetime="closingTime"
                                       @input="updateDateTime"
                                       :error-messages="closingTimeError"></v-datetime-picker>
                    <v-text-field type="number" label="Startup Price (SEK)"
                                  :error-messages="startupPriceError"
                                  v-model="startUpPrice"
                                  @keydown="clearStartupPriceError"
                                  @keydown.enter="postNewAuction"></v-text-field>
                    <v-text-field type="number" label="Buyout Price (SEK)"
                                  :error-messages="buyoutPriceError"
                                  v-model="buyOutPrice"
                                  @keydown="clearBuyoutPriceError"
                                  @keydown.enter="postNewAuction"></v-text-field>
                    <v-select :items="categories" label="Category" v-model="category"></v-select>
                    <h2>Images: ({{imagesCount}} selected)</h2>
                    <v-text-field id="imagepicker" label="Select Images" @click="pickFile"
                                  prepend-icon='attach_file'></v-text-field>
                    <v-layout align-center justify-center>
                        <v-btn color="primary"
                               @click="postNewAuction">Create Auction
                        </v-btn>
                    </v-layout>
                </v-container>
            </v-card>
        </v-container>
    </v-content>
    <!--    </v-app>-->
</template>

<script>
    import auctionService from '../services/auctionsService'

    export default {
        name: "NewAuction",
        mounted() {
            // if (!this.$store.state.loggedIn) {
            //     this.$router.push("/login")
            // }
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

                titleError: "",
                descriptionError: "",
                closingTimeError: "",
                startupPriceError: "",
                buyoutPriceError: "",

                showAlert: false
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
                this.showAlert = false;
                if (this.validateFields()) {
                    try {
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
                            this.$router.push("/")
                        } else if (response.status === 401) {
                            this.$router.push("/login")
                        }
                    } catch (e) {
                        this.showAlert = true;
                    }
                }
            },
            updateDateTime(e) {
                this.closingTime = e;
                if (!this.validateClosingTime()) {
                    this.closingTime = null;
                }
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
            },
            validateFields() {
                return this.validateTitle() && this.validateDescription() &&
                    this.validateClosingTime() && this.validateStartupPrice() &&
                    this.validateBuytOutPrice();
            },
            validateTitle() {
                this.title = this.title.trim();
                this.titleError = this.title === "" ? "Title cannot be empty" : "";
                return this.titleError === "";
            },
            validateDescription() {
                this.description = this.description.trim();
                this.descriptionError = this.description === "" ? "Description cannot be empty" : "";
                return this.descriptionError === "";
            },
            validateClosingTime() {
                this.closingTimeError = "";
                let currentdatetime = new Date();
                if (this.closingTime === null) {
                    this.closingTimeError = "Pick closing time";
                    return false;
                } else if (this.closingTime < currentdatetime) {
                    this.closingTimeError = "Pick future date";
                    return false;
                }
                return true;
            },
            validateStartupPrice() {
                this.startupPriceError = "";
                if (this.startUpPrice === null) {
                    this.startupPriceError = "Choose Startup price";
                    return false;
                } else if (this.startUpPrice === "") {
                    this.startupPriceError = "Choose Startup price";
                    return false;
                } else if (parseFloat(this.startUpPrice) < 0) {
                    this.startupPriceError = "Startup price needs to be bigger than 0";
                    return false;
                }
                return true;
            },
            validateBuytOutPrice() {
                this.buyoutPriceError = "";

                if (this.buyOutPrice === null) {
                    this.buyoutPriceError = "Choose buyout price";
                    return false;
                } else if (this.buyOutPrice === "") {
                    this.buyoutPriceError = "Choose buyout price";
                    return false;
                } else if (parseFloat(this.buyOutPrice) < parseFloat(this.startUpPrice)) {
                    this.buyoutPriceError = "Buyout price need to be bigger than startup price";
                    return false;
                }
                return true;
            },
            clearTitleError(e) {
                if (e.key !== "enter") {
                    this.titleError = "";
                }
            },
            clearDescriptionError(e) {
                if (e.key !== "enter") {
                    this.descriptionError = "";
                }
            },
            clearStartupPriceError(e) {
                if (e.key !== "enter") {
                    this.startupPriceError = "";
                }
            },
            clearBuyoutPriceError(e) {
                if (e.key !== "enter") {
                    this.buyoutPriceError = "";
                }
            }
        },
        beforeMount() {
            if (!this.$store.state.loggedIn) {
                this.$router.push("/");
            }
        }
    }
</script>

<style scoped>

</style>