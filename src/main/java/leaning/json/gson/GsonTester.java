package json.gson;

import json.Student;

import java.util.*;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;  
import com.google.gson.JsonElement;  
import com.google.gson.JsonParser;  

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
        Gson gson = new Gson(); 

        String jsonString = gson.toJson(student);

        return jsonString;
    }

    // 입력받은 문자열을 객체로 변환하지 않고 곧바로, 필드를 가져오기
    public String parsingStringGetField(String jsonString) {
        Gson gson = new Gson();

        String result = "";

        JsonParser parser = new JsonParser();
	    JsonElement element = parser.parse(jsonString);

        String no = element.getAsJsonObject().get("no").getAsString();
        String name = element.getAsJsonObject().get("name").getAsString();
        int age = element.getAsJsonObject().get("age").getAsInt();
        int grade = element.getAsJsonObject().get("grade").getAsInt();

        result = String.format("no : %s, name : %s, age : %d, grade : %d", no, name, age, grade);

        return result;
    }
    
}