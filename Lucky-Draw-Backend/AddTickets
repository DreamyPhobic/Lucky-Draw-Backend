**Add Tickets**
----
  It adds the given number of tickets to the user account.

* **URL**  
  `/users/addTickets/{user_id}`

* **Method:**  
  `PATCH` 
  
*  **Headers**  
  `Authorization=Bearer {admin_token}` 
  
*  **URL Params**  
    **Required:**
   `user_id = [string]`

* **Data Params**

  ```json
  {
    "tickets":"4"
  } 
  ```
  **Required:**  
  `tickets=[integer]`
   
   
* **Sample Call:**
  ```curl
  curl --location --request PATCH 'https://lucky-draw-backend.glitch.me/users/addTickets/608507630c011a00d1fd9297' \
    --header 'Authorization: Bearer {admin_token}' \
    --data-raw '{
        "tickets":"4"
     }'
  ```
   
* **Success Response:**

  * **Code:** `200 OK` <br />
    **Content:**  
    ```json
    {
       "message": "Tickets have been added",
       "result": {
           "tickets": 4,
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
