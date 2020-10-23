package fr.combelionvert.domain;

import fr.combelionvert.entity.Order;
import fr.combelionvert.repositories.OrderRepository;
import fr.combelionvert.services.BeverageQuantityChecker;
import fr.combelionvert.services.EmailNotifier;
import fr.combelionvert.strategy.DrinkStrategy;
import fr.combelionvert.strategy.factory.DrinkStrategyFactory;

public class DrinkMaker {

    DrinkStrategyFactory drinkStrategyFactory;
    OrderRepository orderRepository;
    BeverageQuantityChecker quantityChecker;
    EmailNotifier notifier;

    public DrinkMaker(DrinkStrategyFactory drinkStrategyFactory,
                      OrderRepository orderRepository,
                      BeverageQuantityChecker quantityChecker,
                      EmailNotifier notifier) {
        this.drinkStrategyFactory = drinkStrategyFactory;
        this.orderRepository = orderRepository;
        this.quantityChecker = quantityChecker;
        this.notifier = notifier;
    }

    public String generateOrder(Order order) {
        String checkBaseOfDrink = order.getDrinkType().getBaseOfDrink();
        if (!checkBaseOfDrink.isEmpty() && this.quantityChecker.isEmpty(checkBaseOfDrink)) {
            notifier.notifyMissingDrink(checkBaseOfDrink);
            return deliverMessage("A shortage "+checkBaseOfDrink+" is detected");
        }
        DrinkStrategy drinkStrategy = drinkStrategyFactory.getStrategy(order.getDrinkType());
        if (order.isCashSufficient()) {
            this.orderRepository.addOrder(order);
            return drinkStrategy.makeDrink(order.isHot()).concat(order.generateSugarAndStick());
        } else {
            return deliverMessage("No Sufficient cash, it's missing " + order.howMuchIsMissing());
        }
    }

    public String deliverMessage(String message) {
        return "M:".concat(message);
    }
}
