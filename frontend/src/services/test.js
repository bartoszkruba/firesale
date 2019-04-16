import Api from './Api';

export default {
    test(testString) {

        let formData = new FormData();
        formData.append("username", "john69");
        formData.append("password", "password1234");

        return Api()(`/login`, {
            method: 'POST',
            header: {
                'Content-Type': 'application/x-www-form-urlencoded',
                // 'Access-Control-Allow-Origin': '*'
            },
            data: formData
        })
    }
}