# Description
A rest api on spring boot powered by Vue.js

# Requirements

| Name  | Description | Status API/FRONT |
| ------------- | ------------- | ------------- |
| Login  | Secure endpoint for sign in and sign up  | `Done` / `Done ` |
| Content  | Upload multimedia interface   | `Done` / `Done ` |
| Friends  | Manage friendships and show privileges   | `Done` / `Done ` |
| Comments  |  CRUD comments system | `Done` / `Done ` |
| Likes | Like interface | `Done` / `Done ` |
| Test | Make test for spring and vue | `Working` / `none ` |
| Deploy | Dockerize and try to deploy frontend as static app (ideally w/o alter a lot of lines..) | `none` / `none`| 

# Test
| Name  | Requirements | Technology | Description | Status  |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| LOGIN01  | Login  | Postman |  check home access with a valid and invalid credentials | `done` |
| LOGIN02  | Login  | Selenium |  check for app feedback while sign in| `none` |
| REGISTER01  | Login  | Postman |  check sign up validators (email and user name) | `done` |
| REGISTER02  | Login  | Selenium |  check for sign up feedback while sign up| `none` |
| UPLOAD01  | Content  | Postman |  check availability of uploaded content | `done` |
| UPLOAD02  | Content  | Selenium |  try to upload content| `none` |
| FRIENDS01  | Friends  | Postman |  check privileges for friend and non friend user | `done` |
| FRIENDS02  | Friends  | Selenium |  The same | `none` |
| COMMENTS01  | Comments  | Postman |  check CRUD operations | `working on` |
| COMMENTS02  | Comments  | Selenium |  The same | `none` |
| LIKES01  | Likes  | Postman |  add like to content | `working on` |
| LIKES02  | Likes  | Selenium |  The same | `none` |
