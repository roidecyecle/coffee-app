package fr.combelionvert.entity;

import java.text.MessageFormat;

public class Order {

    private final DrinkType drinkType;
    private final int sugar;
    private final boolean isHot;
    private final Double cash;

    public Order(DrinkType drinkType, int sugar, boolean isHot, Double cash) {
        this.drinkType = drinkType;
        this.sugar = sugar;
        this.isHot = isHot;
        this.cash = cash;
    }

    public DrinkType getDrinkType() {
        return this.drinkType;
    }

    public boolean isHot() {
        return isHot;
    }

    public String generateSugarAndStick(){
        return this.sugar > 0
                ? String.valueOf(this.sugar).concat(":0")
                : ":";
    }

    public boolean isCashSufficient(){
        return this.drinkType.isCashSufficient(this.cash);
    }

    public Double howMuchIsMissing(){
        return this.getDrinkType().howMuchIsMissing(this.cash);
    }

    @Override
    public String toString() {
        return MessageFormat.format("M:Order'{'drinkType={0}, sugar={1}'}'", drinkType, sugar);
    }


}
