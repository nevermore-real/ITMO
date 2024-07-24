package Commands;

import MajorClasses.Product;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class CommandBox implements Serializable {

    private static final long serialVersionUID = 1L;
    private String command;
    private String someField;
    private String[] massiveArgs;
    private ArrayList<String> arrayArgs;
    private ArrayList<Integer> intArgs;
    private Product product;

    public CommandBox(String command){
        this.command = command;
    }
    public void setMassiveArgs(String[] args){
        this.massiveArgs = args;
    }
    public void setArrayArgs(ArrayList<String> args){
        this.arrayArgs = args;
    }
    public void setIntArgs(ArrayList<Integer> args){
        this.intArgs = args;
    }
    public void setProduct(Product product){
        this.product = product;
    }
    public void setField(String someField){
        this.someField = someField;
    }
    public String[] getMassiveArgs(){
        return this.massiveArgs;
    }
    public ArrayList<String> getArrayArgs(){
        return this.arrayArgs;
    }
    public String getField(){
        return this.someField;
    }
    public Product getProduct(){
        return this.product;
    }
    public String getCommand(){
        return this.command;
    }
    public ArrayList<Integer> getIntArgs(){
        return this.intArgs;
    }
}
