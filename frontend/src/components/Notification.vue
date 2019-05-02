<template>
    <v-snackbar v-if="type === 'bid'" multi-line top right v-model="showSnackbar" :timeout="timeout"
                color="teal darken-4">
        <div>
            <router-link :to="userLink">{{username}}</router-link>
            placed new bid ({{bidValue}} SEK) to auction "{{auctionTitle}}"
        </div>
        <v-btn flat @click="showAuction">show</v-btn>
        <v-btn flat @click="hideSnackbar">close</v-btn>
    </v-snackbar>
    <v-snackbar v-else multi-line top right v-model="showSnackbar" :timeout="timeout" color="blue-grey darken-4">
        <div>
            <router-link :to="userLink">{{username}}</router-link>
            : {{message}}
        </div>
        <v-btn flat @click="showConversation">show</v-btn>
        <v-btn flat @click="hideSnackbar">close</v-btn>
    </v-snackbar>
</template>

<script>
    export default {
        name: "Notification",
        data() {
            return {
                timeout: 0
            }
        },
        methods: {
            hideSnackbar() {
                this.$store.dispatch("closeNotification");

            },
            showAuction() {
                let currentNotification = this.$store.state.currentNotification;
                if (currentNotification) {
                    this.$router.push("/auction?id=" + currentNotification.auctionId);
                    this.hideSnackbar();
                }

            },
            showConversation() {
                let currentNotification = this.$store.state.currentNotification;
                let id = currentNotification.conversationId;
                if (currentNotification) {
                    this.$router.push({path: '/chat/conversation', query: {id}});
                    this.hideSnackbar();
                }
            }
        },
        computed: {
            showSnackbar() {
                return this.$store.state.showNotification;
            },
            username() {
                let currentNotification = this.$store.state.currentNotification;
                if (currentNotification) {
                    return currentNotification.username;
                } else {
                    return "..."
                }
            },
            bidValue() {
                let currentNotification = this.$store.state.currentNotification;
                if (currentNotification) {
                    return currentNotification.newHighestBid;
                } else {
                    return -1
                }
            },
            auctionTitle() {
                let currentNotification = this.$store.state.currentNotification;
                if (currentNotification) {
                    let title = currentNotification.auctionTitle;
                    if (title && title.length > 53) {
                        return title.sub(0, 50) + "..."
                    }
                    return title
                } else {
                    return "..."
                }
            },
            userLink() {
                let currentNotification = this.$store.state.currentNotification;
                if (currentNotification) {
                    return "/user?id=" + currentNotification.userId
                } else {
                    return "/"
                }
            },
            type() {
                let currentNotification = this.$store.state.currentNotification;
                if (currentNotification) {
                    return currentNotification.type;
                } else {
                    return '';
                }
            },
            message() {
                let currentNotification = this.$store.state.currentNotification;
                if (currentNotification) {
                    return currentNotification.textContent;
                } else {
                    return '';
                }
            }
        }
    }
</script>

<style scoped>

</style>