import axios from "axios";
import store from "@/store";

const ENDPOINT_PATH = "http://localhost:8080/api/social/";

export default {
  like(likeDTO) {
    return axios
      .post(ENDPOINT_PATH + "like", likeDTO, {
        headers: {
          Authorization: `Bearer ${store.getters["auth/user"].token}`
        }
      })
      .then(response => {
        return response.data.message;
      })
      .catch(error => {
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      });
  },
  follow(username) {
    axios({
      method: "post", //put
      url: ENDPOINT_PATH + "follow",
      headers: {
        Authorization: `Bearer ${store.getters["auth/user"].token}`
      },
      data: username
    }).then(response => {
      return response.data.message;
    });
  },
  sendComment(commentDTO) {
    return axios
      .post(ENDPOINT_PATH + commentDTO.username + "/addComment", commentDTO, {
        headers: {
          Authorization: `Bearer ${store.getters["auth/user"].token}`
        }
      })
      .then(response => {
        return response.data.message;
      });
  },
  updateClient(updateClientDTO) {
    return axios
      .post(ENDPOINT_PATH + "update/credentials", updateClientDTO, {
        headers: {
          Authorization: `Bearer ${store.getters["auth/user"].token}`
        }
      })
      .then(response => {
        return response.data.message;
      });
  }
};
