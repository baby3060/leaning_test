package json;

import lombok.*;

import java.util.*;

@Setter
    @Getter
    @ToString
    @EqualsAndHashCode
    public class Results {
        private int korean;
        private int math;
        private int english;

        public Results() {

        }

        public Results(int korean, int math, int english) {
            this.korean = korean;
            this.math = math;
            this.english = english;
        }
    }