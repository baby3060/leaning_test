package json.genson;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class SqlMapModel {
    private String key;
    private Map<String, String> sqlMap = new HashMap<String, String>();

    public void addSql(String key, String value) {
        if( sqlMap == null ) {
            throw new UnsupportedOperationException("Map이 Null입니다.");
        } else {
            this.sqlMap.put(key, value);
        }
    }

    public String getKey() {
        return key;
    }

    public Map<String, String> getSqlMap() {
        return sqlMap;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    @Override
    public boolean equals(Object obj) {
        if( obj == null ) {
            return false;
        }

        if( obj instanceof SqlMapModel ) {
            SqlMapModel temp = (SqlMapModel)obj;

            return this.key.equals(temp.getKey());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result + this.key.hashCode();

        return result;
    }

    @Override
    public String toString() {
        StringBuilder mapStr = new StringBuilder();

        Iterator<String> iterator = sqlMap.keySet().iterator();

        String key = "";

        while(iterator.hasNext()) {
            key = iterator.next();
            mapStr.append("(");
            mapStr.append(key);
            mapStr.append(" : ");
            mapStr.append(sqlMap.get(key));
            mapStr.append(")");
        }

        return "key is " + key + ", sqlMap(" + sqlMap.size() + ") : [" + mapStr.toString() + "]";
    }
}