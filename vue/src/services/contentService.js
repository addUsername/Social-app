import axios from "axios";

const ENDPOINT_PATH = "http://localhost:8080/api/media/";

export default {
  getUserFrontPage(username) {
    const user = { username: username };
    return axios
      .get(ENDPOINT_PATH + "home/" + user.username, {
        headers: {
          Authorization: `Bearer ${
            JSON.parse(localStorage.getItem("user")).token
          }`
        }
      })
      .catch(error => {
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      });
    //this.$router.push("/");
  }
};
