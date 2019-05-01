import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import Register from './views/Register.vue'
import NewAuction from './views/NewAuction.vue'
import Auction from "./views/Auction";
import About from "./views/About";
import UserProfile from "./views/UserProfile";
import Profile from "./views/Profile";
import Chat from "./views/Chat";


Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        {
            path: '/auctions',
            name: 'filteredActions',
            component: Home,
        },
        {
            path: '/register',
            name: 'register',
            component: Register
        },
        {
            path: '/new_auction',
            name: 'newAuction',
            component: NewAuction
        },
        {
            path: '/auction',
            name: 'Auction',
            component: Auction
        },
        {
            path: '/about',
            name: 'About',
            component: About
        },
        {
            path: '/profile',
            name: 'Profile',
            component: Profile
        },
        {
            path: '/user',
            name: 'UserProfile',
            component: UserProfile
        },
        {
            path: '/chat',
            name: 'Chat',
            component: Chat
        }
    ]
})
