package json.gson;

import json.Student;

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;  

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
}