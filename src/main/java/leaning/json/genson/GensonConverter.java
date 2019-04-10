package json.genson;

import java.io.*;

import java.util.*;
import java.util.Map.Entry;

import com.owlike.genson.Genson;
import com.owlike.genson.GenericType;

public class GensonConverter {
    public void convertComplex() {
        Genson genson = new Genson();

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File( classLoader.getResource("sqlmap.json").getFile())),"UTF8"));
            
            List<Map<String, String>> persons = genson.deserialize(reader, List.class);

            List<Map<String, String>> sqlMaps = new ArrayList<Map<String, String>>();

            Map<String, String> hashMap = null;
            Set<Entry<String, String>> keys = null;
            String gubun = "";
            Iterator<Entry<String, String>> iterator = null;
            Entry<String, String> entry = null;
            for(int i = 0; i < persons.size(); i++) {
                hashMap = persons.get(i);

                keys = hashMap.entrySet();
                iterator = keys.iterator();
                
                while(iterator.hasNext()) {
                    
                    entry = iterator.next();

                    if( entry.getKey().equals("gubun") ) {
                        System.out.println(entry.getValue());
                    } else {
                        String str = entry.toString();
                        
                        str = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]"));
                        // System.out.println(str);

                        sqlMaps = new ArrayList<Map<String, String>>();

                        String[] sql = str.split("}");

                        for( int inner = 0; inner < sql.length; inner++ ) {
                            Map<String, String> maps = new HashMap<String, String>();

                            String innerStr = sql[inner].replace(", {", "").replace("{", "");

                            String key = innerStr.substring(innerStr.indexOf("key=") + 4);
                            
                            String value = innerStr.substring(innerStr.indexOf("value=") + 6, innerStr.indexOf("key=") - 2).trim();

                            maps.put(key, value);

                            sqlMaps.add(maps);
                        }

                        System.out.println(sqlMaps);
                    }
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            
        }

    }
}