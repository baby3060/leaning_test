package leaning.common;

public class VECStrategy implements Gubun1SolveStrategy {
    public String toSolve(double fcstValue) {
        
        int chkValue = (int)fcstValue;

        int rangeValue = range(chkValue);

        switch(rangeValue) {
            case 1 : return "N-NE";
            case 2 : return "NE-E";
            case 3 : return "E-SE";
            case 4 : return "SE-S";
            case 5 : return "S-SW";
            case 6 : return "SW-W";
            case 7 : return "W-NW";
            case 8 : return "NW-N";
            default : throw new AssertionError("등록된 각도가 아닙니다(" + chkValue + ", 풍향).");
        }

    }

    public static int range(int chkValue){ 
        int result = 0;

        if ( 0 <= chkValue && chkValue < 45) {
            result = 1;
        } else if( 45 <= chkValue && chkValue < 90 ) {
            result = 2;
        } else if( 90 <= chkValue && chkValue < 135 ) {
            result = 3;
        } else if( 135 <= chkValue && chkValue < 180 ) {
            result = 4;
        } else if( 180 <= chkValue && chkValue < 225 ) {
            result = 5;
        } else if( 225 <= chkValue && chkValue < 270 ) {
            result = 6;
        } else if( 270 <= chkValue && chkValue < 315 ) {
            result = 7;
        } else if( 315 <= chkValue && chkValue < 360 ) {
            result = 8;
        }

        return result;
        
    }
}