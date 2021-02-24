import { createApp } from "vue";
import store from "./store";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import VueAxios from "vue-axios";
import { toCurrency, scrollToTop, scrollToBottom } from "./store/helpers";

import "./assets/css/styles.css";

createApp(App)
  .use(router)
  .use(VueAxios, axios)
  .use(store)
  .mixin({
    methods: {
      toCurrency,
      scrollToTop,
      scrollToBottom
    },
  })
  .mount("#app");
