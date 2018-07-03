package com.dms.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.dms.jsondata.*;


public class JsonTest {

    public static void writeJson() {

        JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", new Integer(100));

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        obj.put("messages", list);

        try (FileWriter file = new FileWriter("f:\\test.json")) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);

    }
    
       public static void readJson() {

            JSONParser parser = new JSONParser();
            
            List<SectionData> lst = new ArrayList<SectionData>();

            try {

                Object obj = parser.parse(new FileReader("one.json"));

                JSONObject jsonObject = (JSONObject) obj;
                System.out.println(jsonObject);

                String name = (String) jsonObject.get("businessKey");
                System.out.println(name);
                
                JSONObject jsonObj1 = (JSONObject)jsonObject.get("header");
                JSONArray jsonChildObject = (JSONArray)jsonObj1.get("sectionData");
                Iterator<JSONObject> iterator = jsonChildObject.iterator();
                while (iterator.hasNext()) {
                	JSONObject joob = iterator.next();
                    System.out.println(joob);
                    SectionData sectionData = new SectionData();
                    
                    sectionData.sectionName = (String) joob.get("sectionName");
                    sectionData.sectionOwnerId = (String) joob.get("sectionOwnerId");
                    sectionData.sectionSequence = (String) joob.get("sectionSequence");
                    sectionData.sectionVersionNo = (String) joob.get("sectionVersionNo");
                    sectionData.sectionDataasText = (String) joob.get("sectionDataasText");
                    sectionData.generateQRCode = (String) joob.get("generateQRCode");
                    sectionData.QRCodeURI = (String) joob.get("QRCodeURI");
                    
                    //if (joob.containsKey("sectionDataAsTable"))
                    
                    lst.add(sectionData);
                }
                
                System.out.println(lst.size());

                /*long age = (Long) jsonObject.get("age");
                System.out.println(age); */

                // loop array
                //JSONArray msg = (JSONArray) jsonObject.get("sectionData");
                //Iterator<String> iterator = msg.iterator();
                //while (iterator.hasNext()) {
                //    System.out.println(iterator.next());
               // }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        

    }

}
