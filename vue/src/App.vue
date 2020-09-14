<template>
  <v-app id="inspire">
    <v-snackbar color="success" v-model="snackBar">
      {{ message }}
    </v-snackbar>
    <v-app-bar color="primary">
      <v-spacer></v-spacer>
      <v-switch  color="secondary" v-model="$vuetify.theme.dark" primary label="Dark"></v-switch>
    </v-app-bar>

    <v-row>
      <template v-if="isLogged">
        <v-col class="ma-0 pa-0 col-1">
          <drawer />
        </v-col>
      </template>
      <v-col class="ma-0 pa-0 col-12">
        <v-main>
          <keep-alive>
            <router-view></router-view>
          </keep-alive>
        </v-main>
      </v-col>
    </v-row>
  </v-app>
</template>

<script>
import drawer from "@/components/Drawer";
import { mapGetters } from "vuex";

export default {
  name: "App",
  components: { drawer },
  data() {
    return {
      snackBar: false,
      message: ""
    };
  },
  computed: {
    ...mapGetters({
      isLogged: "auth/isLogged"
    })
  },
  methods: {}
};
</script>
<style>
#app {
  background-color: var(--v-background-base);
}
</style>
