* The app contains multiple boards to signify different projects
* Each board contains different lists to signify sub-project
* Each list contain different cards signifying smaller tasks
* Each card can be assigned to a user or may remain unassigned



_App -> Boards -> Lists -> Cards (assignedTo)_

#### **Entities**
* **User**
--> userId, name, email.
* **Board**
--> id, name, privacy (PUBLIC/PRIVATE), url, members, lists
* **List**
--> id, name and cards
* **Card**
--> id, name, description, assigned user


#### **Functionalities**
*We should be able to create/delete boards, add/remove people from the members list and modify attributes. Deleting a board should delete all lists inside it.
* We should be able to create/delete lists and modify attributes. Deleting a list should delete all cards inside it.
* We should be able to create/delete cards, assign/un-assign a member to the card and modify attributes
* We should also be able to move cards across lists in the same board

* Ability to show all boards, a single board, a single list and a single card
* Default privacy should be public
* Cards should be unassigned by default
* Ids should be auto-generated for board/list/card
* URLs should get created based on the id