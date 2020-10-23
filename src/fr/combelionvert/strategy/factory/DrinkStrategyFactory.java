package fr.combelionvert.strategy.factory;

import fr.combelionvert.entity.DrinkType;
import fr.combelionvert.strategy.DrinkStrategy;
import fr.combelionvert.strategy.impl.ChocolateDrinkStrategy;
import fr.combelionvert.strategy.impl.CoffeeDrinkStrategy;
import fr.combelionvert.strategy.impl.OrangeJuiceDrinkStrategy;
import fr.combelionvert.strategy.impl.TeaDrinkStrategy;

public class DrinkStrategyFactory {

    private static final DrinkStrategyFactory INSTANCE = new DrinkStrategyFactory();

    public static DrinkStrategyFactory getINSTANCE(){
        return INSTANCE;
    }

    public DrinkStrategy getStrategy(DrinkType drinkType) {
        DrinkStrategy drinkStrategy;
        switch (drinkType){
            case TEA:
                drinkStrategy = new TeaDrinkStrategy();
                break;
            case COFFEE:
                drinkStrategy = new CoffeeDrinkStrategy();
            break;
            case CHOCOLATE:
                drinkStrategy = new ChocolateDrinkStrategy();
            break;
            case ORANGE_JUICE:
                drinkStrategy = new OrangeJuiceDrinkStrategy();
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + drinkType);
        }
        return drinkStrategy;
    }
}
