<template>
  <div>
    <v-snackbar left top color="secundary" v-model="snackBar">
      {{ message }}
    </v-snackbar>
    <v-navigation-drawer
      app
      clipped
      permanent
      expand-on-hover
      class="hidden-sm-and-down fill-height"
      color="primary"
    >
      <v-list>
        <v-list-item class="px-2">
          <v-list-item-avatar>
            <v-img
              v-bind:src="avatar"
            ></v-img>
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
        <v-list-item
          color="secondary"
          @click.prevent="toUpload"
          link
          to="/upload"
        >
          <v-list-item-icon>
            <v-icon>mdi-upload</v-icon>
          </v-list-item-icon>
          <v-list-item-title>Say something!</v-list-item-title>
        </v-list-item>
        <v-list-item
          color="secondary"
          @click.prevent="toHome"
          link
          :to="getLinkHome"
        >
          <v-list-item-icon>
            <v-icon>mdi-home</v-icon>
          </v-list-item-icon>
          <v-list-item-title>Home</v-list-item-title>
        </v-list-item>
        <v-list-item
          color="secondary"
          @click.prevent="toSettings"
          link
          to="/settings"
        >
          <v-list-item-icon>
            <v-icon>mdi-cog</v-icon>
          </v-list-item-icon>
          <v-list-item-title>Settings</v-list-item-title>
        </v-list-item>
        <v-list-item link>
          <v-list-item-icon>
            <v-icon>mdi-account-off</v-icon>
          </v-list-item-icon>
          <div color="secondary" @click.prevent="logout">
            <v-list-item-title>Logout</v-list-item-title>
          </div>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-bottom-navigation app class="d-flex d-md-none">
      <v-btn link to="/upload">
        <v-icon>mdi-upload</v-icon>
      </v-btn>

      <v-btn link :to="getLinkHome">
        <v-icon>mdi-home</v-icon>
      </v-btn>

      <v-btn link to="/settings">
        <v-icon>mdi-cog</v-icon>
      </v-btn>
      <v-btn @click.prevent="logout">
        <v-icon>mdi-account-off</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      //borrar
      linkHome: "/home/" + this.$store.getters["auth/user"].username,
      snackBar: false,
      message: "",
      logoutCount: 0
    };
  },
  computed: {
    getLinkHome() {
      return "/home/" + this.$store.getters["auth/user"].username;
    },
    ...mapGetters({
      avatar: "content/avatarBlob"
    })
  },
  methods: {
    logout() {
      if (this.logoutCount == 0) {
        this.logoutCount++;
        this.message = "Please, click again to confirm";
        this.snackBar = true;
        return;
      }
      this.$store.dispatch("auth/logout");
      this.$router.push("/login");
    },
    toUpload() {
      this.message = "Woah!";
      this.snackBar = true;
    },
    toHome() {
      this.message = "there is no place like [::1]..";
      this.snackBar = true;
    },
    toSettings() {
      this.message = "tune up dis beach";
      this.snackBar = true;
    }
  }
};
</script>

<style></style>
