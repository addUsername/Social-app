<template>
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
</template>

<script>
export default {};
</script>

<style></style>
