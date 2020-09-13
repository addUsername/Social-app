<template>
  <v-navigation-drawer app clipped permanent expand-on-hover color="primary">
    <v-list>
      <v-list-item class="px-2">
        <v-list-item-avatar>
          <v-img src="https://randomuser.me/api/portraits/women/85.jpg"></v-img>
        </v-list-item-avatar>
      </v-list-item>

      <v-list-item link>
        <v-list-item-content>
          <v-list-item-title class="title">{{
            $store.getters["content/home"].username
          }}</v-list-item-title>
          <v-list-item-subtitle>Logged In</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-list>

    <v-divider></v-divider>

    <v-list nav dense>
      <v-list-item link to="/upload">
        <v-list-item-icon>
          <v-icon>mdi-upload</v-icon>
        </v-list-item-icon>
        <v-list-item-title>Say something!</v-list-item-title>
      </v-list-item>
      <v-list-item link v-bind:to="getLinkHome">
        <v-list-item-icon>
          <v-icon>mdi-home</v-icon>
        </v-list-item-icon>
        <v-list-item-title>Home</v-list-item-title>
      </v-list-item>
      <v-list-item link to="/settings">
        <v-list-item-icon>
          <v-icon>mdi-cog</v-icon>
        </v-list-item-icon>
        <v-list-item-title>Settings</v-list-item-title>
      </v-list-item>
      <v-list-item link>
        <v-list-item-icon>
          <v-icon>mdi-account-off</v-icon>
        </v-list-item-icon>
        <div @click.prevent="logout">
          <v-list-item-title>Logout</v-list-item-title>
        </div>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
export default {
  /*    
      <div>        
        <v-btn color="primary" to="/login">IN</v-btn>
        <v-btn color="primary" to="/register">UP</v-btn>
        <v-btn color="secundary" @click.prevent="logout">OUT</v-btn>
      </div> */
  data() {
    return {
      //borrar
      linkHome: "/home/" + this.$store.getters["auth/user"].username
    };
  },
  computed: {
    getLinkHome() {
      return "/home/" + this.$store.getters["auth/user"].username;
    }
  },
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/login");
    }
  }
};
</script>

<style></style>
