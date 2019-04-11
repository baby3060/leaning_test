package leaning.common;

public class UUUStrategy implements Gubun1SolveStrategy {
    public String toSolve(double fcstValue) {
        String result = "";

        // 동
        if(fcstValue > 0 ) {
            result = "(동)" + fcstValue;
        } else {
            result = "(서)" + Math.abs(fcstValue);
        }  

        return result;

    }

}