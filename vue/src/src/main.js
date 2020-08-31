import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
//import VeeValidate from 'vee-validate';

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  //VeeValidate,
  render: h => h(App)
}).$mount("#app");
