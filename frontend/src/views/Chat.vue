<template>
    <v-container>
        <v-container id="new-conversation"> new </v-container>
        <v-container id="conversation-list" v-for="conversation in this.$store.state.conversations" v-bind:key="conversation.id">
                        <v-container>
                            <h5>  {{conversation.username}} </h5>
                        </v-container>
        </v-container>
    </v-container>
</template>

<script>
    export default {
        name: "Chat",
        beforeMount(){
            this.$store.dispatch('getConversations');
        }
    }
</script>

<style scoped>

</style>