import Api from '@/services/Api';

export default () => {
    return {
        loadBids(auctionId, page) {
            return Api().get(`/bids/byAuctionId/${auctionId}?page=${page}`);
        }
    }
}