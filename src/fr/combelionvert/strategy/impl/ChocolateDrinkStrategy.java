package fr.combelionvert.strategy.impl;

import fr.combelionvert.strategy.DrinkStrategy;

public class ChocolateDrinkStrategy implements DrinkStrategy {

    public String makeDrink(boolean isHot) {
        return isHot ? "Hh:" : "H:";
    }
}
