import { createApp } from "vue";
import store from "./store";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import VueAxios from "vue-axios";

createApp(App)
  .use(router)
  .use(VueAxios, axios)
  .use(store)
  .mount("#app");
