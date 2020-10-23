package fr.combelionvert.repositories;

import fr.combelionvert.entity.DrinkType;
import fr.combelionvert.entity.Order;

import java.util.Set;

public interface OrderRepository {
    void addOrder(Order order);
    Set<Order> getAllOrders();
    Long getNbOfAllOrderByDrinkType(DrinkType drinkType);
}
