import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "/login"
  },
  {
    path: "/home/:username",
    name: "Home",
    component: () => import(/* webpackChunkName: "home" */ "../views/Home.vue")
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
    path: "/login",
    name: "Login",
    component: () =>
      import(/* webpackChunkName: "login" */ "../views/Login.vue")
  },
  {
    path: "/register",
    name: "Resgister",
    component: () =>
      import(/* webpackChunkName: "register" */ "../views/Register.vue")
  },
  {
    path: "/upload",
    name: "Upload",
    component: () =>
      import(/* webpackChunkName: "upload" */ "../views/Upload.vue")
  }
];

const router = new VueRouter({
  //para que vue impida al browser actuzalizar la pagina o nosq
  mode: "history",
  routes
});

router.beforeEach((to, from, next) => {
  const publicPages = ["/login", "/register"];
  const authRequired = !publicPages.includes(to.path);
  console.log("authrequired?");
  console.log(authRequired);
  if (authRequired) {
    //if(checktoken())
    if (store.getters["auth/isLogged"]) {
      console.log("There is a token, resume. (" + to.path + ")");
      next();
    } else {
      console.log("There is no token, redirect to login. (login)");
      next("login");
    }
  } else {
    console.log("You're on the login/register page");
    next(); // This is where it should have been
  }
});

export default router;
