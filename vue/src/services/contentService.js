/*
The services layer handles all http communication with backend apis for the application, each service encapsulates 
the api calls for a content type (e.g. users) and exposes methods for performing various operations (e.g. CRUD operations).
 Services can also have methods that don't wrap http calls, for example the userService.logout() method just removes an item
  from local storage.

I like wrapping http calls and implementation details in a services layer, it provides a clean separation of concerns and simplifies the vuex modules that use the services.
*/
import axios from "axios";
import store from "@/store";

const ENDPOINT_PATH = "http://localhost:8080/api/media/";

export default {
  getUserFrontPage(username) {
    const user = { username: username };
    return axios
      .get(ENDPOINT_PATH + "home/" + user.username, {
        headers: {
          Authorization: `Bearer ${store.getters["auth/user"].token}`
        }
      })
      .catch(error => {
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      });
    //this.$router.push("/");
  },
  getBigImg(imgID, username, type) {
    //this is not needed
    console.log(type);
    return axios.get(ENDPOINT_PATH + "home/big/" + imgID + "/" + username, {
      responseType: "blob",
      headers: {
        //JSON.parse(localStorage.getItem("user")).token
        Authorization: `Bearer ${store.getters["auth/user"].token}`
      }
    });
  },
  getThumbImg(imgID, username) {
    return axios.get(ENDPOINT_PATH + "home/" + imgID + "/" + username, {
      responseType: "blob",
      //mirar aqui, tenemos que pasarle el type video o png
      type: "image/png",
      headers: {
        Authorization: `Bearer ${store.getters["auth/user"].token}`
      }
    });
  },
  getAvatar(username) {
    return axios.get(ENDPOINT_PATH + "avatar/" + username, {
      responseType: "blob",
      //mirar aqui, tenemos que pasarle el type video o png
      type: "image/png",
      headers: {
        Authorization: `Bearer ${store.getters["auth/user"].token}`
      }
    });
  },
  getFrame(obj) {
    return axios.get(
      ENDPOINT_PATH + "frame/" + obj.frameId + "/" + obj.username,
      {
        headers: {
          Authorization: `Bearer ${store.getters["auth/user"].token}`
        }
      }
    );
  },
  uploadFrame(file, media) {
    const mediaBlob = new Blob([JSON.stringify(media)], {
      type: "application/json"
    });
    var formData = new FormData();
    formData.append("file", file);
    formData.append("media", mediaBlob);

    return axios
      .post(ENDPOINT_PATH + "upload/", formData, {
        //mirar aqui, nosq xq content-type tiene que ir asi, quizas probando con type
        headers: {
          Accept: "application/json",
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${store.getters["auth/user"].token}`
        }
      })
      .then(response => {
        return response.data.message;
      });
  },
  uploadAvatar(file, username) {
    var formData = new FormData();
    formData.append("file", file);

    return axios
      .post(ENDPOINT_PATH + "upload/avatar/" + username, formData, {
        //mirar aqui, nosq xq content-type tiene que ir asi, quizas probando con type
        headers: {
          Accept: "application/json",
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${store.getters["auth/user"].token}`
        }
      })
      .then(response => {
        return response.data.message;
      });
  }
};
