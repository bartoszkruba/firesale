<template>
    <v-container>
        <v-layout
                text-xs-center
                wrap>
            <v-flex xs12>
                <v-img
                        :src="require('../assets/logoNoName.png')"
                        class="my-3"
                        contain
                        height="75"></v-img>
                <v-img
                        :src="require('../assets/logoName.png')"
                        class="my-3"
                        contain
                        height="30"></v-img>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>

  export default {
      name: 'Logo',
        data() {
        return {}
},
  }
</script>

<style>

</style>
