<template>
  <v-app id="inspire">
    <!--
      <Login />
       -->
    <v-app-bar color="primary" dense dark>
      <v-app-bar-nav></v-app-bar-nav>
      <v-toolbar-title>Page title</v-toolbar-title>
      <v-spacer></v-spacer>

      <router-link to="/login">
        <v-btn>
          <span>IN</span>
        </v-btn>
      </router-link>
      <router-link to="/register">
        <v-btn>
          <span>UP</span>
        </v-btn></router-link
      >
    </v-app-bar>
    <v-main>
      <keep-alive>
        <router-view></router-view>
      </keep-alive>
    </v-main>
  </v-app>
</template>

<script>
//import Login from "@/views/Login";
//import Register from "@/views/Register";

export default {
  name: "App",

  components: {
    //Login,
    //Register
  },

  data: () => ({
    //
  })
};
</script>
<style></style>
