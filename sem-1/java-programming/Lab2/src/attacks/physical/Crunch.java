package attacks.physical;

import ru.ifmo.se.pokemon.*;

public class Crunch extends PhysicalMove {
    public Crunch(){
        super(Type.DARK, 80, 1);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        if (new Effect().chance(0.2).success()){
            p.setMod(Stat.DEFENSE,-1);
        }
    }
    @Override
    public String describe(){
        return "crunches the opponent!";
    }
}
