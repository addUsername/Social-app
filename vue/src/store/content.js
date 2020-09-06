import contentService from "@/services/contentService";

const content = {
  namespaced: true,
  state: {
    home: {
      username: "",
      bigImg: "",
      imgs: [],
      frames: {},
      text: ""
    },
    img: ""
  },
  mutations: {
    //Modify objects, keep simple as setter should be bc things
    SAVE_HOME(state, home) {
      state.home = home;
      console.log("saving home");
    },
    SAVE_IMG(state, img) {
      state.img = img;
      console.log("saving img");
    },
    //Unused
    DEL_HOME(state) {
      state.user = null;
      console.log("deleting user");
    }
  },
  getters: {
    //Notice how getters is an object. user is a property of this object,
    // which accepts the state as the parameter, and returns the user property of the state.
    home: state => state.home,
    img: state => state.img
  },
  actions: {
    //Api calls here, actions are meant to be async while mutations should happen as near to instantly as possible.
    getUserFrontPage({ commit }, username) {
      console.log("getUserFrontPage");
      return contentService.getUserFrontPage(username).then(response => {
        console.log(response.data);
        if (response.status == 200) {
          var home = {
            username: username,
            bigImgUrl: response.data.img,
            imgs: response.data.imgs,
            frames: frames.data.idFrame,
            text: response.data.text
          };
          console.log(home);
          commit("SAVE_HOME", home);
        }
      });
    },
    getBigImg({ commit, getters }) {
      console.log(getters["content/home"].img);
      console.log("getbigimg");
      return contentService
        .getBigImg(
          getters["content/home"].img,
          getters["content/home"].username
        )
        .then(response => {
          // this.bigImgUrl = Buffer.from(response.data, "binary").toString("base64" );
          var url = URL.createObjectURL(new Blob([response.data]));
          commit("SAVE_IMG", url);
          return url;
        });
    },
    logout({ commit }) {
      commit("DEL_USER");
      commit("SET_LOGGED", false);
    }
  }
};
export default content;
