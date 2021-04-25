**Previous Events**
----
  It gives a list of previous events with winner ids and names.

* **URL**  
  `/events/previousEvents`

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
  curl --location --request GET 'https://lucky-draw-backend.glitch.me/events/previousEvents' \
    --header 'Authorization: Bearer {user_token}' \
    --data-raw ''
  ```
   
* **Success Response:**

  * **Code:** `200 OK` <br />
    **Content:**  
    ```json
    [
      {
         "Status": 1,
         "_id": "608452b8171e0135d3089945",
         "Reward": "Washing Machine",
         "Time": "8:00 pm, 26 April 2021",
         "Winner": {
              "_id": "6084020b71c7ab374c48ee5e",
               "name": "Harsh Gupta"
          }
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
