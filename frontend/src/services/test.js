import Api from './Api';

export default {
    test(testString) {
        return Api().get(`/test/${testString}`)
    }
}