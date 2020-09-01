import axios from "axios";

const ENDPOINT_PATH = "http://localhost:8080/api/auth/";

export default {
  register(name, username, email, password) {
    console.log("we are here");
    const user = { name, username, email, password };

    axios
      .post(ENDPOINT_PATH + "new", user)
      .then(this.handeResponse)
      .then(data => {
        const user = { username: data.username, token: data.token}
        localStorage.setItem(user);
      });
    return true;
  },
  login(username, password) {
    const user = { username, password };
    axios
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
        console.log(error);
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