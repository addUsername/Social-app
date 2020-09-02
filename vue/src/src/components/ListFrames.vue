<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col v-for="(frame, index) in frames" :key="index">
          <v-card class="mx-auto">
            <v-img
              class="white--text align-end"
              src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
              align="right"
              max-width="400"
            />
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
//import auth from "@/services/authService";

export default {
  name: "listFrames",
  data: () => ({
    frames: ["uno", "dos", "tres", "cuatro", "cinco"]
  }),
  methods: {
    logout() {}
  }
};
</script>

<style></style>
