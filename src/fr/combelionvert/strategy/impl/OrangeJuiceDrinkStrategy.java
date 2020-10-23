package fr.combelionvert.strategy.impl;

import fr.combelionvert.strategy.DrinkStrategy;

public class OrangeJuiceDrinkStrategy implements DrinkStrategy {

    public String makeDrink(boolean isHot) {
        return "O:";
    }
}
