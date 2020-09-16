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
              <v-card-title>Edit Home</v-card-title>
              <v-card-text>
                <v-file-input
                  accept="image/png, image/jpeg"
                  placeholder="Avatar"
                  prepend-icon="mdi-camera"
                  v-model="fileAvatar"
                ></v-file-input>
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
                  accept="image/png, image/jpeg"
                  placeholder="Home"
                  prepend-icon="mdi-camera"
                  v-model="fileBig"
                ></v-file-input>
                <v-card-subtitle>Type password and confirm</v-card-subtitle>
              </v-card-text>
              <v-card-actions>
                <v-row>
                  <v-col lg="6" sm="12">
                    <v-text-field
                      id="password"
                      label="Type password to confirm"
                      name="password"
                      prepend-icon="mdi-lock"
                      type="password"
                      :rules="['Required']"
                      v-model="password"
                    ></v-text-field>
                  </v-col>
                  <v-col lg="6" sm="12">
                    <v-btn
                      color="primary"
                      type="submit"
                      form="connect-submit-btn-to form"
                      >Update</v-btn
                    ></v-col
                  >
                </v-row>
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
                <v-card-subtitle> Here should be the same </v-card-subtitle>
                <v-sheet class="mx-auto" elevation="flat" max-width="800">
                  <v-slide-group v-model="model" center-active show-arrows>
                    <v-slide-item
                      v-for="n in 15"
                      :key="n"
                      v-slot:default="{ active, toggle }"
                    >
                      <v-card
                        :color="active ? 'primary' : 'grey lighten-1'"
                        class="ma-2"
                        height="200"
                        width="100"
                        @click="toggle"
                      >
                        <v-card align="center" justify="center">
                          <h3>imagen</h3>
                          <br />
                          <br />
                          <br />
                          <br />
                          <v-text> something</v-text>
                        </v-card>
                      </v-card>
                    </v-slide-item>
                  </v-slide-group>
                </v-sheet>
              </v-card-text>
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
      message: "",
      snackBar: false
    };
  },
  computed: {
    ...mapGetters({
      user: "auth/user",
      isFrameLoaded: "content/isFrameLoaded"
    })
  },
  methods: {
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
      console.log(updateClientDTO);
      this.$store
        .dispatch("social/updateClient", updateClientDTO)
        .then(response => {
          this.message = response;
          this.snackBar = true;
          if (this.checkboxDelete || this.newPassword !== "") {
            this.$router.push("/login");
          }
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
