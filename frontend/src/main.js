import Vue from 'vue'
import './plugins/vuetify'
import Vuetify from 'vuetify'
import App from './App.vue'
import router from './router'
import store from './store'

import 'vuetify/dist/vuetify.min.css'

import colors from 'vuetify/es5/util/colors'

Vue.use(Vuetify, {
    theme: {
        primary: colors.amber.lighten1, // #E53935
        secondary: colors.orange.lighten4, // #FFCDD2
        accent: colors.indigo.base // #3F51B5
    }
});

Vue.config.productionTip = false;
Vue.config.devtools = true;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
