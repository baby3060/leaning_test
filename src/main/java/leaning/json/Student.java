package json;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Student {
    @NonNull private String no;
    @NonNull private String name;
    @NonNull private int age;
    @NonNull private int grade;

    public Student() { }

    public Student( String no, String name, int age, int grade ) {
        this.no = no;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
}