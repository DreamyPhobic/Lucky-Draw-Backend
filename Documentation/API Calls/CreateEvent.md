**Create Event**
----
  It creates new event and returns the event object.

* **URL**  
  `/events/createEvent`

* **Method:**  
  `POST` 
  
*  **Request Headers**  
  `Authorization=Bearer {admin_token}` 
  
*  **URL Params**  
    `None`

* **Data Params**

  ```json
  {
      "Reward":"Washing Machine",
      "Time":"8:00 pm, 4th May 2021",
      "Status":"0"
  }
  ```
  **Required:**  
  `Reward=[string]`
  `Time=[string]`
  
  **Optional:**  
  `Status=[integer]`
   
   
* **Sample Call:**
  ```curl
  curl --location --request POST 'https://lucky-draw-backend.glitch.me/events/createEvent' \
      --header 'Authorization: Bearer {admin_token}' \
      --data-raw '{
          "Reward":"Washing Machine",
          "Time":"8:00 pm, 4th May 2021",
          "Status":"0"
      }'
  ```
   
* **Success Response:**

  * **Code:** `201 CREATED` <br />
    **Content:**  
    ```json
    {
        "message": "Event added",
        "result": {
            "Status": 0,
            "Participants": [],
            "_id": "608521d1b2af6400d18be3e1",
            "Reward": "Washing Machine",
            "Time": "8:00 pm, 4th May 2021",
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
