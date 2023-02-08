package ca.concordia.processmanagement;

import java.util.HashMap;


public class ProcessManager implements Operation{
    PIDManager PidManager;//PIDManager has to be here to assign the pid
    HashMap<Integer, ProcessControlBlock> ReadyQueue = new HashMap<Integer, ProcessControlBlock>();
    //I am using a hashmap<k,v> to save the PCB,k is the pid which is unique and datatype for v is  ProcessControlBlock
    public ProcessManager()
    {
        setPidManager();
    }//constructor

    public PIDManager getPidManager()
    {
        return PidManager;
    }//get getPidManager function
    public void setPidManager()
    {
        PidManager=new PIDManager();
    }//set getPidManager function
    @Override
    public int createProcess() throws UserDefinedExceptions {
        //her we are using the interface ,this function is aim to allocate the PID according to the PIDManager.
        //if there is a PID available we will create the process and distribute the PID.
        int pid=PidManager.allocatePid();//allocate PID according to PIDManager
        ProcessControlBlock tempPCB=new ProcessControlBlock(pid,1);
        //Create PCB with initial statue 1 which stands for ready
        //0 new
        //1 ready
        //2 waiting
        //3 partially finished
        //4 terminated
        if(checkPID(pid))throw new UserDefinedExceptions("PID duplicated in the ready queue");
        //if there is no available pid throw exception
        else ReadyQueue.put(pid,tempPCB);//no error we save it to the Hashmap ReadyQueue<PID,ProcessControlBlock>
        return pid;
    }

    @Override
    public void terminateProcess(int pid) throws UserDefinedExceptions {
        //when release a PID
        PidManager.releasePid(pid);//call function to release the id ,if there is an error we throw exception
        if(!checkPID(pid))throw new UserDefinedExceptions("PID is not available");
        //if not exist we throw exception
        else ReadyQueue.remove(pid);//if everything is fine ,we remove the PCB from the hashmap
    }

    public boolean checkPID(int pid)//check PID exist or not.
    {
        for (int i : ReadyQueue.keySet()) {
           if(pid==i)
           {
               return true;
           }
        }
        return false;
    }

}
