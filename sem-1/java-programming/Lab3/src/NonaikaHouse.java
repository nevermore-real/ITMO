public class NonaikaHouse {
    public static class Room { // может быть много комнат в доме! и в других внешних домах!!!
        public String sizeOfRoom, lightingOfRoom, decorOfRoom;
        public Object[] roomResidents;

        public Room(String roomSize, String roomLighting) {
            this.sizeOfRoom = roomSize;
            this.lightingOfRoom = roomLighting;
        }
    }

    public class NonaikaRoom extends Room {
        Object[] residents = {new NoNaika(), new DoggoRoland(), new DoggoMimi()};

        public NonaikaRoom() {
            super("просторная", "светлая");
            this.decorOfRoom = getDecor();
            this.roomResidents = residents;
        }
        public void description(){
            System.out.println("В доме, где теперь предстояло Незнайке жить, его поселили В светлой, просторной комнате, "
                    + getDecor());
        }

        public String getDecor() {
            String result;
            class NonaikaDecor {
                public String wallsPortraits(Object[] o) throws WrongResidentException {
                    if (o[0].getClass() != residents[0].getClass() && o[1].getClass() != residents[1].getClass()
                            && o[2].getClass() != residents[2].getClass()) {
                        throw new WrongResidentException("TheresSomeImposter!!!");
                    } else {
                        return "стены которой были украшены портретами Роланда, Мимишки и каких-то других собак\n";
                    }
                }

                public String roomBeds() throws WrongResidentException {
                    int bed1Size = 220;
                    int bed2Size = 130;
                    int bed3Size = 100;
                    String subResult = "Посреди комнаты стояли три кровати. ";
                    if (compareBed(bed1Size, bed2Size) && compareBed(bed2Size, bed3Size)) {
                        subResult += "Две были побольше -- на них спали Роланд и Незнайка."
                              + " Третья кровать была поменьше -- на ней спала Мимишка\n";
                    } else {
                        throw new WrongResidentException("WrongBedsMeansWrongResidents!");
                    }
                    return subResult;
                }

                public boolean compareBed(int num1, int num2) {
                    return num1 > num2;
                }

                public String wardrobe(Object mimi) throws DoggoNotFoundException {
                    if (!(mimi instanceof DoggoMimi)) {
                        throw new DoggoNotFoundException("Where's Mimi???");
                    } else {
                        return "У стены был зеркальный шкаф, в котором хранились собачьи фуфайки," +
                                " шубейки, попонки, жилетики, ночные пижамки, а также вечерние панталончики для Мими";
                    }
                }
            }
            NonaikaDecor nDecor = new NonaikaDecor();
            try {
                result = nDecor.wallsPortraits(residents) + nDecor.roomBeds() + nDecor.wardrobe(residents[2]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                result = "error!";
            }
            return result;
        }
    }
}
