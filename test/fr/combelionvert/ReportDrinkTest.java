package fr.combelionvert;

import fr.combelionvert.domain.DrinkReport;
import fr.combelionvert.entity.DrinkType;
import fr.combelionvert.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportDrinkTest {

    OrderRepository orderRepository = mock(OrderRepository.class);
    DrinkReport drinkReport = new DrinkReport(orderRepository);

    @Test
    public void shouldGenerateAStringReport(){
        when(orderRepository.getNbOfAllOrderByDrinkType(DrinkType.TEA))
                .thenReturn(Long.valueOf(10));
        when(orderRepository.getNbOfAllOrderByDrinkType(DrinkType.CHOCOLATE))
                .thenReturn(Long.valueOf(15));
        when(orderRepository.getNbOfAllOrderByDrinkType(DrinkType.COFFEE))
                .thenReturn(Long.valueOf(120));
        when(orderRepository.getNbOfAllOrderByDrinkType(DrinkType.ORANGE_JUICE))
                .thenReturn(Long.valueOf(0));
        Assertions.assertEquals("Total purchase orders: CHOCOLATE=15, COFFEE=120, ORANGE_JUICE=0, TEA=10, Total sales=83.5 euros", drinkReport.generateConsoleReport());
    }
}
