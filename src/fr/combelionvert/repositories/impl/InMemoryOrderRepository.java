package fr.combelionvert.repositories.impl;

import fr.combelionvert.entity.DrinkType;
import fr.combelionvert.entity.Order;
import fr.combelionvert.repositories.OrderRepository;

import java.util.LinkedHashSet;
import java.util.Set;

public class InMemoryOrderRepository implements OrderRepository {

    private final Set<Order> orders = new LinkedHashSet<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Set<Order> getAllOrders() {
        return orders;
    }

    public Long getNbOfAllOrderByDrinkType(DrinkType drinkType) {
        return orders.stream()
                .filter(o->o.getDrinkType().equals(drinkType))
                .count();
    }


}
