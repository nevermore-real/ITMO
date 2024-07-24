package MajorClasses;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayDeque;

public class Response implements Serializable {

    private static final long serialVersionUID = 1L;
    ArrayDeque<Product> collection;
    private String message;
    public boolean flag;
    public void setMessage(String message){
        this.message = message;
    }
    public void setFlagTrue(){
        this.flag = true;
    }
    public String getMessage(){
        return this.message;
    }
    public void printMessage(){
        System.out.println(message);
    }
    public void setCollection(ArrayDeque<Product> collection){
        this.collection = collection;
    }
    public ArrayDeque<Product> getCollection(){
        return this.collection;
    }
}
