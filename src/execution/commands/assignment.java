package execution.commands;

import java.lang.reflect.Constructor;

import core.Context;
import core.Main;
import core.types.Agent;
import execution.Command;
import processing.core.PApplet;

public class assignment implements Command{
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
            Class cls = Class.forName("core.types."+varType); // Chercher le type
            Constructor ctr = cls.getDeclaredConstructor(PApplet.class); // Chercher un constructeur
            Main mainInstance = (Main)context.getVar("Main", "Main"); // Recuperer la classe principale, le "vrai" contexte
            Object instance = ctr.newInstance(mainInstance); // Faire un objet du bon type, dans le contexte "Main"
            if(varType.equals("Agent")){ 
                ((Agent)instance).setName(varName); // Les agents sont identifies par leur nom de variable
                if(!context.hasVar(varType, varName))
                    mainInstance.setAgent(((Agent)instance)); // Si c'est le premier agent, l'on considere que c'est l'avatar de l'utilisateur
            }
            context.registerVar(varType, varName, instance); // Mettre la nouvelle variable en memoire de l'interprete
         } catch (Exception e){
             e.printStackTrace();
         }
    }

}
