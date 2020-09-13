<template>
  <div class="container">
    <v-card>
      <v-row>
        <v-col class="col-8">
          <div v-if="isImg">
            {{ "is img!!" }}
            <v-img
              class="bold--text align-end"
              height="100%"
              width="100%"
              v-bind:src="getBLOB"
              align="right"
            >
              <v-chip class="ma-2" color="pink" outlined>
                <v-icon dark>mdi-heart</v-icon>
                {{ frame.likes }}
              </v-chip>
            </v-img>
          </div>
          <div v-else>
            <v-tooltip left nudge-left>
              <template v-slot:activator="{ on }">
                <video
                  onclick="this.paused ? this.play() : this.pause();"
                  :key="isFrameLoaded"
                  v-on="on"
                >
                  <source v-bind:src="getBLOB" type="video/mp4" />
                  Your browser does not support the
                </video></template
              >Click to play!</v-tooltip
            >
          </div>
        </v-col>

        <v-col>
          <div>
            <v-card flat>
              <v-row
                ><p class="font-weight-light ma-6">{{ frame.text }}</p></v-row
              >
              <v-row>
                <v-form>
                  <v-tooltip right>
                    <template v-slot:activator="{ on }">
                      <v-textarea
                        prepend-inner-icon="mdi-comment"
                        class="mx-2"
                        label="Comment"
                        rows="1"
                        v-model="input"
                        v-on="on"
                        auto-grow
                      ></v-textarea> </template
                    >Make a comment!</v-tooltip
                  >
                </v-form>
              </v-row>
              <v-row class="d-flex align-end">
                <v-tooltip right>
                  <template v-slot:activator="{ on }">
                    <v-btn
                      @click.native="like"
                      class="ma-2"
                      outlined
                      color="pink"
                      v-on="on"
                      ><v-icon dark>mdi-heart</v-icon></v-btn
                    ></template
                  >Like!</v-tooltip
                >

                <v-tooltip right>
                  <template v-slot:activator="{ on }">
                    <v-btn
                      @click.native="follow"
                      class="ma-2"
                      outlined
                      color="indigo"
                      v-on="on"
                      >Follow</v-btn
                    ></template
                  >Follow!</v-tooltip
                >
              </v-row>
            </v-card>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-expansion-panels hover popout flat>
          <v-expansion-panel>
            <v-tooltip top>
              <template v-slot:activator="{ on }">
                <v-expansion-panel-header v-on="on">
                  <v-badge
                    color="primary"
                    v-bind:content="comments.length"
                    left
                    overlap
                  >
                    <v-icon color="primary" large>
                      mdi-email
                    </v-icon>
                  </v-badge>
                </v-expansion-panel-header></template
              >Show Messages!</v-tooltip
            >
            <v-expansion-panel-content>
              <message />
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-row>
    </v-card>
  </div>
</template>
<script>
import message from "@/components/Message";
import { mapGetters } from "vuex";

export default {
  components: { message },
  data() {
    return {
      idFrame: "",
      likes: "",
      text: "",
      input: "hehhe"
    };
  },
  computed: {
    ...mapGetters({
      frame: "content/frame",
      getBLOB: "content/currentBlob",
      isImg: "content/isImg",
      isFrameLoaded: "content/isFrameLoaded",
      comments: "content/comments"
    })
  },
  methods: {
    like() {
      console.log("LIKEDTO--");
      const likeDTO = {
        objectId: this.$store.getters["content/currentFrameId"],
        type: "frame"
      };
      console.log(likeDTO);
      //const likeDTO = [this.$store.getters["content/currentFrameId"], "frame"];
      this.$store.dispatch("social/like", likeDTO).then(() => {
        //this.$forceUpdate();
      });
    },
    follow() {
      //this.$route.params.username
      this.$store.dispatch("social/follow", this.$route.params.username);
    }
  },
  mounted() {
    console.log("Frame mounteedddd");
    //this.$forceUpdate();
  }
};
</script>
<style>
video {
  max-width: 100%;
  height: auto;
}
</style>
