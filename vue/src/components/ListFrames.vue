<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col v-for="(pair, index) in getData" :key="index">
          <v-card class="mx-auto">
            <div small @click="changeOverlay">
              <v-img
                class="white--text align-end"
                v-bind:src="pair.value"
                align="right"
                max-width="400"
              >
                <v-chip class="ma-2" color="pink" outlined>
                  <v-icon dark>mdi-heart</v-icon>
                  {{ pair["like"] }}
                </v-chip>
              </v-img>
            </div>
          </v-card>

          <!-- aqui dentro va frame y message, ademas aqui hay que implementar el click outside-->
        </v-col>
        <v-overlay :value="getOverlay">
          <div class="pa-16">
            <frame />
          </div>
        </v-overlay>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import frame from "@/components/Frame";
export default {
  name: "listFrames",
  components: { frame },
  computed: {
    getData() {
      return this.$store.getters["content/thumbFrame"];
    },
    getOverlay() {
      return this.$store.getters["content/overlay"];
    }
  },
  methods: {
    init() {
      console.log("iniit Lisst");
      //this.frames = this.$store.getters["content/thumbnails"];
    },
    changeOverlay() {
      this.$store.dispatch("content/changeOverlay");
    }
  },
  mounted() {
    this.init();
  }
};
</script>

<style></style>
