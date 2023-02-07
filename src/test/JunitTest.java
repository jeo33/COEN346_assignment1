package test;

import ca.concordia.processmanagement.ProcessManager;
import ca.concordia.processmanagement.UserDefinedExceptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class JunitTest {

    private static  ProcessManager testcase;
    private static  int id;


    //set up
    @BeforeAll
    public static void setUp() {
        testcase=new ProcessManager();
        id=0;
    }

    @Test
    void CreatAndTerminate() {

       try{
           testcase=new ProcessManager();
           id=testcase.createProcess();
           testcase.terminateProcess(id);
       }catch (UserDefinedExceptions e){
           assertNull(e);
       }
        assertEquals(201, testcase.getPidManager().AvailableBits());
        assertFalse(testcase.checkPID(id));
    }

    @Test
    void FullError()  {

        for(int i=testcase.getPidManager().getMinPid();i<=testcase.getPidManager().getMaxPid();i++)
        {
            try{testcase.createProcess();}catch (UserDefinedExceptions e){
                System.out.println(e);
            }
        }
        try{testcase.createProcess();}catch (UserDefinedExceptions e){
            System.out.println(e);
        }
        assertEquals(0, testcase.getPidManager().AvailableBits());

        assertEquals(201, testcase.getPidManager().NotAvailableBits());
    }
    @Test
    void OneMoreToFull()  {
        Random randomNumber=new Random();
        id=randomNumber.nextInt(200)+testcase.getPidManager().getMinPid();
        System.out.println("\narbitrary pid is :"+ id);
        try{
            testcase.terminateProcess(id);
            assertEquals(200, testcase.getPidManager().NotAvailableBits());
            id=testcase.createProcess();
            System.out.println("Create process for the 1st time");
            assertEquals(201, testcase.getPidManager().NotAvailableBits());
            testcase.createProcess();
            System.out.println("Create process for the 2ed time");
        }catch (UserDefinedExceptions e){
            System.out.println(e);}
    }

    @Test
    void TerminateAll()  {
        for(int i=testcase.getPidManager().getMinPid();i<=testcase.getPidManager().getMaxPid();i++)
        {
           try
           {
               if(testcase.checkPID(i))testcase.terminateProcess(i);
           }catch (UserDefinedExceptions e){
               System.out.println(e);
           }
        }
        System.out.println("After Terminate all processes");
        assertEquals(201, testcase.getPidManager().AvailableBits());
        assertEquals(0, testcase.getPidManager().NotAvailableBits());
    }






}
