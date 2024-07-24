package MajorClasses;

import MyExceptions.WrongArgumentException;

import java.math.BigDecimal;

public class Coordinates {

    private BigDecimal x; //Значение поля должно быть больше -964
    private Long y; //Максимальное значение поля: 187, Поле не может быть null

    public static final double COORDINATES_LEFT_BOUND = -964d;
    public static final long COORDINATES_RIGHT_BOUND = 187L;
    public BigDecimal getX (){
        return this.x;
    }
    public void setX (BigDecimal x) throws WrongArgumentException {
        if(x.compareTo(new BigDecimal(COORDINATES_LEFT_BOUND)) > 0){
            this.x = x;
        }else{
            throw new WrongArgumentException("X должен быть больше" + COORDINATES_LEFT_BOUND);
        }
    }
    public long getY (){
        return this.y;
    }
    public void setY (long y) throws WrongArgumentException{
        if (y <= COORDINATES_RIGHT_BOUND){
            this.y = y;
        }else{
            throw new WrongArgumentException("Y должен быть не больше" + COORDINATES_RIGHT_BOUND);
        }
    }
}
