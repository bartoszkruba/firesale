<template>
    <v-container id="chat" style="height: 100vh;">
        <v-layout column fluid>
            <v-container id="top-row" style="height: 100%; padding: 0;" white>
                <v-container id="new-conversation">
                    <v-layout row>
                        <v-text-field id="input-username" label="Username" pa-1></v-text-field>
                        <v-icon medium> chat</v-icon>
                    </v-layout>
                </v-container>
                <v-layout column align-center>
                    <v-container v-show="showConversationList">
                        <v-card id="conversation-list" v-for="conversation in this.getConversations"
                                v-bind:key="conversation.id" color="primary" style="width: 100%;">
                            <h3 class="pa-4" @click="getMessagesInConversation(conversation.id)">
                                {{conversation.members[0].username}} </h3>
                        </v-card>
                    </v-container>
                    <v-icon large @click="toggleConversationList">keyboard_arrow_down</v-icon>
                </v-layout>
            </v-container>
            <v-container id="chat-window" class="pa-0 mt-1 mb-1" v-show="messages.length > 0">
<!--                <v-container id="messages" class="pa-0 pt-2">-->
                    <v-card v-for="message in messages" v-bind:key="message.id"
                            class="pa-1">

                        <h3> {{message.textContent}}</h3>
                        <p class="ma-0"> {{ message.username }} {{message.createdAt}}</p>

                    </v-card>
<!--                </v-container>-->
                    <v-container id="bottom-row" white class="mt-1">
                        <v-layout row>
                            <v-text-field v-model="newMessage" label="Message"></v-text-field>
                            <v-icon>send</v-icon>
                        </v-layout>
                    </v-container>


            </v-container>
        </v-layout>
    </v-container>
</template>

<script>
    import conversationService from "../services/conversationService";

    export default {
        name: "Chat",
        data() {
            return {
                newMessage: '',
                messages: [],
                showConversationList: false,
            }
        },
        methods: {
            async getMessagesInConversation(id) {
                await conversationService.getMessagesInConversation(id).then(response => {
                    this.messages = response.data;
                });
                this.toggleConversationList();
            },
            toggleConversationList() {
                this.showConversationList = !this.showConversationList;
            }
        },
        computed: {
            getConversations() {
                return this.$store.state.conversations;
            },
        },
        beforeMount() {
            this.$store.dispatch('getConversations');
        }
    }
</script>

<style scoped>

</style>