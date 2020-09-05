import userService from "@/services/userService";

const auth = {
  //https://www.ictshore.com/javascript/vuex-modules-tutorial/
  //hey are not on the root of the store anymore. To reference them, you need to call them with
  // the module name and the property name, separated by a slash. [ex: 'auth/login' ]
  /*
    // State
    this.$store.state.auth.user
    // Mutation
    this.$store.commit('auth/login', {user})
    // Action
    this.$store.dispatch('auth/login', { user })
    // Getter
    this.$store.getters['auth/loggedIn']
    */
  namespaced: true,
  state: {
    //Our objects
    user: {
      username: "",
      token: ""
    },
    isLogged: false,
    hasAccount: false
  },
  mutations: {
    //Modify objects, keep simple as setter should be bc things
    SAVE_USER(state, user) {
      state.user = user;
      console.log("saving user");
    },
    SET_LOGGED(state, bool) {
      state.isLogged = bool;
      console.log("setting logged");
    },
    SET_ACCOUNT(state, bool) {
      state.hasAccount = bool;
      console.log("setting account");
    },
    DEL_USER(state) {
      state.user = null;
      console.log("deleting user");
    }
  },
  getters: {
    //Notice how getters is an object. user is a property of this object,
    // which accepts the state as the parameter, and returns the user property of the state.
    user: state => state.user,
    isLogged: state => state.isLogged,
    hasAccount: state => state.hasAccount
  },
  actions: {
    //Api calls here, actions are meant to be async while mutations should happen as near to instantly as possible.
    login({ commit }, user) {
      return userService.login(user).then(result => {
        if (result.token) {
          commit("SAVE_USER", result);
          commit("SET_LOGGED", true);
          return true;
        }
      });
    },
    register({ commit }, user) {
      return userService.register(user).then(result => {
        if (result.status == 201) {
          commit("SET_ACCOUNT", true);
          return true;
        }
      });
    },
    logout({ commit }) {
      commit("DEL_USER");
      commit("SET_LOGGED", false);
    }
  }
};
export default auth;
