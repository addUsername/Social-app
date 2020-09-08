<template>
  <div>
    <v-card class="d-inline-block mx-auto" v-click-outside="setOverlayFalse">
      <v-container>
        <v-row>
          <v-col cols="8">
            <v-img
              class="white--text align-end"
              height="600"
              width="600"
              src="https://cdn.vuetifyjs.com/images/cards/store.jpg"
              align="right"
            >
              <v-chip class="ma-2" color="pink" outlined>
                <v-icon dark>mdi-heart</v-icon>
                104
              </v-chip>
            </v-img>
          </v-col>

          <v-col>
            <div>
              <v-card flat>
                <p class="font-weight-light ma-6">
                  Vale, yo aqui tengo el ID de la img/video y los likes,
                  necesito pedir el texto y el id mensages<br />
                  En el componente mensages voy a necesitar texto, usernames y
                  likes e id que ya tendre para los likes<br />
                  (N. del T. persona que se dedica a la imprenta) desconocido
                  usó una galería de textos y los mezcló de tal manera que logró
                  hacer un libro de textos especimen. No sólo sobrevivió 500
                  años, sino que tambien ingresó como texto de relleno en
                  documentos electrónicos, quedando esencialmente igual al
                  original. Fue popularizado en los 60s con la creación de las
                  hojas "Letraset", las cuales contenian pasajes de Lorem Ipsum,
                  y más recientemente con software de autoedición, como por
                  ejemplo Aldus PageMaker, el cual incluye versiones de Lorem
                  Ipsum.
                </p>
              </v-card>
            </div>
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
                <v-col>
                  <v-card
                    class="mx-auto primary"
                    color="#26c6da"
                    dark
                    max-width="300"
                  >
                    <v-card-text class="headline font-weight-bold">
                      "Turns out semicolon-less style is easier and safer in TS
                      because most gotcha edge cases are type invalid as well."
                    </v-card-text>
                    <v-card-actions>
                      <span>Username</span>
                      <v-icon class="mr-1 left">mdi-heart</v-icon>
                      <span class="subheading mr-2">256</span>
                    </v-card-actions>
                  </v-card>
                </v-col>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>
<script>
export default {
  methods: {
    setOverlayFalse() {
      this.$store.dispatch("content/setOverlayFalse");
    }
  }
};
</script>

<style></style>
