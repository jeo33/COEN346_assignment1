package ca.concordia.processmanagement;

public class UserDefinedExceptions extends Exception{
    public UserDefinedExceptions(String str)
    {
        // Calling constructor of parent Exception
        super(str);
    }
}
