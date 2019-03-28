/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package leaning;

import com.google.gson.*;

import json.*;
import json.gson.GsonTester;

import java.util.*;

public class App {

    public static void main(String[] args) {
        List<Student> toList = new ArrayList<Student>(Arrays.asList(
            new Student("101", "학생1", 15, 2),
            new Student("102", "학생2", 16, 3),
            new Student("103", "학생3", 14, 1),
            new Student("104", "학생4", 15, 2),
            new Student("105", "학생5", 16, 2)
        ));

        GsonTester tester = new GsonTester();

        JsonArray jsonArr = tester.convertListToArray(toList);

        JsonObject jsonObject = tester.treeJsonObjWrapJArray(jsonArr);

        System.out.println(jsonObject);

        jsonArr = jsonObject.getAsJsonArray("treeIn");
        System.out.println(jsonArr);

        Student student = new Student();

        student.setNo("101");

        String nullStr = tester.nullAbleFilld(student);

        System.out.println(nullStr);

        String notNullStr = tester.convertObjectTostring(student);

        System.out.println(notNullStr);
    }
}
