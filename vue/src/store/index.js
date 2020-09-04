import Vue from "vue";
import Vuex from "vuex";
import userService from "@/services/userService";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    //Our objects
    user: {
      username: "",
      token: ""
    },
    isLogged: false
  },
  mutations: {
    //Modify objects, keep simple as setter should be bc things
    SAVE_USER(state, user) {
      state.user = user;
      console.log("holaaa");
    },
    SET_LOGGED(state, bool) {
      state.isLogged = bool;
      console.log("holaaa");
    },
    DEL_USER(state) {
      state.user = null;
    }
  },
  getters: {
    //Notice how getters is an object. user is a property of this object,
    // which accepts the state as the parameter, and returns the user property of the state.
    user: state => state.user,
    isLogged: state => state.isLogged
  },
  actions: {
    //Api calls here, actions are meant to be async while mutations should happen as near to instantly as possible.
    login({ commit }, user) {
      console.log("login from store");
      console.log(user);
      userService.login(user).then(result => {
        console.log("result");
        console.log(result);
        commit("SAVE_USER", result);
        commit("SET_LOGGED", true);
      });
    },
    register({ commit, user }) {
      //TO DO
      userService.register(user).then(result => commit("SAVE_USER", result));
    },
    logout({ commit }) {
      commit("DEL_USER");
      commit("SET_LOGGED", false);
    }
  },
  modules: {}
});
