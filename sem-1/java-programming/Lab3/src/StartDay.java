public class StartDay{
    String[] deals;
    public StartDay(String ... di){
        deals = di;
    }
    public void describeHouse(){
        System.out.println("We dont know anything yet");
    }
    public void go(){
        MrsMinoge Minoge = new MrsMinoge();
        Human[] people = {new NoNaika(deals,Minoge), Minoge, new Barber()};
        for (TypeOfDay typeOfDay: TypeOfDay.values()){
            for (Human human: people){
                human.startDoingSmt(typeOfDay);
            }
        }
    }
}