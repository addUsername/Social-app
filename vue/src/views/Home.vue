<template>
  <v-app id="hii">
    <v-container fluid>
      <v-row no-gutters>
        <v-col cols="12">
          <v-card class="mx-auto">
            <v-img
              class="white--text align-end"
              v-bind:src="bigImgUrl"
              height="400px"
              align="right"
            >
              <v-avatar size="180" color="grey lighten-4">
                <img
                  src="https://vuetifyjs.com/apple-touch-icon-180x180.png"
                  alt="avatar"
                />
              </v-avatar>
            </v-img>
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="6">
          <v-card>
            <v-card-title>this is User description</v-card-title>
            <v-card-subtitle
              >you are {{ $store.getters["auth/user"].username }} <br />
              you likes:</v-card-subtitle
            >
            <v-card-actions
              ><v-btn @click.prevent="logout">Log out</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col cols="6">
          <v-card>
            <v-card-title>TEXT</v-card-title>
            <v-card-subtitle>
              <p>
                {{ $store.getters["content/home"].text }}
              </p>
            </v-card-subtitle>
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

export default {
  name: "Home",
  data: () => ({
    bigImgUrl: ""
  }),
  components: { list },
  computed: {
    childDataLoaded() {
      return this.$store.getters["content/isThumbnailLoaded"];
    }
  },
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
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
        });
    }
  },
  mounted() {
    this.init();
  }
};
</script>
