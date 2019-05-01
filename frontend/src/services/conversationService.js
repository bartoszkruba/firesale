import Api from './Api'

export default {
    async getConversations(){
        return await Api().get('/conversation/my_conversations');
    },
    async getMessagesInConversation(id){
        return await Api().get('/conversation/id/' + id);
    }
}