package attacks.special;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class RevelationDance extends SpecialMove {
    public RevelationDance(Type t){
        super(t, 90,1);
    }
    @Override
    public String describe(){
        return "Uses Revelation Dance!";
    }
}
