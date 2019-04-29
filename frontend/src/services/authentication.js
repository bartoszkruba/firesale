import Api from './Api';

export default {
    async login(username, password) {
        let axios = Api();
        axios.interceptors.response.use(
            response => response,
            error => {
                const {status} = error.response;
                if (status === 401) {
                    // dispatch(userSignOut());
                }
                return Promise.reject(error);
            }
        );

        let formData = new FormData();
        formData.append("username", username);
        formData.append("password", password);
        try {
            let response = await axios(`/login`, {
                method: 'POST',
                header: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                data: formData
            });
            return response.status === 200;
        } catch (err) {
            return false;
        }
    },

    async logout() {
        let axios = Api();
        try {
            let response = await axios.post("/logout");
            this.$store.commit("setListItemBidFieldSwtich", null);
            return response.status === 200;
        } catch (err) {
            return false;
        }
    },

    async checkIfLoggedIn() {
        try {
            let response = await Api().get("/login/roles");
            return response.status === 200;
        } catch (ex) {
            return false;
        }
    }
}