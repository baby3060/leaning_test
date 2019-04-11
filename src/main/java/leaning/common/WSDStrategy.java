package leaning.common;

public class WSDStrategy implements Gubun1SolveStrategy {
    public String toSolve(double fcstValue) {
        
        int chkValue = (int)fcstValue;

        int rangeValue = range(chkValue);

        switch(rangeValue) {
            case 1 : return "약";
            case 2 : return "약간 강";
            case 3 : return "강";
            case 4 : return "매우 강";
            default : throw new AssertionError("등록된 각도가 아닙니다(" + chkValue + ", 풍속).");
        }

    }

    public static int range(int chkValue){ 
        int result = 0;

        if ( 0 <= chkValue && chkValue < 4) {
            result = 1;
        } else if( 4 <= chkValue && chkValue < 9 ) {
            result = 2;
        } else if( 9 <= chkValue && chkValue < 14 ) {
            result = 3;
        } else {
            result = 4;
        }

        return result;
        
    }
}