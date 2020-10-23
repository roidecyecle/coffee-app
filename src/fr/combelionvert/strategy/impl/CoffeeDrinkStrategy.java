package fr.combelionvert.strategy.impl;

import fr.combelionvert.strategy.DrinkStrategy;

public class CoffeeDrinkStrategy implements DrinkStrategy {

    public String makeDrink(boolean isHot) {
        return isHot ? "Ch:" : "C:";
    }
}
