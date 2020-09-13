<template>
  <v-app>
    <v-container>
      <v-row>
        <v-dialog v-model="dialog" width="80%">
          <template v-slot:activator="{ on, attrs }">
            <v-col v-for="(pair, index) in getData" :key="index">
              <div>
                <v-card class="mx-auto">
                  <div v-bind="attrs" v-on="on">
                    <v-img
                      v-on:click="setFrameId(pair.key)"
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
              </div>
            </v-col>
          </template>
          <frame />
        </v-dialog>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import frame from "@/components/Frame";
export default {
  name: "listFrames",
  components: { frame },
  data() {
    return {
      dialog: false
    };
  },
  computed: {
    getData() {
      return this.$store.getters["content/thumbFrame"];
    },
    getDialog() {
      return this.dialog;
    }
  },
  methods: {
    init() {
      console.log("iniit Lisst ###################");
    },
    setFrameId(frameId) {
      this.$store.dispatch("content/setFrameId", frameId);
      this.getFrame();
    },
    getFrame() {
      this.$store
        .dispatch("content/getFrame", {
          username: this.$route.params.username,
          frameId: this.$store.getters["content/currentFrameId"]
        })
        .then(() => {
          this.$store.dispatch("content/getFrameBlob");
        });
    }
  },
  mounted() {
    this.init();
  }
};
</script>

<style></style>
