import socialService from "@/services/socialService";

const content = {
  namespaced: true,
  state: {
    isSocialWorking: false
  },
  mutations: {
    SAVE_ISSOCIALWORKING(state, boolean) {
      console.log("SAVE_ISSOCIALWORKING");
      state.isSocialWorking = boolean;
    }
  },
  getters: {
    isSocialWorking: state => state.isSocialWorking
  },
  actions: {
    like({ commit }, likeDTO) {
      commit("SAVE_ISSOCIALWORKING", true);
      return socialService.like(likeDTO).then(response => {
        commit("SAVE_ISSOCIALWORKING", false);
        return response;
      });
    },
    follow({ commit }, username) {
      commit("SAVE_ISSOCIALWORKING", true);
      return socialService.follow(username).then(response => {
        commit("SAVE_ISSOCIALWORKING", false);
        return response;
      });
    },
    sendComment({ commit }, commentDTO) {
      commit("SAVE_ISSOCIALWORKING", true);
      return socialService.sendComment(commentDTO).then(response => {
        commit("SAVE_ISSOCIALWORKING", false);
        return response;
      });
    },
    updateClient({ commit }, updateClientDTO) {
      commit("SAVE_ISSOCIALWORKING", true);
      return socialService.updateClient(updateClientDTO).then(response => {
        commit("SAVE_ISSOCIALWORKING", false);
        return response;
      });
    }
  }
};
export default content;
