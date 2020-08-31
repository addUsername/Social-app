<template>
  <v-app>
    <!--
      <Login />
       -->
       <transition>
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </transition>
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
