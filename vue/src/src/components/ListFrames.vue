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
import content from "@/services/contentService";

export default {
  name: "listFrames",
  props: ["username"],
  data: () => ({
    frames: [],
    username: ""
  }),
  methods: {
    init() {
      console.log("iniit Lisst");
      this.frames = this.$parent.thumbImgs;
      console.log(this.frames);
    },
    getThumb(id) {
      content.getThumbImg(id, this.username).then(response => {
        this.bigImgUrl = URL.createObjectURL(new Blob([response.data]));
        console.log(response);
      });
    }
  },
  mounted() {
    this.init();
  }
};
</script>

<style></style>
