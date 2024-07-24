package attacks.special;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class MegaDrain extends SpecialMove {
    public MegaDrain(){
        super(Type.GRASS,400,1);
    }
    private int dmg = 0;
    @Override
    protected void applyOppDamage(Pokemon var1, double var2) {
        var1.setMod(Stat.HP, (int)Math.round(var2));
        dmg = (int)Math.round(var2);
    }

    @Override
    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.HP,(int) Math.round(dmg*(-0.5)));
    }
    @Override
    public String describe(){
        return "Uses Mega_Drain and restores HP!";
    }

}
