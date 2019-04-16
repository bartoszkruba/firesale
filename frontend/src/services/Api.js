import axios from 'axios';

const baseUrl = `http://${window.location.host}/api`;

export default () => {

    return axios.create({
        baseURL: baseUrl
    })
}