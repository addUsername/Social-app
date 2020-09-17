<template>
  <v-app>
    <v-snackbar color="success" v-model="snackBar">
      {{ message }}
    </v-snackbar>
    <v-container fluid class="pa-5">
      <v-row no-gutters>
        <v-col cols="12">
          <v-card flat class="mx-auto">
            <v-img
              class="white--text align-end"
              v-bind:src="bigImgUrl"
              height="400px"
              align="right"
              alt="ppalimage"
            >
              <v-avatar size="180" color="grey lighten-4">
                <img v-bind:src="avatarBlob" alt="avatar" />
              </v-avatar>
            </v-img>
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <v-col lg="6" sm="12">
          <v-card outlined class="pa-5 ma-0">
            <v-card-title
              ><p class="font-weight-bold">
                {{ $store.getters["auth/user"].username }}
              </p></v-card-title
            >
            <v-card-subtitle>
              {{ $store.getters["content/home"].text }}
            </v-card-subtitle>
            <v-card-actions>
              <v-tooltip top>
                <template v-slot:activator="{ on }">
                  <v-btn
                    v-on="on"
                    @click.native="like"
                    class="ma-2"
                    outlined
                    color="pink"
                    ><v-icon dark>mdi-heart</v-icon></v-btn
                  ></template
                >Like!</v-tooltip
              >
              <v-tooltip top>
                <template v-slot:activator="{ on }">
                  <v-btn
                    v-on="on"
                    @click.native="follow"
                    class="ma-2"
                    outlined
                    color="indigo"
                    >Follow</v-btn
                  ></template
                >Follow!</v-tooltip
              >
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col lg="6" sm="12">
          <v-card flat color="primary" height="100%">
            <v-card-title>List friends overhere</v-card-title>
            <v-card-subtitle>
              Maybe i should make a v-card and putting it in a
              list</v-card-subtitle
            >
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <template v-if="childDataLoaded">
          <list />
        </template>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
// @ is an alias to /src

import list from "@/components/ListFrames";
import { mapGetters } from "vuex";

export default {
  name: "Home",
  data: () => ({
    bigImgUrl: "",
    snackBar: true,
    message: "Hey! hello!"
  }),
  components: { list },
  computed: {
    ...mapGetters({
      childDataLoaded: "content/isThumbnailLoaded",
      isFrameLoaded: "content/isFrameLoaded",
      avatarBlob: "content/avatarBlob"
    })
  },
  methods: {
    like() {
      const likeDTO = {
        objectId: this.$store.getters["content/"],
        type: "frame"
      };
      this.$store.dispatch("social/like", likeDTO).then(response => {
        this.message = response;
        this.snackBar = true;
      });
    },
    follow() {
      this.$store
        .dispatch("social/follow", this.$route.params.username)
        .then(response => {
          this.message = response;
          this.snackBar = true;
        });
    },
    init() {
      console.log("init home!!!!");
      //Cogemos el username del path api/home/{username}
      this.$store
        .dispatch("content/getUserFrontPage", this.$route.params.username)
        .then(() => {
          this.$store.dispatch("content/getBigImg").then(img => {
            this.bigImgUrl = img;
          });
          this.$store.dispatch("content/getThumbnails");
          this.$store.dispatch("content/getAvatar", this.$store.getters["auth/user"].username);
        });
    }
  },
  mounted() {
    this.init();
  }
};
</script>
