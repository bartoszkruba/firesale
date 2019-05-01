<template>
    <v-container id="chat" style="height: 100vh;">
        <v-layout column fluid>
            <v-container id="left-col" style="height: 100%;">
                <v-container id="new-conversation">
                    <v-text-field id="input-username" label="Username"></v-text-field>

                </v-container>
                <v-card id="conversation-list" v-for="conversation in this.getConversations" v-bind:key="conversation.id" color="primary">
                        <h3 class="pa-4" @click="getMessagesInConversation(conversation.id)">  {{conversation.members[0].username}} </h3>
                </v-card>
            </v-container>
            <v-container id="chat-window">
                <v-container id="messages">
                    <v-card v-for="message in messages" v-bind:key="message.id" v-show="messages.length > 0">
                        <h3> {{message.textContent}}</h3>
                        <p text-align="right"> {{ message.sender }} {{message.createdAt}}</p>
                    </v-card>
                </v-container>
                <v-container id="bottom-row"    >
                    <v-layout row>
                    <v-text-field v-model="newMessage"></v-text-field>
                    <v-btn><v-icon>plus</v-icon></v-btn>
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
        data(){
            return {
                newMessage: '',
                messages: [],
            }
        },
        methods: {
            async getMessagesInConversation(id){
                await conversationService.getMessagesInConversation(id).then(response => {
                    this.messages = response.data;
                });
                console.log(this.messages);
            }
        },
        computed: {
            getConversations(){
                return this.$store.state.conversations;
            },
        },
        beforeMount(){
            this.$store.dispatch('getConversations');
        }
    }
</script>

<style scoped>
    #left-col{
        background-color: white;
    }
</style>