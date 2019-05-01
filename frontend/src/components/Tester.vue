<template>
    <v-app>
        <v-text-field v-model="message"></v-text-field>
        <v-btn @click="sendMessage">Send</v-btn>
    </v-app>
</template>

<script>
    import socketService from '../services/socket'

    export default {
        name: "Tester",
        data() {
            return {
                message: ""
            }
        },
        methods: {
            sendMessage() {
                socketService().sendMessage({
                    conversationId: 1,
                    username: "john69",
                    textContent: this.message
                });
            }
        }
    }
</script>

<style scoped>

</style>