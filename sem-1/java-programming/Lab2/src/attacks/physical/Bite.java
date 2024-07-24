package attacks.physical;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Bite extends PhysicalMove {
    public Bite(){
        super(Type.DARK,60,1);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        if (new Effect().chance(0.3).success()){
            Effect.flinch(p);
        }
    }
    @Override
    public String describe(){
        return "Bites the opponent!";
    }
}
