package execution;

import java.util.LinkedList;

public class Execution {

    LinkedList<Instruction> buffer;
    
    public Execution(){
        this.buffer = new LinkedList<Instruction>();
    }
    
    public void add(Instruction ins){
        buffer.add(ins);
    }
    
    public void run(){
        while(!buffer.isEmpty())
            buffer.removeFirst().execute();
    }
}
