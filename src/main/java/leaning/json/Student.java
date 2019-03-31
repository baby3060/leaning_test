package json;

import lombok.*;

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

    @Setter
    @Getter
    @ToString
    @EqualsAndHashCode
    public class Results {
        private int korean;
        private int math;
        private int english;
    }

}