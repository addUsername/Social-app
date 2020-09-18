import axios from "axios";

const ENDPOINT_PATH = "http://localhost:8080/api/auth/";

export default {
  register(user) {
    //const user = { name, username, email, password };
    return axios
      .post(ENDPOINT_PATH + "new", user)
      .then(response => {
        return response;
      })
      .catch(error => {
        throw new Error(`API ${error}`);
      });
  },
  login(user) {
    //const user = { username, password };
    return axios
      .post(ENDPOINT_PATH + "login", user)
      .then(response => {
        const user = {
          username: response.data.username,
          token: response.data.token
        };
        return user;
      })
      .catch(error => {
        throw new Error(`API ${error}`);
      });
  }
};
