package execution.commands;

import java.lang.reflect.Constructor;

import core.Context;
import core.Main;
import core.types.Agent;
import execution.Instruction;
import processing.core.PApplet;

public class assignment implements Instruction{
    Context context;
    String varName;
    String varType;
    
    public assignment(String varType, String varName, Context context){
        this.varType = varType;
        this.varName = varName;
        this.context = context;
    }
        
    @Override
    public void execute() {

        try {
            Class cls = Class.forName("core.types."+varType);
            Constructor ctr = cls.getDeclaredConstructor(PApplet.class);
            Main mainInstance = (Main)context.getVar("Main", "Main");
            Object instance = ctr.newInstance(mainInstance);
            if(varType.equals("Agent")){
                ((Agent)instance).setName(varName);
                if(!context.hasVar(varType, varName))
                    mainInstance.setAgent(((Agent)instance));
            }
            context.registerVar(varType, varName, instance);
         } catch (Exception e){
             e.printStackTrace();
         }
    }

}
