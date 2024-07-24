import java.util.Objects;

public abstract class Human {
    String name;
    Place place;
    public Human(String name, Place place){  //реализация значений разных наследников
        this.name = name;
        this.place = place;
    }
    @Override
    public String toString() {
        return "Menya zovut" + " " + this.name;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof  Human)) return false;
        Human h = (Human) o;
        return Objects.equals(h.name, name);
    }
    @Override
    public  int hashCode(){
        return Objects.hash(name);
    }

    abstract void startDoingSmt(TypeOfDay typeOfDay);
    abstract void describe(String t);
}
