import Vue from "vue";
import Vuex from "vuex";
import auth from "./auth";
import content from "./content";
import social from "./social";
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    social,
    auth,
    content
  }
});
