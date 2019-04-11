package json.genson;

import java.io.*;

import java.util.*;
import java.util.Map.Entry;

import com.owlike.genson.Genson;
import com.owlike.genson.GenericType;

public class GensonConverter {
    public void convertComplex() {
        Genson genson = new Genson();

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File( classLoader.getResource("sqlmap.json").getFile())),"UTF8"));
            
            List<Map<String, String>> sqlMapList = genson.deserialize(reader, List.class);

            Map<String, String> hashMap = null;
            List<Map<String, String>> sqlList = null;

            String gubun = "";
            
            String sqlJsonString = "";
            
            SqlMapModel model = null;

            for(int i = 0; i < sqlMapList.size(); i++) {
                hashMap = sqlMapList.get(i);

                model = new SqlMapModel();

                model.setGubun(hashMap.get("gubun"));
                hashMap.remove("gubun");
                
                Map<String, String> modelMap = new HashMap<String, String>();

                // Parsing
                // "부터 "key" 앞의 ,까지
                sqlJsonString = hashMap.toString();
                sqlJsonString = sqlJsonString.substring(sqlJsonString.indexOf("sqlMap=") + 7, sqlJsonString.lastIndexOf("}"));
                sqlJsonString = sqlJsonString.replace("=", ":");
                sqlJsonString = sqlJsonString.replace("value", "\"value\"").replace("key", "\"key\"");
                sqlJsonString = sqlJsonString.replace(":", ":\"");
                sqlJsonString = sqlJsonString.replace(", \"key\"", "\", \"key\"");
                sqlJsonString = sqlJsonString.replace("}", "\"}");
                // Parsing

                // System.out.println(sqlJsonString);

                sqlList = genson.deserialize(sqlJsonString, new GenericType<List<Map<String, String>>>(){});

                for( int sqlIndex = 0; sqlIndex < sqlList.size(); sqlIndex++ ) {
                    Map<String, String> innerMap = sqlList.get(sqlIndex);
                    System.out.println(innerMap);

                    System.out.println(innerMap.get("key"));
                    System.out.println(innerMap.get("value"));
                    modelMap.put(innerMap.get("key"), innerMap.get("value"));

                }

                model.setSqlMap(modelMap);
                System.out.println(model);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            
        }

    }
}