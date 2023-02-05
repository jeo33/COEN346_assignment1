package ca.concordia.processmanagement;
import java.util.BitSet;
public class PIDManager {

    private static final int MIN_PID = 300;
    private static final int MAX_PID = 500;

    BitSet PIDmap;

    public PIDManager()
    {
        allocateMap();
    }

    public void allocateMap()
    {
        PIDmap=new BitSet(MAX_PID-MIN_PID+1);
    }

    public int allocatePid() throws UserDefinedExceptions
    {
        for(int i=0;i<=MAX_PID-MIN_PID;i++) {

            if (!PIDmap.get(i) ) {
                PIDmap.flip(i);
                return (i + MIN_PID);
            }
        }
        throw new UserDefinedExceptions("ERROR:All pids are taken, there is no available PID ");

    }
    public void releasePid(int pid)throws UserDefinedExceptions
    {
        if(PIDmap.get(pid-MIN_PID))
        {
            PIDmap.flip(pid-MIN_PID);
            System.out.println(pid+" get released successfully!");
        }
        else
        {
            throw new UserDefinedExceptions("ERROR : Target PID not exist");
        }
    }


    public void NotAvailableBits()
    {
        int bits=0;
        for(int i=0;i<=MAX_PID-MIN_PID;i++)
        {
            if (PIDmap.get(i))bits++;

        }
        System.out.println("Not Available pid numbers: "+bits);
    }
    public void AvailableBits()
    {
        int bits=0;
        for(int i=0;i<=MAX_PID-MIN_PID;i++)
        {
            if (!PIDmap.get(i))bits++;
        }
        System.out.println("Available pid numbers: "+bits);
    }
    public int getMinPid()
    {
        return  MIN_PID;
    }
    public int getMaxPid()
    {
        return  MAX_PID;
    }

}
