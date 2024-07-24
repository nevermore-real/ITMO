package pokemons;

import attacks.special.*;
import attacks.status.CalmMind;
import attacks.status.DoubleTeam;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class OricorioPomPom extends Pokemon {
    public OricorioPomPom(String name, int level){
        super(name,level);
        setStats(75,70,70,98,70,93);
        setType(Type.ELECTRIC,Type.FLYING);
        setMove(new Hurricane(),new DoubleTeam(),new CalmMind(), new RevelationDance(Type.ELECTRIC));
    }
}
