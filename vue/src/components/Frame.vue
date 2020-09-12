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
            <video>
              <source v-bind:src="getBLOB" type="video/mp4" />
              Your browser does not support the
            </video>
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
                  <v-textarea
                    prepend-inner-icon="mdi-comment"
                    class="mx-2"
                    label="Comment"
                    rows="1"
                    v-model="input"
                    auto-grow
                  ></v-textarea>
                </v-form>
              </v-row>
              <v-row class="d-flex align-end">
                <v-btn @click.native="like" class="ma-2" outlined color="pink"
                  ><v-icon dark>mdi-heart</v-icon></v-btn
                >
                <v-btn
                  @click.native="follow"
                  class="ma-2"
                  outlined
                  color="indigo"
                  >Follow</v-btn
                >
              </v-row>
            </v-card>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-expansion-panels hover popout flat>
          <v-expansion-panel>
            <v-expansion-panel-header>
              <v-badge color="primary" content="99" left overlap>
                <v-icon color="primary" large>
                  mdi-email
                </v-icon>
              </v-badge>
            </v-expansion-panel-header>
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
      input: ""
    };
  },
  computed: {
    ...mapGetters({
      frame: "content/frame",
      getBLOB: "content/currentBlob",
      isImg: "content/isImg",
      isFrameLoaded: "content/isFrameLoaded"
    })
  },
  methods: {
    like() {
      console.log("LIKEDTO-----");
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
