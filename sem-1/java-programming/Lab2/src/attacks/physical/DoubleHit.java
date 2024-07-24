package attacks.physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class DoubleHit extends PhysicalMove {
    public DoubleHit(){
        super(Type.NORMAL,35,1,0,2);
    }
    @Override
    public String describe(){
        return "Hits TWICE!";
    }
}
