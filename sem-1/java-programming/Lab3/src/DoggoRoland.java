public class DoggoRoland extends Dog{
    Boolean raspKos, isFaceOk, isTailOk, isDoggyBeautiful;
    public DoggoRoland(){
        this.raspKos = false;
        this.isFaceOk = false;
        this.isTailOk = false;
        this.isDoggyBeautiful = false; // ohh no
    }
    @Override
    public void goToBath(TypeOfDay typeOfDay) {
        if (typeOfDay == TypeOfDay.MORNING) {
            System.out.println("Роланд купается только по утрам, так как перед купанием ему надо распускать косички, "
                    + (raspKos ? "а это требовало много времени" : "но Незнайка про это забыл и купал его просто так"));
        }
    }
    @Override
    public  void goToBarber(){
        System.out.println("Где Роланду "
                + (!raspKos ? "расплетали косички" : "заплели косички еще больше") + ", "
                + (isFaceOk ? "подстригали морду" : "напудрили щечки") + " и "
                + (isTailOk ? "" : "забыли про ") + "хвост, "
                + (isDoggyBeautiful ? "восстанавливая нарушенную красоту" : "так как доггерс и так всегда красивый"));
    }
}
