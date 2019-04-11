package leaning.common;

/**
 * 초단기예보 항목명
 */
public enum DataGubun1 {
    T1H("기온", "\u00b0" + "C"), RN1("1시간 강수량", ""), SKY("하늘상태", ""), UUU("동서바람성분", "m/s"), 
    VVV("남북바람성분", "m/s"), REH("습도", "%"), PTY("강수형태", ""), LGT("낙뢰", ""), VEC("풍향", ""), WSD("풍속", "");

    private String printName;
    private String symbol;

    DataGubun1(String printName, String symbol) {
        this.printName = printName;
        this.symbol = symbol;
    }
    
    public String getPrintName() {
        return this.printName;
    }
    public String getSymbol() {
        return this.symbol;
    }

    public static DataGubun1 valueOfCustom(String codeName) {
        switch(codeName) {
            case "T1H": return T1H;
            case "RN1": return RN1;
            case "SKY": return SKY;
            case "UUU": return UUU;
            case "VVV": return VVV;
            case "REH": return REH;
            case "PTY": return PTY;
            case "LGT": return LGT;
            case "VEC": return VEC;
            case "WSD": return WSD;

            default : throw new AssertionError("Unknown codeName : " + codeName);
        }
    }

}