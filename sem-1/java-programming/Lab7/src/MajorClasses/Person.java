package MajorClasses;

import MyExceptions.WrongArgumentException;

import java.io.Serializable;

public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long height; //Значение поля должно быть больше 0
    private long weight; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null

    public static final String[] PERSON_FIELDS = {"name", "height", "weight", "eyecolor"};

    public Person(){
        this.name = "";
        this.height = 1L;
        this.weight = 1;
        this.eyeColor = Color.BROWN;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) throws WrongArgumentException {
        if (name != null && !name.isEmpty() && !name.equalsIgnoreCase("null")) {
            this.name = name;
        } else {
            throw new WrongArgumentException("Имя не может быть null, не может быть пустым");
        }
    }

    public long getHeight() {
        return this.height;
    }

    public void setHeight(long height) throws WrongArgumentException{
        if (height > 0){
            this.height = height;
        }else{
            throw new WrongArgumentException("Рост должен быть больше нуля");
        }
    }

    public long getWeight() {
        return this.weight;
    }

    public void setWeight(long weight) throws WrongArgumentException{

        if (weight > 0){
            this.weight = weight;
        }else{
            throw new WrongArgumentException("Вес должен быть больше нуля");
        }
    }
    public Color getEyeColor (){
        return this.eyeColor;
    }
    public void setEyeColor (Color eyeColor) throws WrongArgumentException{
        if (eyeColor != null){
            this.eyeColor = eyeColor;
        }else{
            throw new WrongArgumentException("EyeColor не может быть null");
        }
    }
}

