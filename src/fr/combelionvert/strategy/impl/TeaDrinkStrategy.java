package fr.combelionvert.strategy.impl;

import fr.combelionvert.strategy.DrinkStrategy;

public class TeaDrinkStrategy implements DrinkStrategy {

    public String makeDrink(boolean isHot) {
        return isHot ? "Th:" : "T:";
    }
}
