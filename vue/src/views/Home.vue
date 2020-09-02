<template>
  <v-app id="hii">
    <v-container fluid>
      <v-row no-gutters>
        <v-col cols="12">
          <v-card class="mx-auto">
            <v-img
              class="white--text align-end"
              src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
              height="400px"
              align="right"
            >
              <v-avatar :tile="tile" size="180" color="grey lighten-4">
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
import auth from "@/services/authService";
import list from "@/components/ListFrames";
import content from "@/services/contentService";

export default {
  name: "Home",
  data: () => ({
    username: ""
    
  }),
  components: { list },
  methods: {
    logout() {
      auth.logout();
    },
    init() {
      /*
      if (this.$route.username === undefined) {
        this.$route.push(
          "/home/" + JSON.parse(localStorage.getItem("user")).username
        );
      }
      */
      console.log("init()!!!!");
      //Cogemos el username del path api/home/{username}
      //content.getUserFrontPage(this.$route.username).then(response => {
      console.log(this.$route.params.username);
      content.getUserFrontPage(this.$route.params.username).then(response => {
        console.log(response);
      });
    }
  },
  mounted() {
    this.init();
  }
};
</script>
