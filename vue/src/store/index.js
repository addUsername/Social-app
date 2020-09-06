import Vue from "vue";
import Vuex from "vuex";
import auth from "./auth";
import content from "./content";
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    content
  }
});
