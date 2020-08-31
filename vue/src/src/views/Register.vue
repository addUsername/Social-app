<template>
  <v-app id="inspire">
    <v-main>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4" >
            <v-card class="elevation-6">
              <v-toolbar color="primary" dark flat>
                <v-toolbar-title>Register form</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-tooltip bottom>
                  <template v-slot:activator="{ on }">
                    <v-btn
                      :href="source"
                      icon
                      large
                      target="_blank"
                      v-on="on"
                    >
                      <v-icon>mdi-code-tags</v-icon>
                    </v-btn>
                  </template>
                  <span>Source</span>
                </v-tooltip>
              </v-toolbar>
              <v-card-text>
                <v-form @submit.prevent="register" id="connect-submit-btn-to form">
                  <v-text-field
                    label="User"
                    name="user"
                    prepend-icon="mdi-user"
                    type="text"
                    :rules="['Required']"
                  ></v-text-field>
                  <v-text-field
                    label="Username"
                    name="username"
                    prepend-icon="mdi-user"
                    type="text"
                    :rules="['Required']"
                  ></v-text-field>
                  <v-text-field
                    label="Email"
                    name="email"
                    prepend-icon="mdi-email"
                    type="email"
                    :rules="['Required']"
                  ></v-text-field>
                  <v-text-field
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    :rules="['Required']"
                  ></v-text-field>
                  <v-text-field
                    id="password"
                    label="Repeat Password"
                    name="password2"
                    prepend-icon="mdi-lock"
                    type="password"
                    :rules="['Required']"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" type="submit" form="connect-submit-btn-to form">Register</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import auth from "@/services/authService";
export default {
  data: ()=>({
      name: "",
      username: "",
      password: "",
      password2: "",
      email: ""
    }),
    methods: {
      register(){
        auth.register(this.name, this.username, this.email, this.password)
          .then(response => {
              alert(response);
        })
      }
    }

}
</script>

<style>

</style>