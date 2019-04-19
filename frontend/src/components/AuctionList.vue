<template>
    <div>
        <AuctionListItem
                v-for="auction in auctions"
                v-bind="auction"
            >

        </AuctionListItem>

    </div>
</template>

<script>
    import AuctionListItem from '../components/AuctionListItem.vue'

    export default {
        namne: "AuctionList",
        components: {
            AuctionListItem,
        },
        data() { return {
            auctions: [
                {
                    "id": 1,
                    "title": 'Angry cat',
                    "description": 'Hates hoomans, eats babies.',
                    "openedAt": '20190328',
                    "closingTime": '20190420',
                    "startUpPrice": 1,
                    "buyOutPrice": 20,
                    "status": 'sold',
                    "mainimage": "https://media.istockphoto.com/photos/angry-cat-picture-id936176546?k=6&m=936176546&s=612x612&w=0&h=OS1vU65NKXVXJEsbsru7x-DG47v42NtEehXsc-WaOQU=",
                },
                {
                    "id": 2,
                    "title": 'Angrier cat',
                    "description": 'Hates hoomans, eats babies. Killed first buyer.',
                    "openedAt": '20190415',
                    "closingTime": '20190510',
                    "startUpPrice": 2,
                    "buyOutPrice": 10,
                    "status": 'active',
                    "mainimage": "https://static.boredpanda.com/blog/wp-content/uploads/2016/02/japanese-grumpy-cat-angry-koyuki-moflicious-22.jpg",
                },
                {
                    "id": 3,
                    "title": 'Evilest cat',
                    "description": 'Hates hoomans, kills small dogs. Eats them.',
                    "openedAt": '20190416',
                    "closingTime": '20190420',
                    "startUpPrice": 50,
                    "buyOutPrice": 100,
                    "status": 'active',
                    "mainimage": "https://external-preview.redd.it/aNZtZ4nAUo3D4LmflEeDhXIULjjlHUHaJQhmheeSmoc.jpg?auto=webp&s=c5b256ddcd6cf080de277e39153c50cc77b222a6",
                },
                {
                    "id": 4,
                    "title": 'Cute cat',
                    "description": 'Unfortunately, also hates hoomans, snuggles before bite.',
                    "openedAt": '20190416',
                    "closingTime": '20190420',
                    "startUpPrice": 50,
                    "buyOutPrice": 100,
                    "status": 'active',
                    "mainimage": "https://images.unsplash.com/photo-1547565393-1b180d53d82a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",
                }

            ]
            }
        }
    }


</script>

<style scoped>

</style>