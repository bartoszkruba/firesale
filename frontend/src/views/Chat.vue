<template>
    <v-container id="chat" style="max-height: 100vh;" class="pa-2 scroll-y">
        <v-layout column fluid>
            <v-container id="top-row" style="height: 100%; padding: 0;" white>
                <v-layout column align-center>
                    <h2>Conversations </h2>
                    <v-container v-show="showConversationList" class="pa-1">
                        <v-container id="new-conversation" class="pa-2">
                            <v-layout row justify-center>
                                <v-text-field v-model="newChatUsername" id="input-username" label="New conversation"
                                              class="pa-0" :error-messages="newChatError"
                                              @keydown.enter="startChat"></v-text-field>
                                <v-icon medium @click="startChat"> chat</v-icon>
                            </v-layout>
                        </v-container>
                        <v-card id="conversation-list" v-for="conversation in this.getConversations"
                                @click="getMessagesInConversation(conversation)"
                                v-bind:key="conversation.id" color="primary">
                            <h4 class="pa-2 mt-1">
                                {{ getSenderUsername(conversation.members) }}
                            </h4>
                        </v-card>
                    </v-container>
                    <v-icon large @click="toggleConversationList" v-show="!showConversationList">keyboard_arrow_down
                    </v-icon>
                    <v-icon large @click="toggleConversationList" v-show="showConversationList">keyboard_arrow_up
                    </v-icon>
                </v-layout>
            </v-container>
            <v-container v-show="loading" class="text-xs-center">
                <v-progress-circular
                        :size="50"
                        color="primary"
                        indeterminate
                ></v-progress-circular>
            </v-container>
            <v-container id="chat-window" class="pa-0 mt-1 mb-1" v-show="hasConversations">
                <v-card>Chatting with {{getSenderUsername(this.currentConversation.members)}}</v-card>
                <v-container id="messages" class="scroll-y pa-0 mt-1 mb-1"
                             style="max-height: 58vh; max-width: 100vw;" background>
                    <v-layout
                            column
                            align-center
                            justify-center
                    >
                        <v-card class="pa-1 pl-2 pr-2" style="width: 100%" v-show="messages.length === 0"><p
                                class="mb-0 subheading font-weight-bold"> Say hi! </p></v-card>
                        <v-card v-for="message in messages" v-bind:key="message.id" class="pa-1 pl-2 pr-2 mt-1"
                                style="min-width: 100%; max-width: 100%" :id="'msg' + message.id"
                                v-bind:class="{'text-xs-right': message.username === $store.state.currentUser.username}">
                            <p class="mb-0 caption">{{ message.username }}</p>
                            <p class="mb-0 subheading font-weight-bold" style="word-wrap: break-word;">
                                {{message.textContent}}</p>
                            <p class="mb-0 caption">{{ getTimestamp(message.createdAt) }}</p>
                        </v-card>
                    </v-layout>
                </v-container>
                <v-container id="bottom-row" white class="mt-1 pa-2">
                    <v-layout row>
                        <v-text-field v-model="newMessage"
                                      label="Message"
                                      counter="100"
                                      :rules="rules"
                                      class="pa-0"
                                      @keydown.enter="sendMessage">
                        </v-text-field>
                        <v-icon @click="sendMessage">send</v-icon>
                    </v-layout>
                </v-container>


            </v-container>
        </v-layout>
    </v-container>
</template>

<script>
    import conversationService from "../services/conversationService";
    import socketService from '../services/socket'

    export default {
        name: "Chat",
        data() {
            return {
                hasConversations: false,
                loading: true,
                newMessage: '',
                newChatUsername: '',
                messages: [],
                showConversationList: false,
                currentConversation: {},
                rules: [
                    v => v != null && v.length <= 100 || 'Max 100 characters'
                ],
                newChatError: '',
            }
        },
        methods: {
            startChat() {
                if (this.newChatUsername !== undefined) {
                    let conversationExists = true;
                    let conversations = this.getConversations;
                    for (let con of conversations) {
                        if (this.newChatUsername === this.getSenderUsername(con.members) || this.newChatUsername === this.$store.state.currentUser.username) {
                            this.newChatError = 'Conversation already exists';
                            conversationExists = true;
                        } else {
                            conversationExists = false;
                        }
                    }
                    if (!conversationExists || conversations.length === 0) {
                        this.newChatError = '';
                        this.newConversation(this.newChatUsername.trim());
                        this.newChatUsername = '';

                    }
                }
            },
            async getMessagesInConversation(conversation) {
                await conversationService.getMessagesInConversation(conversation.id).then(response => {
                    this.$store.commit('setMessages', response.data);
                    this.messages = this.$store.state.messages;
                });
                this.currentConversation = conversation;
                let id = conversation.id;
                this.$store.commit('setCurrentConversationId', id);
                this.showConversationList = false;
                this.$router.push({path: '/chat/conversation', query: {id}});


            },
            async loadMessagesOnEnter(id) {
                let conversations = this.getConversations;
                if (conversations.length > 0) {
                    this.currentConversation = conversations.find(con => con.id === id);
                    if (this.currentConversation !== undefined) {
                        this.$store.commit('setCurrentConversationId', this.currentConversation.id);
                        await conversationService.getMessagesInConversation(id).then(response => {
                            this.$store.commit('setMessages', response.data);
                            this.messages = this.$store.state.messages;
                        });
                        this.loading = false;
                        this.hasConversations = true;
                    }
                }

            },
            toggleConversationList() {
                this.showConversationList = !this.showConversationList;
            },
            async sendMessage() {
                if (this.newMessage.length > 0 && this.newMessage.length <= 100) {
                    let message = {
                        conversationId: this.$route.query.id,
                        username: this.$store.state.currentUser.username,
                        textContent: this.newMessage
                    };
                    socketService().sendMessage(message);
                    this.newMessage = '';


                } else {
                    console.log("Error in message length")
                }
            },
            getTimestamp(timestamp) {
                let time = new Date(timestamp);

                let options = {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric',
                    hour12: false,
                    hour: "numeric",
                    minute: "numeric",
                    second: "numeric"
                };

                return time.toLocaleDateString('en-EN', options)
            },
            getSenderUsername(members) {
                if (members != null && this.$store.state.currentUser !== null) {
                    return this.$store.state.currentUser.id !== members[0].id ? members[0].username : members[1].username;
                } else {
                    return ''
                }
            },
            async newConversation(username) {
                await conversationService.newConversation(username).then(response => {
                    let conversations = this.getConversations;
                    if (!conversations.find(con => con.id === response.data.id)) {
                        this.$store.commit('addConversation', response.data);
                        this.showConversationList = false;
                        let id = response.data.id;
                        this.$router.push({path: '/chat/conversation', query: {id}});
                    }
                }).catch((error) => {
                    if (error.response.status === 400) {
                        this.newChatError = 'User does not exist!'
                    }
                })
            }
        },
        computed: {
            getConversations() {
                return this.$store.state.conversations;
            }
        },
        async beforeMount() {
            this.$store.dispatch('getConversations');
            if (this.$store.state.loggedIn) {
                let conversations = await conversationService.getConversations().then(response => response.data);
                if (this.$route.query.id !== undefined && conversations.length > 0) {
                    this.hasConversations = true;
                    this.currentConversation = conversations.find(con => con.id === this.$route.query.id);
                    this.loadMessagesOnEnter(this.$route.query.id)
                } else if (conversations.length > 0) {
                    this.showConversationList = true;
                    this.loading = false;
                } else {
                    this.showConversationList = true;
                }
                setTimeout(() => {
                    this.loading = false;
                }, 1500);
            } else {
                this.$router.push({path: '/login'});
            }
        },
        watch: {
            $route(to, from) {
                this.loadMessagesOnEnter(to.query.id);
            }

        }
    }
</script>

<style scoped>
    #chat {
        max-width: 500px;
    }
</style>