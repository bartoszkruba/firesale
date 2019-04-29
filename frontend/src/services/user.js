import Api from './Api'

export default {
    async registerAccount(accountData) {
        return await Api().post("/user", accountData)
    }
}