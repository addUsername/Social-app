import axios from "axios";

const ENDPOINT_PATH = "http://localhost:8080/api/auth/";

export default {
  register(user) {
    //const user = { name, username, email, password };
    console.log("hi from register service");
    console.log(user);
    return axios
      .post(ENDPOINT_PATH + "new", user)
      .then(response => {
        console.log(response.data);
        console.log(response.status);
        return response;
      })
      .catch(error => {
        throw new Error(`API ${error}`);
      });
  },
  login(user) {
    //const user = { username, password };
    console.log("login from userService");
    console.log(user);
    return axios
      .post(ENDPOINT_PATH + "login", user)
      .then(response => {
        const user = {
          username: response.data.username,
          token: response.data.token
        };
        console.log(user);
        console.log("return user");
        return user;
      })
      .catch(error => {
        throw new Error(`API ${error}`);
      });
  }
};
