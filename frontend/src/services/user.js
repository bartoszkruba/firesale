import Api from './Api'

export default {
    async registerAccount(accountData) {
        return await Api().post("/username", accountData)
    },
     getUserById(user){
        return Api().get('/user/' + user.id)
    }
}