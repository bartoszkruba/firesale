import Api from '@/services/Api';

export default () => {
    return {
         getFilteredAuctions(params) {
             return Api().get(params);
        }
    }
}