package leaning.common;

public class VVVStrategy implements Gubun1SolveStrategy {
    public String toSolve(double fcstValue) {
        String result = "";

        // 동
        if(fcstValue > 0 ) {
            result = "(북)" + fcstValue;
        } else {
            result = "(남)" + Math.abs(fcstValue);
        }  

        return result;

    }

}