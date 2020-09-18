<template>
  <v-app id="inspire">
    <v-snackbar color="success" v-model="snackBar">
      {{ message }}
    </v-snackbar>
    <v-card>
      <v-toolbar flat color="secundary" dark> </v-toolbar>
      <v-tabs>
        <v-tab> <v-icon left>mdi-account</v-icon>Profile </v-tab>
        <v-tab> <v-icon left>mdi-lock</v-icon> security </v-tab>
        <v-tab> <v-icon left>mdi-access-point</v-icon> Media </v-tab>

        <v-tab-item>
          <v-row align="center" justify="center">
            <v-card flat class="col-6">
              <v-card-text>
                <v-card-title>Change avatar</v-card-title>
                <v-file-input
                  accept="image/png, image/jpeg"
                  placeholder="Avatar"
                  prepend-icon="mdi-camera"
                  v-model="fileAvatar"
                ></v-file-input>
                <v-card-title>Edit home</v-card-title>
                <v-tooltip color="primary" top>
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      label="Description"
                      prepend-icon="mdi-text"
                      :rules="['Required']"
                      v-model="textHome"
                      v-on="on"
                    ></v-text-field> </template
                  >Sometimes you don’t have to use many words to get your point
                  across</v-tooltip
                >
                <v-file-input
                  accept="image/jpeg"
                  placeholder="Home"
                  prepend-icon="mdi-camera"
                  v-model="fileHome"
                ></v-file-input>
              </v-card-text>
              <v-card-actions>
                <v-col lg="7" sm="12">
                  <v-card-subtitle>
                    <p>
                      Please type: "I want to commit these changes"
                    </p></v-card-subtitle
                  >
                  <v-text-field
                    label="Type here"
                    prepend-icon="mdi-lock"
                    :rules="['Required']"
                    v-model="confirm"
                  ></v-text-field>
                </v-col>
                <v-col lg="3" sm="12">
                  <v-btn color="primary" @click.prevent="submitHome"
                    >Update</v-btn
                  ></v-col
                >
              </v-card-actions>
            </v-card>
          </v-row>
        </v-tab-item>
        <v-tab-item>
          <v-row align="center" justify="center">
            <v-card flat class="col-6">
              <v-card-title>Change credentials</v-card-title>
              <v-card-text>
                <v-card-subtitle>
                  In order to change your name or username
                  <v-tooltip bottom>
                    <template v-slot:activator="{ on }">
                      <a target="_blank" href="#" @click.stop v-on="on">
                        contact us
                      </a>
                    </template>
                    New tab
                  </v-tooltip>
                  please</v-card-subtitle
                >
                <v-text-field
                  label="New email"
                  name="email"
                  prepend-icon="mdi-email"
                  type="email"
                  v-model="email"
                ></v-text-field>
                <v-text-field
                  label="New Password"
                  name="password"
                  prepend-icon="mdi-lock"
                  type="password"
                  v-model="newPassword"
                ></v-text-field>
                <v-text-field
                  id="password2"
                  label="Repeat password"
                  name="password2"
                  prepend-icon="mdi-lock"
                  type="password"
                  v-model="newPassword2"
                ></v-text-field>
              </v-card-text>
              <v-card-title>Account</v-card-title>
              <v-card-text>
                <v-checkbox v-model="checkboxSuspend">
                  <template v-slot:label>
                    <v-card flat>
                      <v-card-title>Suspend account</v-card-title>
                      <v-card-subtitle>
                        <p>
                          Your account and media related files will not be
                          deleted. But your home, list friends and PMs will be
                          disabled and we will stop emailing you feedback.
                        </p>
                        <p>
                          of course you are allowed to come back whenever you
                          please, we are waiting!
                        </p>
                      </v-card-subtitle>
                    </v-card>
                  </template>
                </v-checkbox>
                <v-checkbox v-model="checkboxDelete">
                  <template v-slot:label>
                    <v-card flat>
                      <v-card-title>Delete account</v-card-title>
                      <v-card-subtitle>
                        <p>
                          Everything about you here will be deleted, your
                          username and email will be blocked forever.
                        </p>
                        <p>
                          (This procces could take up tu 1 hour to complete)
                        </p>
                      </v-card-subtitle>
                    </v-card>
                  </template>
                </v-checkbox>
              </v-card-text>
              <v-card-actions>
                <v-col lg="7" sm="12">
                  <v-card-subtitle>
                    <p>
                      Please type: "I want to commit these changes"
                    </p></v-card-subtitle
                  >
                  <v-text-field
                    label="Type here"
                    prepend-icon="mdi-lock"
                    :rules="['Required']"
                    v-model="confirm"
                  ></v-text-field>
                </v-col>
                <v-col lg="7" sm="12">
                  <v-btn color="primary" @click.prevent="submitSecurity"
                    >Update</v-btn
                  ></v-col
                >
              </v-card-actions>
            </v-card>
          </v-row>
        </v-tab-item>
        <v-tab-item>
          <v-row align="center" justify="center">
            <v-card flat class="col-6">
              <v-card-title>My media</v-card-title>
              <v-card-text>
                <v-card-subtitle> Select a post to edit </v-card-subtitle>
                <v-sheet
                  class="ma0-auto"
                  elevation="flat"
                  width="100%"
                  max-width="800"
                >
                  <v-slide-group center-active show-arrows>
                    <v-slide-item
                      v-for="pair in getData"
                      :key="pair.key"
                      v-slot:default="{ active, toggle }"
                    >
                      <v-card
                        v-on:click="callIDSelected(pair.key)"
                        :color="active ? 'primary' : 'grey lighten-1'"
                        class="ma-2"
                        height="230"
                        width="200"
                        @click="toggle"
                      >
                        <v-card align="center" justify="center">
                          <v-img
                            class="white--text align-end"
                            v-bind:src="pair.value"
                            align="right"
                            max-width="200"
                          >
                            <v-chip class="ma-2" color="pink" outlined>
                              <v-icon dark>mdi-heart</v-icon>
                              {{ pair["like"] }}
                            </v-chip>
                          </v-img>
                        </v-card>
                      </v-card>
                    </v-slide-item>
                  </v-slide-group>
                  <v-card-title>Delete post</v-card-title>
                  <v-checkbox v-model="checkboxDeleteFrame">
                    <template v-slot:label>
                      (This procces is irreversible, visibles changes takes
                      time)
                    </template>
                  </v-checkbox>
                </v-sheet>
                <v-card-title>Edit post</v-card-title>
                <v-tooltip color="primary" top>
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      label="Set new description"
                      prepend-icon="mdi-text"
                      :rules="['Required']"
                      v-model="textFrame"
                      v-on="on"
                    ></v-text-field> </template
                  >Sometimes you don’t have to use many words to get your point
                  across</v-tooltip
                >
                <v-file-input
                  accept="image/jpeg"
                  placeholder="Home"
                  prepend-icon="mdi-camera"
                  v-model="fileFrame"
                ></v-file-input>
              </v-card-text>
              <v-card-actions>
                <v-col lg="7" sm="12">
                  <v-card-subtitle>
                    <p>
                      Please type: "I want to commit these changes"
                    </p></v-card-subtitle
                  >
                  <v-text-field
                    label="Type here"
                    prepend-icon="mdi-lock"
                    :rules="['Required']"
                    v-model="confirm"
                  ></v-text-field>
                </v-col>
                <v-col lg="7" sm="12">
                  <v-btn color="primary" @click.prevent="submitFrame"
                    >Update</v-btn
                  ></v-col
                >
              </v-card-actions>
            </v-card>
          </v-row>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </v-app>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      email: "",
      confirm: "",
      newPassword: "",
      newPassword2: "",
      password2: "",
      checkboxSuspend: false,
      checkboxDelete: false,
      fileHome: "",
      fileAvatar: "",
      textHome: "",
      message: "",
      snackBar: false,
      cardSelected: "",
      textFrame: "",
      fileFrame: "",
      checkboxDeleteFrame: ""
    };
  },
  computed: {
    ...mapGetters({
      user: "auth/user",
      isFrameLoaded: "content/isFrameLoaded"
    }),
    getData() {
      return this.$store.getters["content/thumbFrame"];
    }
  },
  methods: {
    callIDSelected(key) {
      this.cardSelected = key;
    },
    submitFrame() {
      alert(this.cardSelected)
      if (this.confirm !== "I want to commit these changes") {
        return this.showMessage(
          "Please write the sentence correctly to validate your actions"
        );
      }
      if (this.checkboxDeleteFrame) {
        if(this.cardSelected == ""){
          return this.showMessage("OMG dude.. pick a Post!");
        }
        this.$store
          .dispatch("content/deleteFrame", this.cardSelected)
          .then(response => {
            return this.showMessage(response);
          });
      }
      if (this.textFrame == "" || this.fileFrame == "") {
        return this.showMessage("Please fill the gaps");
      }
      const myObj = {
        file: this.fileFrame,
        media: {
          username: this.$store.getters["auth/user"].username,
          text: this.textFrame,
          docType: this.fileFrame.type,
          isHome: false,
          frameId: this.cardSelected
        }
      };
      this.$store.dispatch("content/uploadFrame", myObj).then(response => {
        this.file = "";
        this.input = "";
        return this.showMessage(response);
      });
    },
    submitSecurity() {
      if (this.confirm !== "I want to commit these changes") {
        return this.showMessage(
          "Please write the sentence correctly to validate your actions"
        );
      }
      if (this.checkboxSuspend && this.checkboxDelete) {
        return this.showMessage(
          "Please choose one between suspend action or delete"
        );
      }
      if (this.newPassword !== this.newPassword2) {
        return this.showMessage("Passwords didn't match");
      }
      if (this.email !== "" && this.newPassword !== "") {
        return this.showMessage(
          "Password and email can't be changed at once :)"
        );
      }
      const updateClientDTO = {
        username: this.user.username,
        delete: this.checkboxDelete,
        suspend: this.checkboxSuspend,
        newPassword: this.newPassword,
        newEmail: this.newEmail
      };
      this.$store
        .dispatch("social/updateClient", updateClientDTO)
        .then(response => {
          if (this.checkboxDelete || this.newPassword !== "") {
            this.$router.push("/login");
          }
          //aqui deberia borrar el state xd
          (this.checkboxDelete = false),
            (this.checkboxSuspend = false),
            (this.newPassword = "");
          this.newEmail = "";
          return this.showMessage(response);
        });
    },
    submitHome() {
      if (this.confirm !== "I want to commit these changes") {
        return this.showMessage(
          "Please write the sentence correctly to validate your actions"
        );
      }
      if (this.fileAvatar == "") {
        if (this.fileHome == "" || this.textHome == "") {
          return this.showMessage(
            "Please add a home image with its description text"
          );
        } else {
          //update home here
          const myObj = {
            file: this.fileHome,
            media: {
              username: this.$store.getters["auth/user"].username,
              text: this.textHome,
              docType: this.fileHome.type,
              isHome: true,
              frameId: this.$store.getters["content/home"].bigImg
            }
          };
          //upload frame
          this.$store.dispatch("content/uploadFrame", myObj).then(response => {
            this.fileHome = "";
            this.textHome = "";
            return this.showMessage(response);
          });
        }
      }
      const obj = {
        username: this.user.username,
        file: this.fileAvatar
      };
      //upload avatar
      this.$store.dispatch("content/uploadAvatar", obj).then(response => {
        this.fileAvatar = "";
        return this.showMessage(response);
      });
    },
    showMessage(message) {
      this.message = message;
      this.snackBar = true;
      return;
    }
  }
};
</script>
<style></style>
