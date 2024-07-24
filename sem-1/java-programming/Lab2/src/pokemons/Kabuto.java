package pokemons;

import attacks.physical.RockTomb;
import attacks.special.MegaDrain;
import attacks.status.SandAttack;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Kabuto extends Pokemon{
    public Kabuto(String name, int level){
        super(name,level);
        setStats(30,80,90,55,45,55);
        setType(Type.WATER,Type.ROCK);
        setMove(new RockTomb(),new SandAttack(), new MegaDrain());
    }
}
