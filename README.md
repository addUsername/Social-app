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
- Go deeper on Postman
- ~~Start with Vue.js~~

## Then
- ~~Complete entity classes and RestController for Spring~~
- ~~Complete Router for Vue~~
- ~~Write home and derivated components~~
- Design test interface set up for both (reading about)
- ~~Test api with Postman and save jsons (Deploy static front) (reading about)~~
- Test static and dinamic(real Spring connection) for login in selenium
- See how to doc

## One day..
- Multimedia thing (almost done!)
- ~~Router (pretty easy, low or none @PathVariable)~~
- ~~Make it pretty (no way..)~~ (in Vuetify we belive)
- More tests, maybe automate things
- ...
- Make app thing ^^ (soon..)
- Next project.. Deploy this vue in java android and ¿java desktop?
- Microservices thing and cloud ??


20/08/20






