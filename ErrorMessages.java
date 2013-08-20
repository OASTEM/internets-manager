
/**
 * Write a description of interface ErrorMessages here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum ErrorMessages
{
    //public ErrorMessages(){
    ERR00 ("ERR00: COMMAND NOT FOUND"),
    ERR01 ("ERR01: INVALID INPUT SYNTAX"),
    ERR02 ("ERR02: INVALID INPUT"),
    ERR03 ("ERR03: ACCOUNT NOT FOUND"),
    ERRO4 ("ERR04: INPUT LIST EMPTY"),
    ERR05 ("ERR05: INVALID SELECTION"),
    ERRO6 ("ERR06: NEGATIVE INPUT FOUND"),
    ERR07 ("ERR07: OPTION FLAG MISSING"),
    ERR08 ("ERR08: OPTION NOT FOUND"),
    //ERRO9:",
    //ERR10:",
    //ERR11:",
    //ERR12:",
    ERR13 ("FATAL ERROR");
    //}
    private String err;
        
    private ErrorMessages(String message){
        this.err = message;
    }
    public String errors(){
        return err;
    }
}
