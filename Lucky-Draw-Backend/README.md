# Lucky-Draw-Backend
 A Backend for lucky draw system
 
 ### Base URL: https://lucky-draw-backend.glitch.me/
 
 ----
 ## For Testing purposes:
 
 ### User token:
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZXhhbXBsZS5jb20iLCJuYW1lIjoiVGVzdCB1c2VyIiwiaWF0IjoxNjE5MjczMTIyLCJleHAiOjE3MTkzNzY3MjJ9.EX7GdBF4l8ENndXtOJqI6Vrr4VmRNChDAHZ2ZxHBPZY
 
 ### Admin token:
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZXhhbXBsZS5jb20iLCJuYW1lIjoiQWRtaW4iLCJpYXQiOjE2MTkyNzMxMjIsImV4cCI6MTcxOTM3NjcyMn0.WBCLicO9YUsokHYPy8odGZGcEv0N1r9p6hcOmS61hXo

### Testing Credentials:
Field | Value
--- | --- 
_id | 608507630c011a00d1fd9297
name | Sample User
email | test@example.com
password | test

----

**User Signup**
----
  It creates new user and returns the user object.

* **URL**  
  `/users/signup`

* **Method:**  
  `POST` 
  
*  **URL Params**  
   `None`
    

   

* **Data Params**

  ```json
  {
    "name":"Sample User",
    "email":"test@example.com",
    "password":"test",
    "tickets":"0"
  } 
  ```
  **Required:**  
   `name=[string]`
   `email=[string]`
   `password=[string]`

   **Optional:**  
   `tickets=[integer]`
   
* **Sample Call:**
  ```curl
  curl --location --request POST 'https://lucky-draw-backend.glitch.me/users/signup' \
    --data-raw '{
        "name":"Sample User",
        "email":"test@example.com",
        "password":"test"
    }'
  ```
   
* **Success Response:**

  * **Code:** `201 Created` <br />
    **Content:**  
    ```json
    {
        "message": "User created",
        "user": {
            "tickets": 0,
            "_id": "608507630c011a00d1fd9297",
            "email": "test@example.com",
            "name": "Sample User",
            "password": "$2b$10$EqWFOfH.4JYYzGvCw71ameBcV5pNmXEgeRvW2O5YKaDeu2WVGm1Um",
            "__v": 0
        }
    }
    ```
 
* **Error Response:**

  * **Code:** `409 CONFLICT` <br />
    **Content:**  
    ```json
    {
      "message": "Mail exists"
    }
    ```
    
    
**User Login**
----
  It logs in the user and generates a token which is used to authenticate the api requests.

* **URL**  
  `/users/login`

* **Method:**  
  `POST` 
  
*  **URL Params**  
   `None`
    

   

* **Data Params**

  ```json
  {
    "email":"test@example.com",
    "password":"test",
  } 
  ```
  **Required:**  
   `email=[string]`
   `password=[string]`

   **Optional:**  
   `None`
   
* **Sample Call:**
  ```curl
  curl --location --request POST 'https://lucky-draw-backend.glitch.me/users/login' \
    --data-raw '{
        "email": "test@example.com",
        "password": "test"
     }'
  ```
   
* **Success Response:**

  * **Code:** `200 OK` <br />
    **Content:**  
    ```json
    {
       "message": "Auth successful",
       "token":  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZXhhbXBsZS5jb20iLCJ1c2VySWQiOiI2MDg1MDc2MzBjMDExYTAwZDFmZDkyOTciLCJuYW1lIjoiU2FtcGxlIFVzZXIiLCJpYXQiOjE2MTkzMzU3OTIsImV4cCI6MTYxOTMzOTM5Mn0.3ICT246WIZJOD1rVfCNWgPkrpqMlEgcAmhCIzckn0hA",
       "user": {
            "tickets": 0,
            "_id": "608507630c011a00d1fd9297",
            "email": "test@example.com",
            "name": "Sample User",
            "password": "$2b$10$EqWFOfH.4JYYzGvCw71ameBcV5pNmXEgeRvW2O5YKaDeu2WVGm1Um",
            "__v": 0
       }
    }
    ```
 
* **Error Response:**

  * **Code:** `401 UNAUTHORIZED` <br />
    **Content:**  
    ```json
    {
       "message": "Auth failed"
    }
    ```
