<template>
  <v-app id="inspire">
    <v-main>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-6">
              <v-toolbar color="primary" flat>
                <v-toolbar-title>Register form</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-icon>mdi-code-tags</v-icon>
              </v-toolbar>
              <v-card-text>
                <v-form @submit.prevent="check" id="connect-submit-btn-to form">
                  <v-text-field
                    label="User"
                    name="user"
                    prepend-icon="mdi-account"
                    type="text"
                    v-model="name"
                    :rules="['Required']"
                  ></v-text-field>
                  <v-text-field
                    label="Username"
                    name="username"
                    prepend-icon="mdi-account"
                    type="text"
                    v-model="username"
                    :rules="['Required']"
                  ></v-text-field>
                  <v-text-field
                    label="Email"
                    name="email"
                    prepend-icon="mdi-email"
                    type="email"
                    v-model="email"
                    :rules="['Required']"
                  ></v-text-field>
                  <v-text-field
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    v-model="password"
                    :rules="['Required']"
                  ></v-text-field>
                  <v-text-field
                    id="password2"
                    label="password2"
                    name="password2"
                    prepend-icon="mdi-lock"
                    type="password"
                    v-model="password2"
                    :rules="['Required']"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="primary"
                  type="submit"
                  form="connect-submit-btn-to form"
                  >Register</v-btn
                >
                <v-btn color="secundary" to="/login">LOG IN</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  data: () => ({
    name: "",
    username: "",
    password: "",
    password2: "",
    email: ""
  }),
  methods: {
    check() {
      if (this.password != this.password2) {
        alert("passwords not match");
      } else {
        this.register();
      }
    },
    register() {
      const user = {
        name: this.name,
        username: this.username,
        password: this.password,
        email: this.email
      };
      this.$store.dispatch("auth/register", user).then(hasAccount => {
        //console.log(this.$store.getters.hasAccount);
        if (hasAccount) {
          console.log("has account!!");
          this.$router.push("login");
        }
      });
    }
  }
};
</script>

<style></style>
