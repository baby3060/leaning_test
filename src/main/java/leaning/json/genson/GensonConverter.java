package json.genson;

import json.Student;

import java.io.*;

import java.util.*;
import java.util.Map.Entry;

import com.owlike.genson.Genson;
import com.owlike.genson.GenericType;
import com.owlike.genson.GensonBuilder;

public class GensonConverter {

    public List<Object> listConvert() {
        Genson genson = new Genson();
        List<Object> result = genson.deserialize("[{\"age\" : 28, \"name\" : \"Foo\"}, {\"age\" : 29, \"name\" : \"Hoo\"}]", List.class);
        return result;
    }

    public String mapObjectConvert() {
        Genson genson = new Genson();

        Random random = new Random();

        String result = "";

        List<Map> resultList = new ArrayList<Map>();

        Map<String, Object> resultMap = new HashMap<String, Object>();

        int randInt = 0;
        int nameInt = 0;

        for( int i = 0; i < 10; i++ ) {
            resultMap = new HashMap<String, Object>();
            // 25 ~ 84
            randInt = random.nextInt(60) + 25;
            nameInt = random.nextInt(27) + 67;

            resultMap.put("age", randInt);
            resultMap.put("name", Character.toString((char)nameInt) + "oo" );

            resultList.add(resultMap);
        }

        result = genson.serialize(resultList);

        return result;
    }

    public List<Map<String, Object>> onResultMsgConvertList(String convertStr) {
        Genson genson = new Genson();

        List<Map<String, Object>> result = genson.deserialize(convertStr, new GenericType<List<Map<String, Object>>>(){});

        return result;
    }

    // 단순 변환
    public Student simpleConvert() {
        Genson genson = new Genson();
        Student student = new Student();
        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File( classLoader.getResource("sample.json").getFile())),"UTF8"));
            
            student = genson.deserialize(reader, Student.class);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { reader.close(); } catch(Exception E){}
        }

        return student;
    }

    /**
     * 억지로 파싱
     */
    public void convertComplex() {
        Genson genson = new Genson();

        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File( classLoader.getResource("sqlmap.json").getFile())),"UTF8"));
            
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
            try { reader.close(); } catch(Exception E){}
        }

    }

    /**
     * 변환해가면서 파싱(억지로 하는거 아님)
     */
    public void convertComplexNoParsing() {
        Genson genson = new Genson();

        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File( classLoader.getResource("sqlmap.json").getFile())),"UTF8"));
            
            List<Map<String, Object>> sqlMaps = genson.deserialize(reader, List.class);

            // System.out.println(sqlMaps);
            
            Map<String, Object> tempSql = null;

            String gubun = "";

            List<Map<String, String>> sqlList = null;
            Map<String, String> innerSqlMap = null;
            Map<String, String> modelMap = null;

            String key = "", sql = "";

            for( int i = 0; i < sqlMaps.size(); i++ ) {
                SqlMapModel model = new SqlMapModel();
                modelMap = new HashMap<String, String>();
                tempSql = sqlMaps.get(i);

                gubun = (String)tempSql.get("gubun");

                model.setGubun(gubun);

                sqlList = ((List<Map<String, String>>)tempSql.get("sqlMap"));

                for( int inner  = 0; inner < sqlList.size(); inner++ ) {
                    
                    innerSqlMap = sqlList.get(inner);
                    
                    key = innerSqlMap.get("key");
                    sql = innerSqlMap.get("value");

                    modelMap.put(key, sql);
                }

                model.setSqlMap(modelMap);

                System.out.println(model);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { reader.close(); } catch(Exception E){}
        }
    }
}