import Api from '@/services/Api';

export default () => {
    return {
        getCategories(params) {
            return Api().get("/category");
        }
    }
}