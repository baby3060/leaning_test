package json;

import lombok.*;

import java.util.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Student {
    @NonNull private String no;
    @NonNull private String name;
    private int age;
    private int grade;
    private Results results;
    private List<String> hobby;

    public Student() { }

    public Student( String no, String name, int age, int grade ) {
        this.no = no;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public Student( String no, String name, int age, int grade, Results results ) {
        this.no = no;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.results = results;
    }

}