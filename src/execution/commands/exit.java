package execution.commands;

import core.Context;
import core.Main;
import core.types.Agent;
import execution.Command;

public class exit implements Command{
    Main app;

    public exit(Context context){
        this.app = (Main)context.getVar("Main", "Main");
    }
    
    @Override
    public void execute() {
        app.exit();
    }

}
