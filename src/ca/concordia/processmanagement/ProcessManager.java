package ca.concordia.processmanagement;

import java.util.HashMap;


public class ProcessManager implements Operation{
    PIDManager PidManager;
    HashMap<Integer, ProcessControlBlock> ReadyQueue = new HashMap<Integer, ProcessControlBlock>();
    public ProcessManager()
    {
        setPidManager();
    }

    public PIDManager getPidManager()
    {
        return PidManager;
    }
    public void setPidManager()
    {
        PidManager=new PIDManager();
    }
    @Override
    public int createProcess() throws UserDefinedExceptions {

        int pid=PidManager.allocatePid();
        ProcessControlBlock tempPCB=new ProcessControlBlock(pid,1);
        if(checkPID(pid))throw new UserDefinedExceptions("PID duplicated in the ready queue");
        else ReadyQueue.put(pid,tempPCB);
        return pid;
    }

    @Override
    public void terminateProcess(int pid) throws UserDefinedExceptions {
        PidManager.releasePid(pid);
        if(!checkPID(pid))throw new UserDefinedExceptions("PID duplicated in the ready queue");
        else ReadyQueue.remove(pid);
    }

    public boolean checkPID(int pid)
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
