public class Main {
    public static void main(String[] args) {
        StartDay day = new StartDay("Wash doggos", "Take Doggos to Barber" , "Take Doggos to Mistress") {
            @Override
            public void describeHouse() {
                NonaikaHouse house = new NonaikaHouse();
                NonaikaHouse.NonaikaRoom nnRoom = house.new NonaikaRoom();
                nnRoom.description();
            }
        };
        day.describeHouse();
        day.go();
    }
}