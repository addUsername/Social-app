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
              >you are {{ username }} <br />
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
                Here we should put your text more text and more text
              </p>
            </v-card-subtitle>
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <list />
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
// @ is an alias to /src

import list from "@/components/ListFrames";
import content from "@/services/contentService";

export default {
  name: "Home",
  data: () => ({
    username: "",
    response: [],
    bigImgUrl: "",
    thumbImgs: []
  }),
  components: { list },
  methods: {
    logout() {
      this.$store.dispatch("logout");
    },
    init() {
      console.log("init()!!!!");
      //Cogemos el username del path api/home/{username}
      //content.getUserFrontPage(this.$route.username).then(response => {
      content.getUserFrontPage(this.$route.params.username).then(response => {
        console.log(response.data);
        this.response = response.data;
        this.thumbImgs = this.response.imgs;
        console.log(this.thumbImgs);
        content
          .getBigImg(this.response.img, this.response.username)
          .then(response2 => {
            // this.bigImgUrl = Buffer.from(response.data, "binary").toString("base64" );
            this.bigImgUrl = URL.createObjectURL(new Blob([response2.data]));
          });
        //aqui un for que nos vaya pidiendo las pequeñas asyncronamemte tb oq
      });
    }
  },
  mounted() {
    this.init();
  }
};
</script>
