# spring-boot-angularjs-displayjson
Display Paginated JSON in spring-boot-angularjs.

Backend Architechture:

src ->

    main ->
        java/com/userdata/display ->
            controller ->
              // Holds all the Endpoints
              -> ParentController.java
              -> ChildController.java
             model ->
              // Holds the structure of the response
              -> Parent.java
              -> Child.java
             service ->
               // Holds the logic to extract the response data
               -> ParentService.java
               -> ChildService.java
               ** Main class, Run this file to start the application **
             -> DisplayApplication.java
         resources ->
              json->
                 // Holds the json files
                 ->Parent.json
                 ->Child.json
             
             
 Frontend Architecture:
 
 
     main ->
        resources->
            static->
                css->
                  //Holds the css for the application
                  ->display.css
                js->
                  //Holds the controllers
                  ->parentController.js
                  ->childController.js
                  
                //html files to display content
                ->parent.html
                -> child.html
                
                // Registers angular, JS files and sets states
                ->app.js
                ->index.html
                
         

How to start the application?

1. Clone the repository

2. Open the repository in an IDE

3. Run the file DisplayApplication.java

4. Open localhost:8080


              
