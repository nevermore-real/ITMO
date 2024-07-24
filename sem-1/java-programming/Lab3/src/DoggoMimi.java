
public class DoggoMimi extends Dog {
    Boolean isCurledHair, isLubricatedWool, isWoolShining, areLashesTinted, areEyesPainted, areEyesMoreImpressive;
    public  DoggoMimi(){
        this.isCurledHair = false;
        this.isLubricatedWool = false;
        this.isWoolShining = false;
        this.areLashesTinted = false;
        this.areEyesPainted = false;
        this.areEyesMoreImpressive = false;
    }
    @Override
    public void goToBath(TypeOfDay typeOfDay){} // Купается молча!
    @Override
    public  void goToBarber(){
        System.out.println("Мимишке же в это время " + (isCurledHair ? "подвивали щипцами кудряшки" : "дали покушать мясца") + ", "
                + (isLubricatedWool ? "смазывали шерсть бриолином" : "показывали смешариков по телевизору") + ", чтобы "
                + (isWoolShining ? "она красиво блестела" : "она не скучала") + ", "
                + (areLashesTinted ? "подкрашивали черной краской ресницы" : "щекотали пузико") + ", "
                + (areEyesPainted ? "подрисовывали синькой глаза" : "гладили по спинке") + ", чтобы "
                + (areEyesMoreImpressive ? "глаза казались больше и выразительней" : "показать, что она хороший песик"));
    }
}
