import pokemons.*;
import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;

public class Main {
    public static void main(String[] args){
        Battle b = new Battle();
        Pokemon p1 = new OricorioPomPom( "Ori",1);
        Pokemon p2 = new Kabutops("Kabutops",1);
        Pokemon p3 = new Deino("Deineris", 1);
        Pokemon p4 = new Kabuto("Kabu",1);
        Pokemon p5 = new Zweilous("Zweli",1);
        Pokemon p6 = new Hydreigon("Hydi",1);
        b.addFoe(p1);
        b.addFoe(p3);
        b.addFoe(p5);
        b.addAlly(p2);
        b.addAlly(p4);
        b.addAlly(p6);
        b.go();
    }
}