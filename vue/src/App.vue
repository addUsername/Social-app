<template>
  <v-app>
      <Register />
  </v-app>
</template>

<script>
//import Login from "./views/Login";
import Register from "./views/Register";

export default {
  name: "App",

  components: {
    Register
  },

  data: () => ({
    //
  })
};
</script>
