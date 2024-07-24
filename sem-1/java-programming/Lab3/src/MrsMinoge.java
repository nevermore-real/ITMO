public class MrsMinoge extends Human {
    Boolean isSleeping;
    Boolean inTheBed;
    public MrsMinoge() {
        super("MrsMinoge", Place.MINOGE_HOME);
        this.isSleeping = true;
        this.inTheBed = true;
    }

    @Override
    public void describe(String t) {
        System.out.println(t);
    }

    @Override
    public void startDoingSmt(TypeOfDay typeOfDay) {
    } // sleeping?
    public void getUpFromBed(){

    }
}
