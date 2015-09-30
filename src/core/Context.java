package core;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<String, Object> vars;
    private Map<String, Object> index;
    
    public Context() {
        vars = new HashMap<String, Object>(); // Organisées par Type->varName->obj
        index = new HashMap<String, Object>(); // Organisées par varName->obj
    }
    
    public void registerVar(String varType, String varName, Object value){
        Map<String, Object> internal;
        if(vars.containsKey(varType))
            internal = (HashMap<String, Object>) vars.get(varType);
        else {
            // First variable
            internal = new HashMap<String, Object>();
            vars.put(varType, internal);
        }
        internal.put(varName, value);
        index.put(varName, value);
    }
    
    public void registerVar(String varName, Object value){
        String varType = value.getClass().getName();
        registerVar(varType, varName, value);
    }
    
    public Object getVar(String varName){
        if(index.containsKey(varName))
            return index.get(varName);
        return null;
    }
    
    public Object getVar(String varType, String varName){
        Map<String, Object> internal;
        if(vars.containsKey(varType)){
            internal = (HashMap<String, Object>) vars.get(varType);
            if(internal.containsKey(varName))
                return internal.get(varName);
        }
        return null;
    }
    
    public void deleteVar(String varType, String varName){
        Map<String, Object> internal;
        if(vars.containsKey(varType)){
            internal = (HashMap<String, Object>) vars.get(varType);
            if(internal.containsKey(varName))
                internal.remove(varName);
        }
    }
    
    public void deleteVar(String varName){
        if(hasVar(varName)){
            String varType = getVar(varName).getClass().getName();
            deleteVar(varType, varName);
        }
    }

    public boolean hasVar(String varName){
        if(index.containsKey(varName))
            return true;
        return false;
    }
    
    public boolean hasVar(String varType, String varName){
        Map<String, Object> internal;
        if(vars.containsKey(varType)){
            internal = (HashMap<String, Object>) vars.get(varType);
            if(internal.containsKey(varName))
                return true;
        }
        return false;
    }
}
