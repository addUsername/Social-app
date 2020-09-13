<template>
  <div class="container">
    <v-row fluid>
      <v-col
        class="ma-0 pa-0 col-3"
        fluid
        v-for="(comment, index) in comments"
        :key="index"
      >
        <v-card class="mx-auto primary" color="#26c6da" dark max-width="300">
          <v-card-text class="headline font-weight-bold body-2">
            {{ comment.text }}
          </v-card-text>
          <v-card-actions>
            <span>{{ comment.username }}</span>
            <v-spacer></v-spacer>
            <v-tooltip right>
              <template v-slot:activator="{ on }">
                <v-btn v-on="on" @click.native="like(comment.id)" text>
                  <span class="subheading mr-2"
                    >{{ comment.likes
                    }}<v-icon class="ma-0 pa-0">mdi-heart</v-icon></span
                  >
                </v-btn>
              </template>
              Like!
            </v-tooltip>

            <div v-if="comment.edited">
              <v-tooltip right>
                <template v-slot:activator="{ on }">
                  <v-icon v-on="on">mdi-barcode</v-icon></template
                >{{ "Edited @: " + comment.date }}</v-tooltip
              >
            </div>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters({
      comments: "content/comments"
    })
  },
  methods: {
    like(messageId) {
      const likeDTO = {
        objectId: messageId,
        type: "message"
      };
      console.log(likeDTO);
      //const likeDTO = [this.$store.getters["content/currentFrameId"], "frame"];
      this.$store.dispatch("social/like", likeDTO).then(() => {
        //this.$forceUpdate();
      });
    }
  }
};
</script>

<style></style>
