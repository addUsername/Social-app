<template>
  <div class="container">
    <v-row fluid>
      <v-col class="ma-0 pa-0 col-3" fluid v-for="index in 10" :key="index">
        <v-card class="mx-auto primary" color="#26c6da" dark max-width="300">
          <v-card-text class="headline font-weight-bold body-2">
            "Turns out semicolon-less style is easier and safer in TS because
            most gotcha edge cases are type invalid as well."
          </v-card-text>
          <v-card-actions>
            <span>Username</span>
            <v-icon class="mr-1 left">mdi-heart</v-icon>
            <span class="subheading mr-2">256</span>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  computed: {
    messagesLength() {
      //AQUI hay que llamar a la store nueva "post.js" y pillar el home ya, distinguir entre img/video (mñn)
      //return this.$store.getters["post/getMenssagesLength"];
      return 10;
    }
  }
};
</script>

<style></style>
