package MajorClasses;

public enum Color {

    RED("RED"),
    BLACK("BLACK"),
    YELLOW("YELLOW"),
    BROWN("BROWN");

    private final String value;

    Color(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static Color getValue(String input) {
        if (Validator.validateInt(input)) {
            try {
                return Color.values()[Integer.parseInt(input) - 1];
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Нет такого номера");
                return null;
            } catch (NumberFormatException e){
                System.out.println("Число должно быть целым");
                return null;
            }
        } else {
            for (Color unit : Color.values()) {
                try {
                    if (input.equalsIgnoreCase(unit.toString())) {
                        return unit;
                    }
                } catch (NullPointerException e) {
                    return null;
                }
            }
        }return null;
    }
}
