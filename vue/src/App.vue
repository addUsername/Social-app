<template>
  <v-app id="inspire">
    <!--
      <Login />
       -->
    <v-app-bar color="primary" dense dark>
      <v-app-bar-nav></v-app-bar-nav>
      <v-toolbar-title>{{
        $store.getters["content/home"].username
      }}</v-toolbar-title>
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
export default {
  name: "App",
  components: {},
  data: () => {}
};
</script>
<style></style>
