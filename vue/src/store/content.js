import contentService from "@/services/contentService";

const content = {
  namespaced: true,
  state: {
    home: {
      // @DOC
      username: "",
      bigImg: "",
      imgs: [],
      frames: [],
      text: "",
      likes: []
    },
    img: "",
    thumbFrame: [],
    currentFrameId: "",
    frame: {
      media_id: "",
      comments: [],
      text: "",
      mediaType: "",
      likes: ""
    }
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
    //Unused by now
    DEL_HOME(state) {
      state.user = null;
      console.log("deleting user");
    },
    DEL_THUMBFRAME(state) {
      state.thumbFrame = null;
      console.log("deleting user");
    },
    SAVE_THUMBFRAME(state, pair) {
      state.thumbFrame.push(pair);
      console.log("append thumbframe");
    },
    SAVE_CURRENTFRAMEID(state, frameId) {
      state.currentFrameId = frameId;
      console.log("setting current frame");
    },
    SAVE_FRAME(state, frame) {
      state.frame = frame;
      console.log("frame");
    }
  },
  getters: {
    // Notice how getters is an object. user is a property of this object,
    // which accepts the state as the parameter, and returns the user property of the state.
    home: state => state.home,
    img: state => state.img,
    thumbFrame: state => state.thumbFrame,
    frame: state => state.frame,
    currentFrameId: state => state.currentFrameId
  },
  actions: {
    // Api calls here, actions are meant to be async while mutations should happen as near to instantly as possible.
    getUserFrontPage({ commit }, username) {
      return contentService.getUserFrontPage(username).then(response => {
        console.log(response.data);
        if (response.status == 200) {
          var home = {
            username: username,
            bigImg: response.data.img,
            imgs: response.data.imgs,
            frames: response.data.idFrame,
            text: response.data.text,
            likes: response.data.likes
          };
          console.log(home);
          commit("SAVE_HOME", home);
        }
      });
    },
    getBigImg({ commit, state }) {
      return contentService
        .getBigImg(state.home.bigImg, state.home.username)
        .then(response => {
          var url = URL.createObjectURL(new Blob([response.data]));
          commit("SAVE_IMG", url);
          return url;
        });
    },
    getThumbnails({ commit, state }) {
      state.home.imgs.forEach((thumbnail, i) => {
        contentService
          .getThumbImg(thumbnail, state.home.username)
          .then(response => {
            var url = URL.createObjectURL(new Blob([response.data]));
            commit("SAVE_THUMBFRAME", {
              key: state.home.frames[i],
              value: url,
              like: state.home.likes[i]
            });
          });
      });
      return;
    },
    getFrame({ commit }, obj) {
      contentService.getFrame(obj).then(response => {
        commit("SAVE_FRAME", response.data);
      });
      return;
    },
    setFrameId({ commit }, frameId) {
      commit("SAVE_CURRENTFRAMEID", frameId);
    }
  }
};
export default content;
