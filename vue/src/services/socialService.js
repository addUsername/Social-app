import axios from "axios";
import store from "@/store";

const ENDPOINT_PATH = "http://localhost:8080/api/social/";
const headers = {
  Authorization: `Bearer ${store.getters["auth/user"].token}`
};

export default {
  like(likeDTO) {
    console.log("making like");
    return axios
      .post(ENDPOINT_PATH + "like", likeDTO, {
        headers: headers
      })
      .catch(error => {
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      });
  }
};
