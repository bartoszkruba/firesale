import Api from './Api';

export default {
     test(testString) {

        let formData = new FormData();
        formData.append("username", "john69");
        formData.append("password", "password1234");

        return Api().post("/user", {
            "username": "john69",
            "password": "password1234",
            "email": "email@email.com",
            "address": "some random address",
            "phoneNumber": "123214242",
            "firstName": "John",
            "lastName": "sdasads"
        })
    }
}