<template>
  <v-app
    id="inspire"
    :style="{ background: $vuetify.theme.themes.dark.background }"
  >
    <v-app-bar color="primary">
      <v-spacer></v-spacer>
      <v-switch
        color="secondary"
        class="pa-5"
        v-model="switch1"
        :label="`Switch : ${switch1.toString()}`"
      ></v-switch>
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
      switch1: true
    };
  },
  computed: {
    ...mapGetters({
      isLogged: "auth/isLogged"
    })
  },
  methods: {
    theme() {
      return this.$vuetify.theme.dark ? "dark" : "light";
    }
  }
};
</script>
<style></style>
