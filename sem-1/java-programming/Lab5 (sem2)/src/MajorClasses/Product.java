package MajorClasses;

import MyExceptions.WrongArgumentException;
import MyExceptions.WrongInputException;

import java.math.BigDecimal;

public class Product {

    private int id; // Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    //// Не больше чем 2^31 = 10 цифр
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private BigDecimal price; //Поле не может быть null, Значение поля должно быть больше 0
    private String partNumber; //Длина строки должна быть не меньше 15, Строка не может быть пустой, Длина строки не должна быть больше 98, Поле может быть null

    public static final int PART_NUMBER_LEFT_BOUND = 15;
    public static final int PART_NUMBER_RIGHT_BOUND = 98;
    public static final String[] PRODUCT_FIELDS = {"name", "price", "partnumber", "unitofmeasure"};
    public static final String[] PRODUCT_COMPLEX_FIELDS = {"x", "y", "weight","height"};
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Person owner; //Поле не может быть null

    public int getId (){
        return this.id;
    }
    public void setId (int id) throws WrongArgumentException{
        if (id > 0){
            this.id = id;
        }else{
            throw new WrongArgumentException("id должно быть больше нуля");
        }
    }
    public String getName (){
        return this.name;
    }
    public void setName (String name) throws WrongArgumentException{
       if(name != null && !name.isEmpty() && !name.equalsIgnoreCase("null")){
           this.name = name;
       }else{
           throw new WrongArgumentException("Имя не может быть пустым");
       }
    }
    public Coordinates getCoordinates (){
        return this.coordinates;
    }
    public void setCoordinates (Coordinates coordinates) throws WrongArgumentException{
        if(coordinates!=null){
            this.coordinates = coordinates;
        }else{
            throw new WrongArgumentException("Координаты не могут быть пустыми");
        }
    }
    public BigDecimal getPrice (){
        return this.price;
    }
    public void setPrice (BigDecimal price) throws WrongArgumentException{
        if (price.compareTo(new BigDecimal(0)) > 0) {
            this.price = price;
        }else{
            throw new WrongArgumentException("Цена должна быть больше нуля");
        }
    }

    public java.time.LocalDate getCreationDate (){
        return this.creationDate;
    }
    public void setCreationDate (java.time.LocalDate date) throws WrongArgumentException{
        if (date != null){
            this.creationDate = date;
        }else{
            throw new WrongArgumentException("Дата создания не может быть пустой");
        }
    }
    public String getPartNumber (){
        return this.partNumber;
    }
    public void setPartNumber (String partNumber) throws WrongArgumentException{
        if(partNumber.length() >= PART_NUMBER_LEFT_BOUND && partNumber.length() <= PART_NUMBER_RIGHT_BOUND){
            this.partNumber = partNumber;
        }else{
            throw new WrongArgumentException("Длина номера должна быть не меньше " + PART_NUMBER_LEFT_BOUND + " и не больше " + PART_NUMBER_RIGHT_BOUND);
        }
    }
    public UnitOfMeasure getUnitOfMeasure (){
        return this.unitOfMeasure;
    }
    public void setUnitOfMeasure (UnitOfMeasure unitOfMeasure) throws WrongArgumentException{
        if (unitOfMeasure != null){
            this.unitOfMeasure = unitOfMeasure;
        }else{
            throw new WrongArgumentException("Единицы измерения не могут быть пустыми");
        }
    }
    public Person getPerson (){
        return this.owner;
    }
    public void setPerson (Person owner) throws WrongArgumentException{
        if (owner != null){
            this.owner = owner;
        }else{
             throw new WrongArgumentException("Человек не может быть пустым ...");
        }
    }

    @Override
    public String toString(){

        Coordinates coordinates = this.getCoordinates();
        Person owner = this.getPerson();

        return("  id: " + this.getId() +"\n" +
        "  name: " + this.getName() + "\n" +
        "  Coordinates:" + "\n" +
        "       - x : " + coordinates.getX() + "\n" +
        "       - y : " + coordinates.getY() + "\n" +
        "  creationDate: " + this.getCreationDate() + "\n" +
        "  price: " + this.getPrice() + "\n" +
        "  partNumber: " + this.getPartNumber() + "\n" +
        "  UnitOfMeasure: " + this.getUnitOfMeasure() + "\n" +
        "  Person:" + "\n" +
        "       - name : " + owner.getName() + "\n" +
        "       - height : " + owner.getHeight() + "\n" +
        "       - weight : " + owner.getWeight() + "\n" +
        "       - Color : " + owner.getEyeColor());
    }

}