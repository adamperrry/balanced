import { createRouter, createWebHashHistory } from "vue-router";
import HomePage from "../views/HomePage.vue";

const routes = [
  {
    path: "/",
    name: "HomePage",
    component: HomePage
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue")
  },
  {
    path: "/transactions",
    name: "Transactionsage",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/TransactionsPage.vue")
  },
  {
    path: "/accounts",
    name: "AccountsPage",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AccountsPage.vue")
  },
  {
    path: "/summaries",
    name: "SummariesPage",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/SummariesPage.vue")
  },
  {
    path: "/categories",
    name: "CategoriesPage",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/CategoriesPage.vue")
  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

export default router;
