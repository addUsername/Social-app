import axios from "axios";

const ENDPOINT_PATH = "http://localhost:8080/api/auth/";

export default {
  register(name, username, email, password) {
      console.log("we are here");
    const user = { name, username, email, password };
    return axios.post(ENDPOINT_PATH + "new", user);
  },
  login(username,password){
    console.log("we are in login");
      const user = {username, password};
      console.log(ENDPOINT_PATH + "login", user)
      return axios.post(ENDPOINT_PATH + "login", user);
  }
    };