import Vue from "vue";
import VueRouter from "vue-router";
//import Home from "../views/Home.vue";
//import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
//import About from "../views/About.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Home.vue")
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
      import(/* webpackChunkName: "about" */ "../views/Login.vue")
  },
  {
    path: "/register",
    name: "Resgister",
    component: Register
  }
];

const router = new VueRouter({
  //para que vue impida al browser actuzalizar la pagina o nosq
  mode: "history",
  routes
});
/*
//Aqui comprobaremos del local storage si el user esta loggeado.. parece debil esto xd
router.beforeEach((to, from, next) => {
  console.log("router beforeEach");
  const publicPages = ["/login", "/register"];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem("user");
  console.log(localStorage.getItem("user"));
  if (authRequired && !loggedIn) {
    localStorage.getItem("not logged in, next login")
    return next("/login");
  }
  next();
});
*/
router.beforeEach((to, from, next) => {
  const publicPages = ["/login", "/register"];
  const authRequired = !publicPages.includes(to.path);
  console.log("authrequired?");
  console.log(authRequired);
  if (authRequired) {
    if (checkToken()) {
      console.log("There is a token, resume. (" + to.path + ")");
      next();
    } else {
      console.log("There is no token, redirect to login. (login)");
      next("login");
    }
  } else {
    console.log("You're on the login page");
    next(); // This is where it should have been
  }
});

function checkToken(){
  if (localStorage.getItem("user")) {
    console.log("Check token truee");
    return true;
  }
  console.log("Check token false");
  return false;
}

export default router;
