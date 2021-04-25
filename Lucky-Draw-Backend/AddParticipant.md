**Add Participant**
----
  It adds a participant in the given event.

* **URL**  
  `/users/addParticipant/{event_id}`

* **Method:**  
  `PATCH` 
  
*  **Request Headers**  
  `Authorization=Bearer {user_token}` 
  
*  **URL Params**  
    `event_id = [string]`

* **Data Params**

  ```json
  {
      "participant_id":"608507630c011a00d1fd9297"
  }
  ```
  **Required:**  
  `participant_id=[string]`
   
   
* **Sample Call:**
  ```curl
  curl --location --request PATCH 'https://lucky-draw-backend.glitch.me/events/addParticipant/608521d1b2af6400d18be3e1' \
    --header 'Authorization: Bearer {user_token}' \
    --data-raw '{
        "participant_id":"608507630c011a00d1fd9297"
    }'
  ```
   
* **Success Response:**

  * **Code:** `201 CREATED` <br />
    **Content:**  
    ```json
    {
        "message": "Participant Added",
        "result": {
            "Status": 0,
            "Participants": [
                "608507630c011a00d1fd9297"
            ],
            "_id": "608521d1b2af6400d18be3e1",
            "Reward": "Washing Machine",
            "Time": "8:00 pm, 4th May 2021",
            "__v": 1
        }
    }
    ```
 
* **Error Response:**

  * **Code:** `409 CONFLICT` <br />
    **Content:**  
    ```json
    {
      "message": "Already Joined"
    }
    ```
  OR

  * **Code:** `401 UNAUTHORIZED` <br />
    **Content:**  
    ```json
    {
      "message": "Auth failed"
    }
    ```
   
    
   
