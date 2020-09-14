<template>
  <v-app id="inspire">
    <v-card>
      <v-toolbar flat color="primary" dark>
        <v-toolbar-title>User Profile</v-toolbar-title>
      </v-toolbar>
      <v-tabs vertical>
        <v-tab>
          <v-icon left>mdi-account</v-icon>Profile
        </v-tab>
        <v-tab>
          <v-icon left>mdi-lock</v-icon> security
        </v-tab>
        <v-tab>
          <v-icon left>mdi-access-point</v-icon>  Media
        </v-tab>
  
        <v-tab-item>
           <v-row align="center" justify="center">
          <v-card flat class="col-6">
              <v-card-title>Edit Home</v-card-title>
              <v-card-text>
                <v-file-input
                accept="image/png, image/jpeg"
                placeholder="Avatar"
                prepend-icon="mdi-camera"
                v-model="fileAvatar"
                ></v-file-input>
                <v-file-input
                accept="image/png, image/jpeg"
                placeholder="Description"
                prepend-icon="mdi-text"
                v-model="textHome"
                ></v-file-input>
                <v-file-input
                accept="image/png, image/jpeg"
                placeholder="Home"
                prepend-icon="mdi-camera"
                v-model="fileBig"
                ></v-file-input>
              <v-card-title>Edit post</v-card-title>
              </v-card-text>
              <v-card-actions>
                <v-text-field
                    id="password"
                    label="Type password to confirm"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    :rules="['Required']"
                    v-model="password"
                  ></v-text-field>
                <spacer></spacer>
                <v-btn
                  color="primary"
                  type="submit"
                  form="connect-submit-btn-to form"
                  >Update</v-btn
                >
              </v-card-actions>
            </v-card>
             </v-row>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text>              
              <p>
                Etiam ut purus mattis mauris sodales aliquam. Ut varius tincidunt libero. Aenean viverra rhoncus pede. Duis leo. Fusce fermentum odio nec arcu.
              </p>
            </v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text>
              <p class="mb-0">
                Cras sagittis. Phasellus nec sem in justo pellentesque facilisis. Proin sapien ipsum, porta a, auctor quis, euismod ut, mi. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nam at tortor in tellus interdum sagittis.
              </p>
            </v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </v-app>
</template>

<script>
export default {

}
</script>

<style>

</style>