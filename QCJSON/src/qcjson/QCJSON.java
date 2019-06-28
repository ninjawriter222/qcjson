package qcjson;

import org.quickconnect.json.*;
import java.io.*;
import java.util.*;
import java.util.HashMap;
import org.json.simple.JSONObject;
/**
 *
 * @author Poozer
 */
public class QCJSON
{

    /**
     * @param args the command line arguments
     */
    //making an object internally for testing purposes/proof of concept.
    public class numbahWhan implements Serializable
    {
        public String name;
        public String base;
        public int weight;
        public int age;
       

        public numbahWhan(String name1, String base1, int weight1, int age1)
        {
           name = name1;
           base = base1;
           weight = weight1;
           age = age1;
        }
        
        public numbahWhan(HashMap aMapRepresentation)
        {
            this.name = (String)aMapRepresentation.get("name");
            this.base = (String)aMapRepresentation.get("base");
            Long lbs;
            lbs = Long.parseLong((String) aMapRepresentation.get("weight"));
            this.weight = lbs.intValue();
            Long years = Long.parseLong((String) aMapRepresentation.get("age"));
            this.age = years.intValue();
//            if((Boolean)aMapRepresentation.get("status")){
//                this.status = true;
//            }
//            else{
//                this.status = false;
//            }
        }
        //creating a string to compare to.    
        public void to_String()
        {
            System.out.print("Bio: " + name + " Their base is located: " + base + " Their age is currently: " + age + " Their weight is: " + weight);
//            if(status){
//                System.out.print("Kill Confirmed.");
//            }
//            else{
//                System.out.print("Still alive and dangerous!");
//            }
        }
    
        }
        //This method outputs a JSON object as a string to a file stream.
        //Later in the method will use it for the output.
        public void qcJson_Example(String filename) throws IOException
        {
            
        //instantiate the input and output streams for use later.
        FileOutputStream fout = new FileOutputStream(filename);
        FileInputStream fin = new FileInputStream(filename);
        
        //Turn instantiations into objects which will take a serializable object
        //and send it to the destination.  It can also convert it into a hashmap
        //to use to create an object.
        JSONOutputStream jsonOut = new JSONOutputStream(fout);
        JSONInputStream jsonIn = new JSONInputStream(fin);

        //instaniate string we're going to be using as output.
        numbahWhan numberone = new numbahWhan("Boots","Underground Base in Metro City",240,36);
        System.out.println("What was written in the JSON file?.... Lets take a look!");
        numberone.to_String();
        
        //Use JSONoutput to try and output the object as a serialized string
        //to the stream created previously.
        try{
            jsonOut.writeObject(numberone);
            System.out.println("\nattempt to send object out was successful!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        //try and read that object we made into a new object of the same type.
        try{
            System.out.println("");
            System.out.println("now I am reading the file and creating a new object with the exact same information");
            System.out.println("");

            HashMap parsedJSONMap = (HashMap) jsonIn.readObject();
            System.out.println("I just read" + jsonIn);
            numbahWhan readObject = new numbahWhan(parsedJSONMap);
//            System.out.println("Stream Same? " + readObject.equals(numberone));
//            readObject.to_String();
            
            String jsonString = JSONUtilities.stringify(numberone);
            System.out.println("JSON: "+jsonString);
            
            parsedJSONMap = (HashMap)JSONUtilities.parse(jsonString);
            readObject = new numbahWhan(parsedJSONMap);
//            System.out.println("stringify same? "+readObject.equals(numberone));
            
        } catch(JSONException e){
                e.printStackTrace();
        }
        }   
    
        public static void main(String[] args) {
        //Where to pass JSON sting to.
        String dir = "C:\\Users\\Poozer\\Desktop";
        String filename = dir + "\\testFile.txt";
        
        QCJSON obj = new QCJSON();
        
        try{
            obj.qcJson_Example(filename);
        } catch(IOException e){
            System.out.println("the file: " + filename + " Does not exist in this location");
           e.printStackTrace();
        }
        }

}
