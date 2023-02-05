import ca.concordia.processmanagement.*;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws UserDefinedExceptions {

        ProcessManager testcase=new ProcessManager();
        int id=0;
        //scenario 1 create a process and print its PID ,then Terminate that process;
        System.out.println("Before process created");
        testcase.getPidManager().AvailableBits();
        testcase.getPidManager().NotAvailableBits();
        System.out.println();
        id=testcase.createProcess();
        System.out.println("pid is :"+ id);
        System.out.println("After process created");
        testcase.getPidManager().AvailableBits();
        testcase.getPidManager().NotAvailableBits();
        System.out.println();
        System.out.println("After process terminated");
        testcase.terminateProcess(id);
        testcase.getPidManager().AvailableBits();
        testcase.getPidManager().NotAvailableBits();
        System.out.println();
        //scenario 2 Create as many processes as ID’s available and then create one more
        //process, you should get an error
        for(int i=testcase.getPidManager().getMinPid();i<=testcase.getPidManager().getMaxPid();i++)
        {
            testcase.createProcess();
        }
        try{testcase.createProcess();}catch (UserDefinedExceptions e){
            System.out.println(e);
        }

        //scenario 3 Create as many processes as ID’s available and then
        //terminate one process. Create one new process, there should be no error. Create one
        //more process. There should be an error. Terminate all processes.
        Random randomNumber=new Random();
        id=randomNumber.nextInt(200)+testcase.getPidManager().getMinPid();
        System.out.println("\narbitrary pid is :"+ id);
        try{
            testcase.terminateProcess(id);
            id=testcase.createProcess();
            System.out.println("Create process for the 1st time");
            testcase.createProcess();
            System.out.println("Create process for the 2ed time");
        }catch (UserDefinedExceptions e){
            System.out.println(e);}
        //Terminate all processes

        for(int i=testcase.getPidManager().getMinPid();i<=testcase.getPidManager().getMaxPid();i++)
        {
            if(testcase.checkPID(i))testcase.terminateProcess(i);
        }
        System.out.println("After Terminate all processes");
        testcase.getPidManager().AvailableBits();
        testcase.getPidManager().NotAvailableBits();

    }
}