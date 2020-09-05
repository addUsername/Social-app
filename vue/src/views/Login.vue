<template>
  <v-app id="inspire">
    <v-main>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-6">
              <v-toolbar color="primary" dark flat>
                <v-toolbar-title>Login form</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-tooltip bottom>
                  <template v-slot:activator="{ on }">
                    <v-btn icon large target="_blank" v-on="on">
                      <v-icon>mdi-code-tags</v-icon>
                    </v-btn>
                  </template>
                </v-tooltip>
              </v-toolbar>
              <v-card-text>
                <v-form @submit.prevent="login" id="connect-submit-btn-to form">
                  <v-text-field
                    label="Username"
                    name="username"
                    prepend-icon="mdi-account"
                    type="text"
                    :rules="['Required']"
                    v-model="username"
                  ></v-text-field>
                  <p>login check: {{ $store.getters.isLogged }}</p>
                  <v-text-field
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    :rules="['Required']"
                    v-model="password"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="primary"
                  type="submit"
                  form="connect-submit-btn-to form"
                  >Login</v-btn
                >
                <v-btn color="secundary">Register</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
//import auth from "@/services/authService";

export default {
  data: () => ({
    username: "",
    password: ""
  }),
  methods: {
    login() {
      //auth.login(this.username, this.password).then(() => {
      //  this.$router.push("/home/" + this.username);
      const user = { username: this.username, password: this.password };
      this.$store.dispatch("auth/login", user).then(() => {
        //console.log(this.$store.getters.isLogged);
        if (this.$store.getters.["auth/isLogged"]) {
          //this.$router.push("/home/" + this.$store.getters.user.username)
          //esto es para test
          console.log("is logged!!");
          this.$router.push("/home/" + "myTest");
        }
      });
    }
  }
};
</script>

<style></style>
