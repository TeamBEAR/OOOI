package execution;

import java.util.LinkedList;

public class Execution {

    LinkedList<Command> buffer;
    
    public Execution(){
        this.buffer = new LinkedList<Command>();
    }
    
    public void add(Command ins){
        if(ins != null)
            buffer.add(ins);
    }
    
    public void run(){
        while(!buffer.isEmpty())
            buffer.removeFirst().execute();
    }
}
