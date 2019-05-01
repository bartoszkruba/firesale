import Api from './Api'

export default {
    async registerAccount(accountData) {
        return await Api().post("/user", accountData)
    },
     getUserById(user){
        return Api().get('/user/' + user.id)
    },
    async updateAccount(accountData) {
        return await Api().put("/user", accountData)
    },
    async getConversations(){
        return await Api().get('/conversation/my_conversations');
    }
}