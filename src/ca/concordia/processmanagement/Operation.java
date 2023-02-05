package ca.concordia.processmanagement;

public interface Operation {
    public int createProcess() throws UserDefinedExceptions;
    public void terminateProcess(int pid) throws UserDefinedExceptions;

}
