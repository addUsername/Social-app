import axios from "axios";
import store from "@/store";

const ENDPOINT_PATH = "http://localhost:8080/api/social/";

export default {
  like(likeDTO) {
    console.log("making like");
    console.log(likeDTO);
    return axios
      .post(ENDPOINT_PATH + "like", likeDTO, {
        headers: {
          Authorization: `Bearer ${store.getters["auth/user"].token}`
        }
      })
      .then(response => {
        console.log("RESPONSE DATA FROM LIIKKE");
        console.log(response.data);
      })
      .catch(error => {
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      });
  },
  follow(username) {
    console.log(
      "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
    );
    console.log(username);
    console.log(username.username);
    axios({
      method: "post", //put
      url: ENDPOINT_PATH + "follow",
      headers: {
        Authorization: `Bearer ${store.getters["auth/user"].token}`
      },
      data: username
    }).then(response => {
      console.log(response.data);
    });
  }
};
