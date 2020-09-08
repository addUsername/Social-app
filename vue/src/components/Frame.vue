<template>
  <v-app>
    <v-card class="d-inline-block mx-auto">
      <v-container>
        <v-row>
          <v-col cols="8">
            <v-img
              height="600"
              width="600"
              src="https://cdn.vuetifyjs.com/images/cards/store.jpg"
            >
              <v-chip class="ma-2" color="pink" outlined>
                <v-icon dark>mdi-heart</v-icon>
                104
              </v-chip>
            </v-img>
          </v-col>

          <v-col cols="4">
            <div>
              <v-card flat>
                <v-card-title>
                  joam
                </v-card-title>
                <v-card-subtitle>
                  <span
                    >sobreviooosobreviooosobreviooosobreviooosobreviooosobreviooosobreviooosobreviooosobreviooosobreviooo
                  </span>
                </v-card-subtitle>
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
          <p class="font-weight-light ma-6">
            (N. del T. persona que se dedica a la imprenta) desconocido usó una
            galería de textos y los mezcló de tal manera que logró hacer un
            libro de textos especimen. No sólo sobrevivió 500 años, sino que
            tambien ingresó como texto de relleno en documentos electrónicos,
            quedando esencialmente igual al original. Fue popularizado en los
            60s con la creación de las hojas "Letraset", las cuales contenian
            pasajes de Lorem Ipsum, y más recientemente con software de
            autoedición, como por ejemplo Aldus PageMaker, el cual incluye
            versiones de Lorem Ipsum.
          </p>
        </v-row>
      </v-container>
    </v-card>
  </v-app>
</template>

<script>
export default {};
</script>

<style></style>
