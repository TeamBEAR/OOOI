package execution.commands;

import core.Context;
import core.Main;
import core.types.Agent;
import execution.Command;

public class accelerer implements Command{
    Agent agent;      // Seulement les agents peuvent accelerer
    Context context;

    public accelerer(Context context){
        agent = ((Main)context.getVar("Main", "Main")).getAgent();
    }
    
    public accelerer(String varName, Context context){
        agent = null;
        if(context.hasVar(varName))
            if(context.getVar(varName) instanceof Agent)
                agent = (Agent) context.getVar(varName);
    }
    
    @Override
    public void execute() {
        if(!agent.equals(null))
            agent.speed_up();
    }

}
