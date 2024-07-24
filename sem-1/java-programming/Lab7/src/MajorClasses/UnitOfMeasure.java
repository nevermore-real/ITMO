package MajorClasses;

public enum UnitOfMeasure {

    SQUARE_METERS("SQUARE_METERS"),
    MILLILITERS("MILLILITERS"),
    GRAMS("GRAMS");

    private final String value;
    UnitOfMeasure(final String value){
        this.value = value;
    }
    @Override
    public String toString(){
        return this.value;
    }
    public static UnitOfMeasure getValue(String input) {

        if (Validator.validateInt(input)) {
            try {
                return UnitOfMeasure.values()[Integer.parseInt(input) - 1];
            } catch (IndexOutOfBoundsException e) {
                return null;
            } catch (NumberFormatException e) {
                System.out.println("Число должно быть целым");
                return null;
            }
        } else{
            for (UnitOfMeasure unit : UnitOfMeasure.values()) {
                try {
                    if (input.equalsIgnoreCase(unit.toString())) {
                        return unit;
                    }
                } catch (NullPointerException e) {
                    return null;
                }
            }
        } return null;
    }
}