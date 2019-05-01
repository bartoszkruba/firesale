<template>
    <v-container >
        <v-container id="new-conversation"> new </v-container>
        <v-card id="conversation-list" v-for="conversation in this.$store.state.conversations" v-bind:key="conversation.id" color="secondary">
                <h3 pa-4>  {{conversation.members[0].username}} </h3>
        </v-card>
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