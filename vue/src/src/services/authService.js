import axios from "axios";

const ENDPOINT_PATH = "http://localhost:8080/api/auth/";

export default {
  register(name, username, email, password) {
    const user = { name, username, email, password };

    return axios
      .post(ENDPOINT_PATH + "new", user)
      .then(() => {})
      .catch(error => {
        this.logout();
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      });
  },
  login(username, password) {
    const user = { username, password };
    return axios
      .post(ENDPOINT_PATH + "login", user)
      .then(response => {
        const user = {
          username: response.data.username,
          token: response.data.token
        };
        console.log("set user");
        //if no errors
        localStorage.setItem("user", JSON.stringify(user));
        console.log(JSON.parse(localStorage.getItem("user")));
      })
      .catch(error => {
        this.logout();
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      });
    //this.$router.push("/");
  },
  logout() {
    console.log("logout()");
    localStorage.removeItem("user");
  }
};

/*
The services layer handles all http communication with backend apis for the application, each service encapsulates 
the api calls for a content type (e.g. users) and exposes methods for performing various operations (e.g. CRUD operations).
 Services can also have methods that don't wrap http calls, for example the userService.logout() method just removes an item
  from local storage.

I like wrapping http calls and implementation details in a services layer, it provides a clean separation of concerns and simplifies the vuex modules that use the services.
*/
