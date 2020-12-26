package com.userdata.display.service;

import com.userdata.display.model.Parent;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.InputStream;
import java.io.IOException;
import java.util.*;
import org.json.*;


@Service
public class ParentService {

    private String parentJSON = "/json/Parent.json";
    private String childJSON = "/json/Child.json";

    // Function to calculate and return List<Parent>
    public List<Parent> list(Integer offset, String order) {

        // Call Function to extract JSONArray
        JSONArray parentArr = getJsonData(parentJSON);
        JSONArray childArr = getJsonData(childJSON);

        // Create an object to store the response
        List<Parent> parents = new ArrayList<>();

        /**
         * What can be improved?
         * 1. Since the json file is already sorted according to id
         * reversing the order of the JSONArray will give us the 
         * descending ordered list by id.
         * We can add a sorting algorithm incase the file isn't sorted already.
         * 2. If we have to sort multiple columns we can send them as a param
         */

        if (order.equals("desc")) {
            JSONArray descArray = new JSONArray();

            for (int i = parentArr.length()-1; i >= 0; i--) {
                descArray.put(parentArr.get(i));
            }
            parents = getSortedData(descArray, childArr, offset);
        } else {
            parents = getSortedData(parentArr, childArr, offset);
        }

        return parents;
    }

    /**
     * LOGIC:
     * Since every page can only display 2 entries,
     * page 1 will have entries 0,1
     * page 2 will have entries 2,3... etc
     * We send page no as offset and
     * thus ((2*offset) -1) and ((2*offset) -2) are the 
     * entries that need to be extracted.
     */

    // Function to extract the 2 required entires
    public List<Parent> getSortedData(JSONArray parentArr, JSONArray childArr, Integer offset) {

        List<Parent> parents = new ArrayList<>();

        // Variables to store data from child file
        Integer totalPaidAmount = 0;

        // Set the limit to extract array
        Integer limit = (2*offset) - 1;

        // Incase it's the last page but only has one entry
        if ((parentArr.length() - 1) < (2*offset) -1) {
            limit = parentArr.length() - 1;
        }

        // Loop through the parent json for the 2 entries we need
        for (int i = (2*offset) - 2; i <= limit; i++) {
            totalPaidAmount = 0;
            // Loop through the child json
            for(int j = 0; j < childArr.length(); j++){
                
                // Check if the id matches the parent id in the child json
                if((childArr.getJSONObject(j).getInt("parentId") == parentArr.getJSONObject(i).getInt("id"))) {

                    //extract the total paid amount
                    totalPaidAmount += childArr.getJSONObject(j).getInt("paidAmount");
                }
            }

            // Add data to the response
            parents.add(new Parent(parentArr.getJSONObject(i).getInt("id"), parentArr.getJSONObject(i).getString("sender"), parentArr.getJSONObject(i).getString("receiver"), parentArr.getJSONObject(i).getInt("totalAmount"), totalPaidAmount));
        }   

        return parents;
    }

    // Fucntion to calculate the number of entries
    public Integer totalEntries() {
        return getJsonData(parentJSON).length();
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