package pw.cdmi.core.db;

import java.util.HashMap;
import java.util.Map;

public class JPQuery {

    private String jpql = null;

    private Map<String, Object> paramaters = new HashMap<String, Object>();

    private JPQuery(){
    }
    
    public JPQuery setParamater(String name, Object value) {
        if(value != null){
            this.paramaters.put(name, value);
        }
        return this;
    }

    public JPQuery setParamater(String name, Object value, boolean allowNull) {
        if(allowNull){
            this.paramaters.put(name, value);
        }else{
            if(value != null){
                this.paramaters.put(name, value);
            }
        }
        return this;
    }

    String getJPQL() {
        return this.jpql;
    }

    Map<String, Object> getParamaters() {
        return this.paramaters;
    }

    public static JPQuery createQuery(String jpql) {
        JPQuery impl = new JPQuery();
        impl.jpql = jpql;
        return impl;
    }

}
