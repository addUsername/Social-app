# Description
A rest api on spring boot powered by Vue.js

# Requirements

| Name  | Description | Status  |
| ------------- | ------------- | ------------- |
| Login  | Secure endpoint for sign in and sign up  | `Done` |
| Content  | Upload multimedia interface  | `working` |
| Friends  | Manage friendships and show privileges  | `Working` |
| Comments  |  CRUD comments system | `none` |
| Likes | Like interface | `none`|
| Test | Make test for spring and vue | `Working`|
| Deploy | Dockerize and try to deploy frontend as static app (ideally w/o alter a lot of lines..) | `none`|

# Test
| Name  | Requirements | Technology | Description | Status  |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| LOGIN01  | Login  | Postman |  check home access with a valid and invalid credentials | `done` |
| LOGIN02  | Login  | Selenium |  check for app feedback while sign in| `none` |
| REGISTER01  | Login  | Postman |  check sign up validators (email and user name) | `done` |
| REGISTER02  | Login  | Selenium |  check for sign up feedback while sign up| `none` |
| UPLOAD01  | Content  | Postman |  check availability of uploaded content | `none` |
| UPLOAD02  | Content  | Selenium |  try to upload content| `none` |
| FRIENDS01  | Friends  | Postman |  check privileges for friend and non friend user | `none` |
| FRIENDS02  | Friends  | Selenium |  The same | `none` |
| COMMENTS01  | Comments  | Postman |  check CRUD operations | `none` |
| COMMENTS02  | Comments  | Selenium |  The same | `none` |
| LIKES01  | Likes  | Postman |  add like to content | `none` |
| LIKES02  | Likes  | Selenium |  The same | `none` |
