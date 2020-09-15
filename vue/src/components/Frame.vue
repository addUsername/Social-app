<template>
  <div class="container">
    <v-snackbar color="success" v-model="snackBar">
      {{ message }}
    </v-snackbar>
    <v-card>
      <v-row>
        <v-col :key="isImg" class="col-8">
          <div v-if="isImg">
            <v-img
              class="bold--text align-end"
              height="100%"
              width="100%"
              v-bind:src="getBLOB"
              align="right"
            >
              <v-chip class="ma-2" color="pink" outlined>
                <v-icon dark>mdi-heart</v-icon>
                {{ frame.likes }} hii
              </v-chip>
            </v-img>
          </div>
          <div v-else>
            <v-tooltip left nudge-left>
              <template v-slot:activator="{ on }">
                <video
                  v-bind:src="getBLOB"
                  onclick="this.paused ? this.play() : this.pause();"
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
                        :rows="6"
                        label="Comment"
                        v-model="input"
                        v-on="on"
                        auto-grow
                        ><v-icon @click.native="sendComment"
                          >mdi-email-send</v-icon
                        ></v-textarea
                      > </template
                    >Make a comment!</v-tooltip
                  >

                  <v-icon @click.native="sendComment">mdi-email-send</v-icon>
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
      input: "jjj",
      snackBar: false,
      message: ""
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
      const likeDTO = {
        objectId: this.$store.getters["content/currentFrameId"],
        type: "frame"
      };
      //const likeDTO = [this.$store.getters["content/currentFrameId"], "frame"];
      this.$store.dispatch("social/like", likeDTO).then(response => {
        this.message = response;
        this.snackBar = true;
      });
    },
    follow() {
      //this.$route.params.username
      this.$store
        .dispatch("social/follow", this.$route.params.username)
        .then(response => {
          this.message = response;
          this.snackBar = true;
        });
    },
    sendComment() {
      const myObj = {
        text: this.input,
        username: this.$route.params.username,
        idFrame: this.$store.getters["content/currentFrameId"]
      };
      this.$store.dispatch("social/sendComment", myObj).then(response => {
        this.message = response;
        this.snackBar = true;
      });
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
