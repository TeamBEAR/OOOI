package core;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<String, Object> vars;
    
    public Context() {
        vars = new HashMap<String, Object>();
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
