package leaning.common;

public class SkyStrategy implements Gubun1SolveStrategy {
    public String toSolve(double fcstValue) {
        
        int chkValue = (int)fcstValue;

        switch(chkValue) {
            case 1 : return "맑음";
            case 2 : return "구름조금";
            case 3 : return "구름많음";
            case 4 : return "흐림";
            default : throw new AssertionError("등록된 코드가 아닙니다(" + chkValue + ", 하늘상태).");
        }

    }
}