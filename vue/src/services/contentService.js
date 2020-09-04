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
  },
  getBigImg(imgID, username) {
    console.log("making request get");
    return axios.get(ENDPOINT_PATH + "home/big/" + imgID + "/" + username, {
      responseType: "blob",
      type: "image/jpg",
      headers: {
        Authorization: `Bearer ${
          JSON.parse(localStorage.getItem("user")).token
        }`
      }
    });
  },
  getThumbImg(imgID, username) {
    console.log("making request get");
    return axios.get(ENDPOINT_PATH + "home/" + imgID + "/" + username, {
      responseType: "blob",
      type: "image/png",
      headers: {
        Authorization: `Bearer ${
          JSON.parse(localStorage.getItem("user")).token
        }`
      }
    });
  }
};
