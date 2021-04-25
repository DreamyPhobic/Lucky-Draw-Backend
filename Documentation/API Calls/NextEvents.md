**Next Events**
----
  It gives a list of next(on-going) events.

* **URL**  
  `/events/nextEvents`

* **Method:**  
  `GET` 
  
*  **Request Headers**  
  `Authorization=Bearer {user_token}` 
  
*  **URL Params**  
    `None`

* **Data Params**  
  `None`
      
* **Sample Call:**
  ```curl
  curl --location --request GET 'https://lucky-draw-backend.glitch.me/events/nextEvents' \
    --header 'Authorization: Bearer {user_token}' \
    --data-raw ''
  ```
   
* **Success Response:**

  * **Code:** `200 OK` <br />
    **Content:**  
    ```json
    [
       {
          "Status": 0,
          "_id": "6084531eb73eb03706732107",
          "Reward": "Mobile Phone",
          "Time": "8:00 pm, 27 April 2021"
       },
       {
          "Status": 0,
          "_id": "6084533ab73eb03706732108",
          "Reward": "LED TV",
          "Time": "8:00 pm, 28 April 2021"
       }
    ]
    ```
 
* **Error Response:**

  * **Code:** `401 UNAUTHORIZED` <br />
    **Content:**  
    ```json
    {
      "message": "Auth failed"
    }
    ```
