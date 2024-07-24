package MajorClasses;

import MyExceptions.ExecuteException;
import MyExceptions.WrongArgumentException;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Manager {

    public BigDecimal price;
    private ArrayList<String> arr;
    private final Product newProduct;
    Validator validator;

    public Manager(Product newProduct, ArrayList<String> args, BufferedReader reader) {
        this.newProduct = newProduct;
        this.arr = args;
        this.validator = new Validator(reader);
    }

    public void setAll() throws WrongArgumentException, ExecuteException {

        setProductFields();
        setCoordinates();
        setUnitOfMeasure();
        setPersonOwner();
    }

    public void setProductFields() throws WrongArgumentException, ExecuteException {

        validator.price = this.price;
        arr = validator.getValidProductFields(arr);
        newProduct.setName(arr.get(0));
        if (this.price == null) {
            newProduct.setPrice(new BigDecimal(arr.get(1)));
            newProduct.setPartNumber(arr.get(2));
        } else {
            newProduct.setPrice(this.price);
            newProduct.setPartNumber(arr.get(1));
        }
    }

    public void setCoordinates() throws WrongArgumentException, ExecuteException {

        ArrayList<String> validCoors = validator.getValidCoordinates();
        Coordinates coors = new Coordinates();
        coors.setX(new BigDecimal(validCoors.get(0)));
        coors.setY(Long.parseLong(validCoors.get(1)));
        newProduct.setCoordinates(coors);
    }

    public void setUnitOfMeasure() throws WrongArgumentException, ExecuteException{

        UnitOfMeasure unit = validator.getValidUnitOfMeasure();
        newProduct.setUnitOfMeasure(unit);
    }

    public void setPersonOwner() throws WrongArgumentException, ExecuteException{

        Person npc = new Person();
        ArrayList<String> parameters = validator.getValidPersonParameters();
        npc.setName(parameters.get(0));
        npc.setHeight(Long.parseLong(parameters.get(1)));
        npc.setWeight(Long.parseLong(parameters.get(2)));
        npc.setEyeColor(Color.valueOf(parameters.get(3)));
        newProduct.setPerson(npc);
    }
}

