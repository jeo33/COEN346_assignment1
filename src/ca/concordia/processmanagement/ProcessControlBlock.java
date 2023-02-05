package ca.concordia.processmanagement;

public class ProcessControlBlock {

    int PID;
    int ProcessStatus;
    //0 new
    //1 ready
    //2 waiting
    //3 partially finished
    //4 terminated

    public ProcessControlBlock(int pid,int statue)
    {
        PID=pid;
        ProcessStatus=statue;
    }
    public void setProcessStatus(int statue) throws UserDefinedExceptions
    {
        int temp=statue;
        switch (temp) {

            case 0:
                this.ProcessStatus=0;
                break;
            case 1:
                this.ProcessStatus=1;
                break;
            case 2:
                this.ProcessStatus=2;
                break;
            case 3:
                this.ProcessStatus=3;
                break;
            case 4:
                this.ProcessStatus=4;
                break;
            default:
                throw new UserDefinedExceptions("not available statue");

        }

    }

}
