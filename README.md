# Social-app
More Spring boot-Vue in a social like app with Security and multimedia Content

## Done
- Backend project structure.
- Sign up and sign in.
- JWT with Authorities, encoded password.
- JPA persistence entities Client and Rol.
- DTO object JwtDTO, Message, User, newUser.
- Postman test environment (collection structure).
- JPA persistence entities.content.
- Lombock.
- POST/GET backend file management, persistence and controller.
- Add media and mediaDTO clases.
- Friendship structure by a Set<followerRequest> and Set<Long> friend_ids,
  not full CRUD implemented
- Multi file upload and update file(s) are now supported, (same endpoint)
- Tests for everything above, searching for automatize some of them
- Add jcodec dependencies and preview video image service.
- Add like endpoint.
- Add Vue project structute
- Add login and Register template, Vuetify
- Add router and authService, cors policy on WebSecurity.java
- Add token managment, more router
- Login and Sing up woorking
- Create home.vue grid and v-card template structure
- Add axios store
- UserService and ContentService are now in charge to request only to its own RestController
- Divided store in several files
- Add Thumbnail workable vue component
- Fixed bugs while resizing on server side
- Implement profile pic and description on both sides
- Add Frame and Message components
- Add endpoints for frame and menssages
- Implemented new components on vue project
- Vue now reproduce video "almost" nicely, some data reactive bug need fix
- Add theme, sidebar, tooltips
- Add toggle dark/light theme
- Create /Settings and /Upload routes
- Add feedback to user on liking, following and commenting
- Create userCard DTO object and component, to use while searching and viewing friends list
- Several fixies

## TODO
- ~~Set requeriments, identify dependencies for both~~ 
- ~~Create both projects structure~~
- ~~Integrate JWT~~
- Secure Cookies thing
- ~~/Login and /new endpoint~~
- ~~Create entities (frame, files, etc.. )~~
- Hibernate and vue validator
- ~~Refactor lombok~~
- ~~Design RestController(s) (DTO objects, response, router and roles)~~
- ~~Implements friendship thing by userRepo.findByUsername("/{Jackson}").isFriend(username);~~
- ~~Write file IO thing (modify existent JPAentities, add DAOentities, Controller thing and implement it)~~
- ~~Deploy prototype api, write test~~
- ~~Response code and exception handling~~
- ~~Read about asynchronous and see how to include it if suits are meet~~
- Go deeper on Postman (almost finish.. see how to automatize and print cool results)
- ~~Start with Vue.js~~
- ~~frame content: ui design (v-dialog wrapping v-card like in home component, social thing and messages, def data needed to retrieve from api~~
- ~~display video thing, please be simple~~
- ~~code endpoints for menssages and video(maybe)~~
- ~~user sidebar (this will need more endopoints (for both authController/authService, to CRUD user info))~~
- implement following actions, accept/cancel requests in front
- avatar also
- pm thing, last thing, super simple
- create  and edit support for frames (back already supports this)
- ~~finish back, whatever it takes~~, dockerize and see heroku
- mock back and try to deploy front as static app
- DON¨T FORGET VALIDATORS ON FORMS AND ENTITIES
- SonarQube and lighthouse for code audit
  
## Then
- ~~Complete entity classes and RestController for Spring~~
- ~~Complete Router for Vue~~
- ~~Write home and derivated components~~
- ~~Design test interface set up for both (started with Selenium)~~
- ~~Test api with Postman and save jsons (Deploy static front) (reading about)~~
- code front test
- See how to doc (a little bit)

## One day..
- ~~Multimedia thing (almost done)~~
- ~~Router (pretty easy, low or none @PathVariable)~~
- ~~Make it pretty (no way..)~~ (in Vuetify we belive)
- More tests, maybe automate things
- ...
- Make app thing ^^ (soon..)
- Next project.. Deploy this vue in java android and ¿java desktop?
- Microservices thing and cloud ??


20/08/20






