package attacks.special;

import ru.ifmo.se.pokemon.*;

public class Hurricane extends SpecialMove{
    public Hurricane(){
        super(Type.FLYING, 120, 0.7);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        if (new Effect().chance(0.3).success()){
            p.confuse();
        }
    }
    @Override
    public String describe() {
        return "throws Hurricane!";
    }
}
