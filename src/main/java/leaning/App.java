/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package leaning;

import com.google.gson.*;

import json.*;
import json.gson.GsonTester;

import java.util.*;

import leaning.xml.parser.dom.*;

public class App {

    public static void main(String[] args) {
        GsonTester tester = new GsonTester();

        List<Student> list = tester.convertJsonFileToList();

        System.out.println(list);
    }
}
