import Api from '@/services/Api';

export default () => {
    return {
        getFilteredAuctions(query) {
            return Api().get('/auctions', {  params: query });
        },
        postNewAuction(auction) {
            return Api().post("/auctions", auction)
        },
        getAuctionById(id){
            return Api().get("/auctions/id/" + id)
        },
        getAuctionByUserId(id){
            return Api().get("/auctions/userid/" + id)
        },

    }
}