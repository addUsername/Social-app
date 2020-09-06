<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col v-for="(pair, index) in getData" :key="index">
          <v-card class="mx-auto">
            <v-img
              class="white--text align-end"
              v-bind:src="pair.value"
              align="right"
              max-width="400"
            />
            <p>{{ pair }}</p>
            <p>{{ pair["value"] }}</p>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: "listFrames",
  computed: {
    getData() {
      console.log("get data from listFrames");
      return this.$store.getters["content/thumbFrame"];
    }
  },
  methods: {
    init() {
      console.log("iniit Lisst");
      //this.frames = this.$store.getters["content/thumbnails"];
    }
  },
  mounted() {
    this.init();
  }
};
</script>

<style></style>
