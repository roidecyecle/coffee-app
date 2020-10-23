package fr.combelionvert.domain;

import fr.combelionvert.entity.DrinkType;
import fr.combelionvert.repositories.OrderRepository;

public class DrinkReport {

    private final OrderRepository orderRepository;

    public DrinkReport(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String generateConsoleReport() {
        StringBuilder str = new StringBuilder("Total purchase orders: ");
        double allPurchaseAmount = 0.0;
        for(DrinkType type : DrinkType.values()){
            Long countAllOrder = orderRepository.getNbOfAllOrderByDrinkType(type);
            str.append(type).append("=");
            str.append(countAllOrder).append(", ");
            allPurchaseAmount += type.getTotalSoldAmount(countAllOrder);
        }
        str.append("Total sales=").append(allPurchaseAmount).append(" euros");
        System.out.println(str.toString());
        return str.toString();
    }
}
