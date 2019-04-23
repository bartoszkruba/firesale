import Api from '@/services/Api';

export default () => {
    return {
        getFilteredAuctions(query) {
            return Api().get(
                '/auctions',
                {
                    params: query
                });
        },
        postNewAuction(auction) {
            return Api().post("/auctions", auction)
        }
    }
}