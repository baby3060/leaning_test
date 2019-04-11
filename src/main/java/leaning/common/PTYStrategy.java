package leaning.common;

public class PTYStrategy implements Gubun1SolveStrategy {
    public String toSolve(double fcstValue) {
        
        int chkValue = (int)fcstValue;

        switch(chkValue) {
            case 0 : return "없음";
            case 1 : return "비";
            case 2 : return "비/눈";
            case 3 : return "눈(진눈깨비)";
            default : throw new AssertionError("등록된 코드가 아닙니다(" + chkValue + ", 강수형태).");
        }

    }
}