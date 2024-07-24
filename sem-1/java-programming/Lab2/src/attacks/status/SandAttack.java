package attacks.status;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class SandAttack extends StatusMove {
    public SandAttack(){
        super(Type.GROUND,0,1);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        p.setMod(Stat.ACCURACY,-1);
    }
    @Override
    public String describe(){
        return "Uses Sand_Attack!";
    }
}
