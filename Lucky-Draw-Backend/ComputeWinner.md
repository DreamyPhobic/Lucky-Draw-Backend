**Compute Winner**
----
  It randomly selects a winner from participants list and saves it for a given event.

* **URL**  
  `/events/computeWinner/{event_id}`

* **Method:**  
  `GET` 
  
*  **Request Headers**  
  `Authorization=Bearer {admin_token}` 
  
*  **URL Params**  
    `event_id=[string]`

* **Data Params**  
  `None`
      
* **Sample Call:**
  ```curl
  curl --location --request GET 'https://lucky-draw-backend.glitch.me/events/computeWinner/608521d1b2af6400d18be3e1' \
    --header 'Authorization: Bearer {admin_token}' \
    --data-raw ''
  ```
   
* **Success Response:**

  * **Code:** `200 OK` <br />
    **Content:**  
    ```json
    {
        "messgae": "Winner Computed",
        "result": {
            "Status": 1,
            "Participants": [
                "608507630c011a00d1fd9297"
            ],
            "_id": "608521d1b2af6400d18be3e1",
            "Reward": "Washing Machine",
            "Time": "8:00 pm, 4th May 2021",
            "__v": 1,
            "Winner": "608507630c011a00d1fd9297"
        }
    }
    ```
 
* **Error Response:**
  * **Code:** `404 Not Found` <br />
    **Content:**  
    ```json
    {
      "error": "Event doesn't exist"
    }
    ```

  * **Code:** `401 UNAUTHORIZED` <br />
    **Content:**  
    ```json
    {
      "message": "Auth failed"
    }
    ```
