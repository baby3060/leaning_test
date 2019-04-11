package leaning.common;

public class LGTStrategy implements Gubun1SolveStrategy {
    public String toSolve(double fcstValue) {
        
        int chkValue = (int)fcstValue;

        switch(chkValue) {
            case 0 : return "확률없음";
            case 1 : return "낮음";
            case 2 : return "보통";
            case 3 : return "높음";
            default : throw new AssertionError("등록된 코드가 아닙니다(" + chkValue + ", 낙뢰).");
        }

    }
}