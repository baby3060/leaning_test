package json.gson;

import java.io.*;

import json.Student;

import java.util.*;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.*; 

public class GsonTester {
    // Json 형식 문자열을 객체로 변환
    public Student convertStringToObject(String no, String name, int age, int grade) {
        // Json으로 변환할 문자열
        String jsonString = "{\"no\":\""+ no + "\",\"name\":\""+ name + "\", \"age\" : "+ age + ", \"grade\" : "+ grade + "}";

        GsonBuilder builder = new GsonBuilder();
        // 아래 문장을 빼면 줄바꿈이 사라진다.
        builder.setPrettyPrinting();
        
        Gson gson = builder.create();
        // Json 문자열을 객체로 변환
        Student student = gson.fromJson(jsonString, Student.class);
        
        return student;
    }

    // 입력받은 json 문자열을 List로 변환
    public List<Student> convertStringToList(String jsonString) {

        GsonBuilder builder = new GsonBuilder();
        // 아래 문장을 빼면 줄바꿈이 사라진다.
        builder.setPrettyPrinting();
        
        Gson gson = builder.create();
        Type type = new TypeToken<List<Student>>() {}.getType();

        List<Student> result = gson.fromJson(jsonString, type);

        return result;
    }

    // 입력받은 객체를 Json문자열로 변환
    public String convertObjectTostring(Student student) {
        GsonBuilder builder = new GsonBuilder();
        // builder.setPrettyPrinting();

        Gson gson = builder.create();

        String jsonString = gson.toJson(student);

        return jsonString;
    }

    // 입력받은 문자열을 객체로 변환하지 않고 곧바로, 필드를 가져오기
    public String parsingStringGetField(String jsonString) {
        Gson gson = new Gson();

        String result = "";

        JsonParser parser = new JsonParser();
        // JsonElement로 반환
	    JsonElement element = parser.parse(jsonString);

        String no = element.getAsJsonObject().get("no").getAsString();
        String name = element.getAsJsonObject().get("name").getAsString();
        int age = element.getAsJsonObject().get("age").getAsInt();
        int grade = element.getAsJsonObject().get("grade").getAsInt();

        result = String.format("no : %s, name : %s, age : %d, grade : %d", no, name, age, grade);

        return result;
    }
    
    // 입력받은 List를 JsonArray로 변환
    public JsonArray convertListToArray(List<Student> list) {
        Gson gson = new Gson();

        JsonElement element = gson.toJsonTree(list, new TypeToken<List<Student>>() {}.getType());

        if (! element.isJsonArray()) {
            throw new RuntimeException();
        } else {
            return element.getAsJsonArray();
        }
    }

    // 입력받은 JsonArray를 트리형태로 JsonObject에 포함시키기
    public JsonObject treeJsonObjWrapJArray(JsonElement element) {
        JsonObject obj = new JsonObject();

        obj.addProperty("description", "설명");
        obj.add("treeIn", element);

        return obj;
    }

    /**
     * 비어있는 값 Null로 표현(그냥 toJson으로 변환할 경우와 비교해보기)
     */
    public String nullAbleFilld(Student student) {
        GsonBuilder builder = new GsonBuilder();
        
        builder.serializeNulls();
        builder.setPrettyPrinting(); 
        Gson gson = builder.create();  

        String jsonString = gson.toJson(student);

        return jsonString;
    }

    /**
     * Json 파일 읽어서 Student로 변환
     */
    public Student convertJsonFileToObject() {
        ClassLoader classLoader = getClass().getClassLoader();

        Gson gson = new Gson();

        Student student = new Student();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File( classLoader.getResource("sample.json").getFile())),"UTF8"));

            JsonElement json = gson.fromJson(reader, JsonElement.class);

            String result = gson.toJson(json);

            student = gson.fromJson(result, Student.class);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return student;
    }


    /**
     * Json 파일 읽어서 List로 변환
     */
    public List<Student> convertJsonFileToList() {
        List<Student> list = new ArrayList<Student>();
        ClassLoader classLoader = getClass().getClassLoader();
        Gson gson = new Gson();

        final Type STUDENT_TYPE = new TypeToken<Collection<Student>>(){}.getType();

        try {
            BufferedReader brreader = new BufferedReader(new InputStreamReader(new FileInputStream(new File( classLoader.getResource("sampleList.json").getFile())),"UTF8"));

            JsonElement json = gson.fromJson(brreader, JsonElement.class);

            String result = gson.toJson(json);

            list = gson.fromJson(result, STUDENT_TYPE); 
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}