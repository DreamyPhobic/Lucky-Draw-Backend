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
