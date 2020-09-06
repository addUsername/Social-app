<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col v-for="(frame, index) in myValue" :key="index">
          <v-card class="mx-auto">
            <v-img
              class="white--text align-end"
              v-bind:src="frame"
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
//import content from "@/services/contentService";

export default {
  name: "listFrames",
  data: () => ({
    frames: []
  }),
  computed: {
  myValue() { return this.$store.getters["content/thumbnails"] }
  },
  methods: {
    init() {
      console.log("iniit Lisst");
      this.frames = this.$store.getters["content/thumbnails"];
      console.log(this.frames);
    }
  },
  mounted() {
    this.init();
  }
};
</script>

<style></style>
