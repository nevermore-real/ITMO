package pokemons;
import attacks.status.SwordsDance;

public class Kabutops extends Kabuto{
    public Kabutops (String name, int level){
        super(name,level);
        setStats(60,115,105,65,70,80);
        addMove(new SwordsDance());
    }
}