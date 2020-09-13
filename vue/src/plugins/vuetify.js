import Vue from "vue";
import Vuetify from "vuetify/lib";
import colors from "vuetify/lib/util/colors";

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: "#90caf9",
        secondary: "#ffecb3",
        accent: colors.shades.black,
        error: colors.red.accent3,
        background: "#eeeeee" // Added variable
      },
      dark: {
        primary: "#5d99c6",
        secondary: "#cbba83",
        background: "#bcbcbc" // If using base color, be use to mark as such to get HEX value
      }
    }
  }
});
