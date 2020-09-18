<template>
  <v-app>
    <v-snackbar color="success" v-model="snackBar">
      {{ message }}
    </v-snackbar>
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col lg="7" sm="12">
          <v-card
            color="primary"
            v-cloak
            @drop.prevent="addFile"
            @dragover.prevent
          >
            <v-card-title>Choose video or photo</v-card-title>
            <v-card-subtitle>(Drag it over)</v-card-subtitle>
            {{ file.name }}
            <v-card-actions>
              <v-file-input
                accept="image/png, image/jpeg, video/mp4"
                placeholder=""
                prepend-icon="mdi-camera"
                v-model="file"
              ></v-file-input>
              <v-btn @click="removeFile(file)" title="Remove">X</v-btn>
              <v-btn color="secondary" @click="upload">Upload</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col lg="7" sm="12">
          <v-card>
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
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      file: "",
      input: "",
      snackBar: false,
      message: ""
    };
  },
  methods: {
    addFile(e) {
      let droppedFiles = e.dataTransfer.files;
      if (!droppedFiles) return;
      this.file = droppedFiles[0];
    },
    removeFile() {
      this.file = "";
    },
    upload() {
      if (this.file == "") {
        this.message = "No file? :c";
        this.snackBar = true;
        return;
      }
      if (this.input == "") {
        this.message = "No text? :c";
        this.snackBar = true;
        return;
      }
      const myObj = {
        file: this.file,
        media: {
          username: this.$store.getters["auth/user"].username,
          text: this.input,
          docType: this.file.type,
          isHome: false,
          frameId: -1
        }
      };
      this.$store.dispatch("content/uploadFrame", myObj).then(response => {
        this.message = response;
        this.snackBar = true;
        this.file = "";
        this.input = "";
      });
    }
  }
};
</script>

<style></style>
