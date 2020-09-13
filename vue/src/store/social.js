import socialService from "@/services/socialService";

const content = {
  namespaced: true,
  state: {
    isSocialWorking: false
  },
  mutations: {
    SAVE_ISSOCIALWORKING(state, boolean) {
      state.isSocialWorking = boolean;
    }
  },
  getters: {
    isSocialWorking: state => state.isSocialWorking
  },
  actions: {
    like({ commit }, likeDTO) {
      console.log(likeDTO);
      commit("SAVE_ISSOCIALWORKING", true);
      return socialService.like(likeDTO).then(response => {
        commit("SAVE_ISSOCIALWORKING", false);
        return response;
      });
    },
    follow({ commit }, username) {
      console.log(username);
      commit("SAVE_ISSOCIALWORKING", true);
      return socialService.follow(username).then(response => {
        commit("SAVE_ISSOCIALWORKING", false);
        return response;
      });
    },
    sendComment({ commit }, CommentDTO) {
      commit("SAVE_ISSOCIALWORKING", true);
      return socialService.sendComment(CommentDTO).then(response => {
        commit("SAVE_ISSOCIALWORKING", false);
        return response;
      });
    }
  }
};
export default content;
