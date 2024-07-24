package MajorClasses;

import java.io.Serializable;

public class Response implements Serializable {

    private static final long serialVersionUID = 1L;
    private String message;
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void printMessage(){
        System.out.println(message);
    }
}
