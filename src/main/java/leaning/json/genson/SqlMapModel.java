package json.genson;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class SqlMapModel {
    private String gubun;
    private Map<String, String> sqlMap = new HashMap<String, String>();

    public void addSql(String key, String value) {
        if( sqlMap == null ) {
            throw new UnsupportedOperationException("Map이 Null입니다.");
        } else {
            this.sqlMap.put(key, value);
        }
    }

    public String getGubun() {
        return gubun;
    }

    public Map<String, String> getSqlMap() {
        return sqlMap;
    }

    public void setGubun(String gubun) {
        this.gubun = gubun;
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

            return this.gubun.equals(temp.getGubun());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result + this.gubun.hashCode();

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

        return "key is " + gubun + ", sqlMap(" + sqlMap.size() + ") : [" + mapStr.toString() + "]";
    }
}