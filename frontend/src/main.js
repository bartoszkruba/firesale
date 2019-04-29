import Vue from 'vue'
import './plugins/vuetify'
import Vuetify from 'vuetify'
import App from './App.vue'
import router from './router'
import store from './store'

import DateTimePicker from 'vuetify-datetime-picker';
import infiniteScroll from 'vue-infinite-scroll'
import 'vuetify-datetime-picker/src/stylus/main.styl'
import 'vuetify/dist/vuetify.min.css'

import colors from 'vuetify/es5/util/colors'

Vue.use(infiniteScroll);
Vue.use(DateTimePicker);

Vue.use(Vuetify, {
    theme: {
        primary: colors.amber, // #E53935
        secondary: colors.orange.lighten4, // #FFCDD2
        accent: colors.indigo.base, // #3F51B5
        background: colors.grey.lighten1
    }
});

Vue.config.productionTip = false;
Vue.config.devtools = true;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
