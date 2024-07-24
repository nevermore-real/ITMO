public class Barber extends Human{
    public Barber(){
        super("Barber", Place.BARBERSHOP);
    }
    @Override
    public void startDoingSmt(TypeOfDay typeOfDay){} //hes doin his job
    @Override
    public void describe(String t){};
    public void serveOneDog(Dog dog) {
        if (dog instanceof DoggoRoland) {
            DoggoRoland roland = (DoggoRoland) dog;
            roland.raspKos = false;
            barbTail(roland);
            barbFace(roland);
            roland.isDoggyBeautiful = roland.isTailOk && roland.isFaceOk;
        } else {
            DoggoMimi mimi = (DoggoMimi) dog;
            curlHair(mimi);
            lubricateWool(mimi);
            mimi.isWoolShining = mimi.isLubricatedWool;
            tintLashes(mimi);
            paintEyes(mimi);
            mimi.areEyesMoreImpressive = mimi.areLashesTinted && mimi.areEyesPainted;
        }
    }
    public void barbTail (DoggoRoland roland){roland.isTailOk = true;}
    public void barbFace (DoggoRoland roland){roland.isFaceOk = true;}
    public void curlHair(DoggoMimi mimi){mimi.isCurledHair = true;}
    public void lubricateWool(DoggoMimi mimi){mimi.isLubricatedWool = true;}
    public void tintLashes(DoggoMimi mimi){mimi.areLashesTinted = true;}
    public void paintEyes(DoggoMimi mimi){mimi.areEyesPainted = true;}


}
