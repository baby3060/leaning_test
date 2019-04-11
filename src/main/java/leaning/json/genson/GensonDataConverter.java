package json.genson;

import java.io.*;

import java.util.*;
import java.util.Map.Entry;
import java.text.DecimalFormat;
import leaning.common.*;

import com.owlike.genson.Genson;
import com.owlike.genson.GenericType;

public class GensonDataConverter {
    public void convertData() {
        Genson genson = new Genson();

        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader reader = null;
        try {
            // 초단기 예보조회
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File( classLoader.getResource("sample_Data.json").getFile())),"UTF8"));

            Map<String, Object> map = genson.deserialize(reader, Map.class);
            
            // System.out.println(map);

            Map<String, Object> response = (Map<String, Object>)map.get("response");

            Map<String, Object> resultHeader = (Map<String, Object>)response.get("header");

            System.out.println(resultHeader.get("resultCode") + ", " + resultHeader.get("resultMsg"));

            Map<String, Object> resultBody = (Map<String, Object>)response.get("body");
            
            System.out.println("this page is " + resultBody.get("pageNo"));
            System.out.println("Total Count is " + resultBody.get("totalCount") + ", numOfRows Is " + resultBody.get("numOfRows"));

            Map<String, Object> resultItems = (Map<String, Object>)resultBody.get("items");
            
            List<Map<String, Object>> resultItem = (List<Map<String, Object>>)resultItems.get("item");

            // System.out.println(resultItem.size());

            Map<String, Object> item = null;

            String checkGubun = "";
            String tempValue = "";
            Double fcstValue = 0.0D;
            
            for( int i = 0; i < resultItem.size(); i++ ) {
                item = resultItem.get(i);

                item.remove("baseDate");
                item.remove("nx");
                item.remove("ny");
                item.remove("fcstDate");
                item.remove("baseTime");

                // System.out.println(item);

                checkGubun = (String)item.get("category");

                // 일단 문자열로 한 번 변환
                tempValue = (String)item.get("fcstValue").toString();

                // Double 값으로 변환
                fcstValue = Double.parseDouble(tempValue);

                DataGubun1 gubun1 = DataGubun1.valueOfCustom(checkGubun);

                System.out.println("에측 시간 : " + item.get("fcstTime") + ", 항목 : " + gubun1.getPrintName() + ", 표현값 : " + getStatusValue(fcstValue, gubun1)  );
            }


        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { reader.close(); } catch(Exception E){}
        }
    }

    private String getStatusValue(double fcstValue, DataGubun1 gubun1) {
        DecimalFormat df = new DecimalFormat("#,###.##");

        String result = "";

        // 하늘상태 일 경우
        if( gubun1.equals(DataGubun1.SKY) ) {
            result = new SkyStrategy().toSolve(fcstValue);
        } else if(gubun1.equals(DataGubun1.PTY)) {
            result = new PTYStrategy().toSolve(fcstValue);
        } else if(gubun1.equals(DataGubun1.LGT)) {
            result = new LGTStrategy().toSolve(fcstValue);
        } else if(gubun1.equals(DataGubun1.RN1)) {
            result = new RN1Strategy().toSolve(fcstValue);
        } else if(gubun1.equals(DataGubun1.VEC)) {
            result = new VECStrategy().toSolve(fcstValue);
        } else if(gubun1.equals(DataGubun1.WSD)) {
            result = new WSDStrategy().toSolve(fcstValue);      
        } else if(gubun1.equals(DataGubun1.UUU)) {
            result = new UUUStrategy().toSolve(fcstValue);      
        } else if(gubun1.equals(DataGubun1.VVV)) {
            result = new VVVStrategy().toSolve(fcstValue);      
        } else if(gubun1.equals(DataGubun1.T1H) || gubun1.equals(DataGubun1.REH)) {
            result = df.format(fcstValue);
        } 

        return result + gubun1.getSymbol();
    }

}