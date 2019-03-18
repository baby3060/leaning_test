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
}