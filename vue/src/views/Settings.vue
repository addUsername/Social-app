<template>
  <v-app id="inspire">
    <v-card>
      <v-toolbar flat color="secundary" dark> </v-toolbar>
      <v-tabs vertical>
        <v-tab> <v-icon left>mdi-account</v-icon>Profile </v-tab>
        <v-tab> <v-icon left>mdi-lock</v-icon> security </v-tab>
        <v-tab> <v-icon left>mdi-access-point</v-icon> Media </v-tab>

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
                <v-tooltip color="primary" top>
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      label="Description"
                      prepend-icon="mdi-text"
                      :rules="['Required']"
                      v-model="textHome"
                      v-on="on"
                    ></v-text-field> </template
                  >Sometimes you don’t have to use many words to get your point
                  across</v-tooltip
                >
                <v-file-input
                  accept="image/png, image/jpeg"
                  placeholder="Home"
                  prepend-icon="mdi-camera"
                  v-model="fileBig"
                ></v-file-input>
                <v-card-subtitle>Type password and confirm</v-card-subtitle>
              </v-card-text>
              <v-card-actions>
                <v-col lg="7" sm="12">
                  <v-text-field
                    id="password"
                    label="Type password to confirm"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    :rules="['Required']"
                    v-model="password"
                  ></v-text-field>
                </v-col>
                <v-col lg="7" sm="12">
                  <v-btn
                    color="primary"
                    type="submit"
                    form="connect-submit-btn-to form"
                    >Update</v-btn
                  ></v-col
                >
              </v-card-actions>
            </v-card>
          </v-row>
        </v-tab-item>
        <v-tab-item>
          <v-row align="center" justify="center">
            <v-card flat class="col-6">
              <v-card-title>Change credentials</v-card-title>
              <v-card-text>
                <v-card-subtitle>
                  In order to change your name or username
                  <v-tooltip bottom>
                    <template v-slot:activator="{ on }">
                      <a target="_blank" href="#" @click.stop v-on="on">
                        contact us
                      </a>
                    </template>
                    New tab
                  </v-tooltip>
                  please</v-card-subtitle
                >
                <v-text-field
                  label="New email"
                  name="email"
                  prepend-icon="mdi-email"
                  type="email"
                  v-model="email"
                ></v-text-field>
                <v-text-field
                  label="New Password"
                  name="password"
                  prepend-icon="mdi-lock"
                  type="password"
                  v-model="password"
                ></v-text-field>
                <v-text-field
                  id="password2"
                  label="Repeat password"
                  name="password2"
                  prepend-icon="mdi-lock"
                  type="password"
                  v-model="password2"
                ></v-text-field>
              </v-card-text>
              <v-card-title>Account</v-card-title>
              <v-card-text>
                <v-checkbox v-model="checkboxSuspend">
                  <template v-slot:label>
                    <v-card flat>
                      <v-card-title>Suspend account</v-card-title>
                      <v-card-subtitle>
                        <p>
                          Your account and media related files will not be
                          deleted. But your home, list friends and PMs will be
                          disabled and we will stop emailing you feedback.
                        </p>
                        <p>
                          of course you are allowed to come back whenever you
                          please, we are waiting!
                        </p>
                      </v-card-subtitle>
                    </v-card>
                  </template>
                </v-checkbox>
                <v-checkbox v-model="checkboxDelete">
                  <template v-slot:label>
                    <v-card flat>
                      <v-card-title>Delete account</v-card-title>
                      <v-card-subtitle>
                        <p>
                          Everything about you here will be deleted, your
                          username and email will be blocked forever.
                        </p>
                        <p>
                          (This procces could take up tu 1 hour to complete)
                        </p>
                      </v-card-subtitle>
                    </v-card>
                  </template>
                </v-checkbox>
              </v-card-text>
              <v-card-actions>
                <v-col lg="7" sm="12">
                  <v-text-field
                    id="password"
                    label="Type password to confirm"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    :rules="['Required']"
                    v-model="password"
                  ></v-text-field>
                </v-col>
                <v-col lg="7" sm="12">
                  <v-btn
                    color="primary"
                    type="submit"
                    form="connect-submit-btn-to form"
                    >Update</v-btn
                  ></v-col
                >
              </v-card-actions>
            </v-card>
          </v-row>
        </v-tab-item>
        <v-tab-item>
          <v-row align="center" justify="center">
            <v-card flat class="col-6">
              <v-card-title>My media</v-card-title>
              <v-card-text>
                <v-card-subtitle> Here should be the same </v-card-subtitle>
                <v-sheet class="mx-auto" elevation="flat" max-width="800">
                  <v-slide-group v-model="model" center-active show-arrows>
                    <v-slide-item
                      v-for="n in 15"
                      :key="n"
                      v-slot:default="{ active, toggle }"
                    >
                      <v-card
                        :color="active ? 'primary' : 'grey lighten-1'"
                        class="ma-2"
                        height="200"
                        width="100"
                        @click="toggle"
                      >
                        <v-card align="center" justify="center">
                          <h3>imagen</h3>
                          <br />
                          <br />
                          <br />
                          <br />
                          <v-text> something</v-text>
                        </v-card>
                      </v-card>
                    </v-slide-item>
                  </v-slide-group>
                </v-sheet>
              </v-card-text>
            </v-card>
          </v-row>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </v-app>
</template>

<script>
export default {};
</script>

<style></style>
