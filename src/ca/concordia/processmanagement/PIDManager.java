package ca.concordia.processmanagement;
import java.util.BitSet;
public class PIDManager {

    private static final int MIN_PID = 300;//lower bound
    private static final int MAX_PID = 500;//upper bound
    //therefore all together there are 201

    BitSet PIDmap;//we use the BitSet to distribute the PID

    public PIDManager() //constructor
    {
        allocateMap();
    }

    public void allocateMap()
    {
        PIDmap=new BitSet(MAX_PID-MIN_PID+1);
    } //by creating a size of 201 BitSet we have our pid array
    //if it's 1 means;0 means it's available.

    public int allocatePid() throws UserDefinedExceptions
    {
        for(int i=0;i<=MAX_PID-MIN_PID;i++) {

            if (!PIDmap.get(i) ) {
                PIDmap.flip(i);
                return (i + MIN_PID);
            }//if it's false which means it's available,we flip the bit and
        }
        throw new UserDefinedExceptions("ERROR:All pids are taken, there is no available PID ");
        //if it failed we throw the exception
    }

    public void releasePid(int pid)throws UserDefinedExceptions//function for releasing pid
    {
        if(PIDmap.get(pid-MIN_PID))//we convert the pid of[200,500] to [0,200] bit array
        {
            PIDmap.flip(pid-MIN_PID);//if it exits or taken, we released its id by flipping bits.
            System.out.println(pid+" get released successfully!");
        }
        else
        {
            throw new UserDefinedExceptions("ERROR : Target PID not exist");//if it failed ,we throw the exception
        }
    }


    public int NotAvailableBits()//this is a function to avoid repetition
    {
        int bits=0;//counter for counting the bits
        for(int i=0;i<=MAX_PID-MIN_PID;i++)
        {
            if (PIDmap.get(i))bits++; //if not available or false , we increase the counter

        }
        System.out.println("Not Available pid numbers: "+bits);//print the available bits
        return bits; //return it
    }
    public int AvailableBits()//this is a function to avoid repetition
    {
        int bits=0;//counter for counting the bits
        for(int i=0;i<=MAX_PID-MIN_PID;i++)//if it's available or true , we increase the counter
        {
            if (!PIDmap.get(i))bits++;
        }
        return bits;
    }
    public int getMinPid()
    {
        return  MIN_PID;
    }//get function
    public int getMaxPid()
    {
        return  MAX_PID;
    }//get function

}
