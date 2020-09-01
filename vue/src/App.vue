<template>
  <v-app>
    <!--
      <Login />
       -->
       <transition>
        <div>
          <router-link to="/login">login</router-link>
          <router-link to="/about">about</router-link>
          <router-link to="/">home</router-link>
          <keep-alive>
          <router-view></router-view>
        </keep-alive>
        </div>
        
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
