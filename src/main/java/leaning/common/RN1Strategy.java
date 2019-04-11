package leaning.common;

public class RN1Strategy implements Gubun1SolveStrategy {
    public String toSolve(double fcstValue) {
        
        int chkValue = (int)fcstValue;

        switch(chkValue) {
            case 0 : return "0mm 또는 없음";
            case 1 : return "1mm 미만";
            case 5 : return "1~4mm";
            case 10 : return "5~9mm";
            case 20 : return "10~19mm";
            case 40 : return "20~39mm";
            case 70 : return "40~69mm";
            case 100 : return "70mm 이상";
            default : throw new AssertionError("등록된 코드가 아닙니다(" + chkValue + ", 낙뢰).");
        }

    }
}