package ca.concordia.processmanagement;

public interface Operation {
    int createProcess() throws Exception;
    void terminateProcess(int pid) throws Exception;

}
