
public class NoNaika extends Human implements CanBathDoggy, CanTakeDoggosToBarber{
    Dog[] myDoggos = { new DoggoRoland(), new DoggoMimi() };
    String[] deals;
    MrsMinoge Minoge;
    public NoNaika(){
        super("NoNaika", Place.NONAIKA_HOME);
    }
    public NoNaika(String[] di, MrsMinoge M){
        super("NoNaika", Place.NONAIKA_HOME);
        deals = di;
        Minoge = M;
    }
    @Override
    public void startDoingSmt(TypeOfDay typeOfDay) {
        String mostImportantDeal = deals[0];

        switch (typeOfDay) {
            case MORNING:{
                describe(mostImportantDeal, "");
                describe("Мимишку приходилось купать " + TypeOfDay.values().length + " раза в день: утром, днем и вечером"); // по-русски же надо!
                batheYourDoggos(typeOfDay);
                describe("После утреннего купания собак тут же приходилось вести в собачью парикмахерскую");
                takeDoggosToBarber();
                takeDoggosHome();
                takeDoggosToMinoge();
            }
            case MIDDAY, EVENING: batheYourDoggos(typeOfDay);
        }
    }
    @Override
    public void describe(String t){
        System.out.println(t);
    }
    public void describe(String deal, String smt){
        System.out.println("Наиболее ответственным делом, которое поручили Незнайке, было "
                + (deal.startsWith("Wash") ? "купание собак" :
                deal.endsWith("Barber") ? "сводить собачек в парикмахерскую" :
                        deal.endsWith("Minoge") ? "отвести собачек к госпоже Миноге" : "поспать"));
    }
    @Override
    public void bathOneDoggy(Dog dog, TypeOfDay typeOfDay) {
        if (dog instanceof DoggoRoland){
            if (typeOfDay == TypeOfDay.MORNING) {
                raspustitKosichki(dog);
            }
        }
        dog.goToBath(typeOfDay);
    }
    public void batheYourDoggos(TypeOfDay typeOfDay) {
        this.place = Place.BATHROOM;
        for (Dog dog : myDoggos) {
            bathOneDoggy(dog, typeOfDay);
        }
    }
        public void raspustitKosichki (Dog roland){
            DoggoRoland rol = (DoggoRoland) roland;
            rol.raspKos = true;
        }
        @Override
        public void takeDoggosToBarber () {
            this.place = Place.BARBERSHOP;
            Barber barber = new Barber();
            for (Dog dog : myDoggos) {
                barber.serveOneDog(dog);
                dog.goToBarber();
            }
        }
        public void takeDoggosHome () {
            Place last_place = this.place;
            this.place = Place.NONAIKA_HOME;
            describe("Из " + (last_place == Place.BARBERSHOP ? "парикмахерской" : "магазина собачьих вкусняшек")
                    + " собаки возвращались в сопровождении " + (this.name.equals("NoNaika") ? "Незнайки " : "случайного любителя собак ")
                    + "домой");
        }
        public void takeDoggosToMinoge () {
            Boolean isMinogeSleeping = Minoge.isSleeping;
            this.place = Place.MINOGE_HOME;
            Minoge.isSleeping = false; // разбудили, негодяи!
            describe("После чего он вел их прямо в "
                    + (isMinogeSleeping ? "спальню к госпоже Миноге, " : "гостиную к госпоже Миноге, ")
                    + "которая к этому времени "
                    + ((!Minoge.isSleeping && Minoge.inTheBed) ? "вставала с постели" : "смотрела мультики"));
        }
    }
