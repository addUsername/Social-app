<template>
  <v-app id="inspire">
    <v-main>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-6">
              <v-toolbar color="primary" flat>
                <v-toolbar-title>Login form</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-icon>mdi-code-tags</v-icon>
              </v-toolbar>
              <v-card-text>
                <v-form @submit.prevent="login" id="connect-submit-btn-to form">
                  <v-text-field
                    label="Username"
                    name="username"
                    prepend-icon="mdi-account"
                    type="text"
                    :rules="['Required']"
                    hint="At least 8 characters"
                    v-model="username"
                  ></v-text-field>
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
                <v-btn color="secundary" to="/register">SIGN UP</v-btn>
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
    username: "",
    password: ""
  }),
  methods: {
    login() {
      if (this.username != "" && this.password != "") {
        const user = { username: this.username, password: this.password };
        this.$store.dispatch("auth/login", user).then(() => {
          if (this.$store.getters["auth/isLogged"]) {
            //esto es para test
            this.$router.push("/home/" + this.username);
          }
        });
      }
    }
  }
};
</script>
<style></style>
