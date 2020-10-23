package fr.combelionvert;

import fr.combelionvert.domain.DrinkMaker;
import fr.combelionvert.entity.DrinkType;
import fr.combelionvert.entity.Order;
import fr.combelionvert.repositories.OrderRepository;
import fr.combelionvert.repositories.impl.InMemoryOrderRepository;
import fr.combelionvert.services.BeverageQuantityChecker;
import fr.combelionvert.services.EmailNotifier;
import fr.combelionvert.strategy.factory.DrinkStrategyFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class DrinkMakerTest {

    private final OrderRepository orderRepository = new InMemoryOrderRepository();
    private final BeverageQuantityChecker quantityChecker = mock(BeverageQuantityChecker.class);
    private final EmailNotifier notifier = mock(EmailNotifier.class);
    private final DrinkMaker drinkMaker = new DrinkMaker(DrinkStrategyFactory.getINSTANCE(),
            orderRepository, quantityChecker, notifier);

    @Test
    public void generateTeaOrderWithSugar() {
        assertEquals("T:1:0", transformOrder(DrinkType.TEA, 1, false, 10.0));
    }

    @Test
    public void generateTeaOrderWithoutSugar() {
        assertEquals("T::", transformOrder(DrinkType.TEA, 0, false, 10.0));
    }

    @Test
    public void generateChocolateOrderWithoutSugar() {
        assertEquals("H::", transformOrder(DrinkType.CHOCOLATE, 0, false, 10.0));
    }

    @Test
    public void generateCoffeeOrderWithTwoSugar() {
        assertEquals("C:2:0", transformOrder(DrinkType.COFFEE, 2, false, 10.0));
    }

    @Test
    public void deliverMessageForCoffeeOrderWithTwoSugar() {
        assertEquals("M:My message", drinkMaker.deliverMessage("My message"));
    }

    @Test
    public void deliverCoffeeWithOneSugarWithNoSufficientCash() {
        assertEquals("M:No Sufficient cash, it's missing 0.5", transformOrder(DrinkType.COFFEE, 2, false, 0.1));
    }

    @Test
    public void generateOrangeJuiceOrder() {
        assertEquals("O::", transformOrder(DrinkType.ORANGE_JUICE, 0, true, 10.0));
    }

    @Test
    public void generateOrangeJuiceOrderWithNoSufficientCash() {
        assertEquals("M:No Sufficient cash, it's missing 0.3", transformOrder(DrinkType.ORANGE_JUICE, 0, false, 0.3));
    }

    @Test
    public void generateHotCoffeeOrderWithoutSugar() {
        assertEquals("Ch::", transformOrder(DrinkType.COFFEE, 0, true, 10.0));
    }

    @Test
    public void generateHotChocolateOrderWithoutSugar() {
        assertEquals("Hh:1:0", transformOrder(DrinkType.CHOCOLATE, 1, true, 10.0));
    }

    @Test
    public void generateHotTeaOrderWithTwoSugar() {
        assertEquals("Th:2:0", transformOrder(DrinkType.TEA, 2, true, 10.0));
    }

    @Test
    public void shouldAddAnOrderWhenGenerateAnOrder() {
        drinkMaker.generateOrder(makeOrder(DrinkType.TEA, 2, true, 10.0));
        assertEquals(1, orderRepository.getAllOrders().size());
    }

    @Test
    public void shouldNotifyWhenNoMoreWaterForTea(){
        when(quantityChecker.isEmpty(any())).thenReturn(true);
        assertEquals("M:A shortage water is detected", transformOrder(DrinkType.TEA, 2, true, 10));
        verify(notifier).notifyMissingDrink("water");
    }

    @Test
    public void shouldNotifyWhenNoMoreMilkForCoffee(){
        when(quantityChecker.isEmpty(any())).thenReturn(true);
        assertEquals("M:A shortage milk is detected", transformOrder(DrinkType.COFFEE, 2, true, 10));
        verify(notifier).notifyMissingDrink(any());
    }

    private String transformOrder(DrinkType tea, int i, boolean b, double v) {
        return drinkMaker.generateOrder(makeOrder(tea, i, b, v));
    }

    private Order makeOrder(DrinkType tea, int i, boolean b, double v) {
        return new Order(tea, i, b, v);
    }
}