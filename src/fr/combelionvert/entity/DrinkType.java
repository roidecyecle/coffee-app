package fr.combelionvert.entity;

/**
 * Drink type enumeration.
 */
public enum DrinkType {

    CHOCOLATE("milk", .5), COFFEE("milk", .6), ORANGE_JUICE("", .6), TEA("water", .4);

    private final Double price;
    private final String baseOfDrink;

    DrinkType(String base, Double p) {
        price = p;
        baseOfDrink = base;
    }

    public boolean isCashSufficient(Double cash) {
        return cash >= price;
    }

    public Double howMuchIsMissing(Double cash) {
        return price - cash;
    }

    public Double getTotalSoldAmount(Long totalSoldOrder) {
        return totalSoldOrder * price;
    }

    public String getBaseOfDrink() {
        return baseOfDrink;
    }
}
