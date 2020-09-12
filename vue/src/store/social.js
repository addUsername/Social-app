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
      return socialService.like(likeDTO).then(() => {
        commit("SAVE_ISSOCIALWORKING", false);
      });
    },
    follow({ commit }, username) {
      console.log(username);
      commit("SAVE_ISSOCIALWORKING", true);
      return socialService.follow(username).then(() => {
        commit("SAVE_ISSOCIALWORKING", false);
      });
    }
  }
};
export default content;
