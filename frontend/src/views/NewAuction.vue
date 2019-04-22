<template>
    <v-app class="mt-4">
        <v-content>
            <v-container style="background-color: white; color:black">
                <input type="file"
                       style="display: none"
                       ref="image"
                       accept="image/*">
                <h1>Create New Auction</h1>
                <v-text-field label="Title" id="title"></v-text-field>
                <v-textarea label="Description" id="description"
                            hint="Describe your product/products" outline></v-textarea>
                <v-datetime-picker label="Closing At:"></v-datetime-picker>
                <v-text-field type="number" label="Startup Price (SEK)"></v-text-field>
                <v-text-field type="number" label="Buyout Price (SEK)"></v-text-field>
                <v-select :items="categories" label="Category"></v-select>
                <h2>Images:</h2>
                <v-text-field id="imagepicker" label="Select Images" @click="pickFile"
                              v-model="pickedImage" prepend-icon='attach_file'></v-text-field>
                <v-layout align-center justify-center>
                    <v-btn color="primary">Create Auction</v-btn>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    export default {
        name: "NewAuction",
        data() {
            return {
                pickedImage: ""
            }
        },
        computed: {
            categories() {
                return this.$store.state.categories;
            }
        },
        methods: {
            pickFile() {
                this.$refs.image.click()
            }
        }
    }
</script>

<style scoped>

</style>