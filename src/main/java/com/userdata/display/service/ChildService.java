package com.userdata.display.service;
import com.userdata.display.model.Child;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.InputStream;
import java.io.IOException;
import java.util.*;
import org.json.*;

@Service
public class ChildService {

    private String parentJSON = "/json/Parent.json";
    private String childJSON = "/json/Child.json";

    // Function to calculate and return List<Child>
    public List<Child> list(Integer parentId) {

        // Call function to get the JSONArray from the json files
        JSONArray parentArr = getJsonData(parentJSON);
        JSONArray childArr = getJsonData(childJSON);

        // Create an object to store the response
        List<Child> child = new ArrayList<>();

        // Variables to store data from parent file
        Integer totalAmount = 0;
        String sender = new String();
        String receiver = new String();

        /**
         * Use Binary Search to find the entry of 
         * the parent using parentId
         */
        Integer mid = (parentArr.length()-1)/2;
        Integer first = 0;
        Integer last = parentArr.length()-1;

        while( first <= last ){  
            if ( parentArr.getJSONObject(mid).getInt("id") < parentId ) {  
                first = mid + 1;     
            } else if ( parentArr.getJSONObject(mid).getInt("id") == parentId ) {  
                sender = parentArr.getJSONObject(mid).getString("sender");
                receiver = parentArr.getJSONObject(mid).getString("receiver");
                totalAmount = parentArr.getJSONObject(mid).getInt("totalAmount");
                break;  
            } else {  
               last = mid - 1;  
            }  
            mid = (first + last)/2;  
        }

        // Loop through the child json
        for (int i = 0; i < childArr.length(); i++) {
            // Check if the parent id matches
            if ((childArr.getJSONObject(i).getInt("parentId") == parentId)) {
                // Add data to the response
                child.add(new Child(childArr.getJSONObject(i).getInt("id"), parentId, sender, receiver, totalAmount, childArr.getJSONObject(i).getInt("paidAmount")));
            }
        }
            
        return child;
    }

    /**
     * What can be improved?
     * This operation of retrieving data can
     * be shifted to DAO folder if we use 
     * a repository instead of json files.
     */

    // Function to open json file and extracting the array content
    public JSONArray getJsonData(String fileName) {

        JSONArray arr = new JSONArray();

        // Create input stream of json files
        InputStream inputStream = TypeReference.class.getResourceAsStream(fileName);

        // Create mapper object to read json files
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Use readValue API of ObjectMapper to read the json files
            Map<String, List<Object>> list = mapper.readValue(inputStream, new TypeReference<Map <String, List<Object>>>(){});
        
            // Create the json object from Map<String,List<Parent>> and Map<String,List<Child>>
            JSONObject jsonObj = new JSONObject(list);

            // Extract the json array from JSONObject
            arr = jsonObj.getJSONArray("data");

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return arr;
    }
}
