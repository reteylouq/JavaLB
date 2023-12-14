import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ECommerceSystemTest {

    @Test
    void testAddAndRemoveProductFromCart() {
        Product product1 = new Product(1, "Laptop", 999.99);
        Product product2 = new Product(2, "Smartphone", 499.99);

        Cart cart = new Cart();
        cart.addProduct(product1);

        assertTrue(cart.getProducts().contains(product1));
        assertFalse(cart.getProducts().contains(product2));

        cart.removeProduct(product1);

        assertFalse(cart.getProducts().contains(product1));
    }

    @Test
    void testPlaceOrder() {
        Product product1 = new Product(1, "Laptop", 999.99);
        Product product2 = new Product(2, "Smartphone", 499.99);

        Cart cart = new Cart();
        cart.addProduct(product1);
        cart.addProduct(product2);

        Order order = new Order(cart.getProducts());

        assertEquals("Pending", order.getStatus());

        order.setStatus("Shipped");

        assertEquals("Shipped", order.getStatus());
    }
}
