package execution;

import java.lang.reflect.Constructor;

import core.Context;
import core.Main;
import processing.core.PApplet;

public class CommandFactory {
    
    public static Command get(String commandClass, Context context, String[] params) {
        return null;
    }
    
    public static Command get(String commandClass, Context context) {
        try{
            Class cls = Class.forName("execution.commands." + commandClass); // Chercher le type
            Constructor ctr = cls.getDeclaredConstructor(Context.class); // Chercher un constructeur
            return (Command) ctr.newInstance(context);
        }catch(Exception e){
            e.printStackTrace();
        }
         
        return null;
    }
}
