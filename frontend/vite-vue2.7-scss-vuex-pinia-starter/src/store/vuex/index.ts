import Vue from 'vue'
import Vuex from 'vuex'
import App from './modules/App'
import Counter from './modules/Counter';

Vue.use(Vuex);


export const vuexStore = new Vuex.Store({
    strict: true,
    modules: {
        App,
        Counter
    }
})