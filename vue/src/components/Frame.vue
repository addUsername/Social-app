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
                {{ getLikes }}
              </v-chip>
            </v-img>
          </div>
          <div v-else>
            {{ "is viddeeo" }}
            <video controls>
              {{ getBLOB }}
              <source v-bind:src="getBLOB" type="video/mp4" />
              Your browser does not support the video tag.
            </video>
          </div>
        </v-col>

        <v-col>
          <div>
            <v-card flat>
              <v-row
                ><p class="font-weight-light ma-6">{{ getText }}</p></v-row
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
export default {
  components: { message },
  data() {
    return {
      idFrame: "",
      likes: "",
      text: ""
    };
  },
  computed: {
    getText() {
      return this.$store.getters["content/frame"].text;
    },
    getLikes() {
      return this.$store.getters["content/frame"].likes;
    },
    getBLOB() {
      return this.$store.getters["content/currentBlob"];
    },
    isImg() {
      return this.$store.getters["content/isImg"];
    }
  },
  methods: {}
};
</script>
<style></style>
