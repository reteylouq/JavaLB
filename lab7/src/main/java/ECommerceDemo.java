import java.util.*;
import java.util.stream.Collectors;

class Product implements Comparable<Product> {
    private Integer id;
    private String name;
    private double price;
    private int stock;

    public Product(Integer id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // getters and setters

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    // Implement Comparable for sorting by stock
    public static Comparator<Product> StockComparator = Comparator.comparing(Product::getStock);

    // Implement Comparable for sorting by name
    public static Comparator<Product> NameComparator = Comparator.comparing(Product::getName);

    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }
}

class User {
    private Integer id;
    private String username;
    private Map<Product, Integer> cart;

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
        this.cart = new HashMap<>();
    }

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void addToCart(Product product, int quantity) {
        cart.put(product, cart.getOrDefault(product, 0) + quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        int currentQuantity = cart.getOrDefault(product, 0);
        if (currentQuantity > quantity) {
            cart.put(product, currentQuantity - quantity);
        } else {
            cart.remove(product);
        }
    }

    public void modifyCart(Product product, int newQuantity) {
        cart.put(product, newQuantity);
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }
}

class Order {
    private Integer id;
    private Integer userId;
    private Map<Product, Integer> orderDetails;
    private double totalPrice;

    public Order(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
        this.orderDetails = new HashMap<>();
    }

    // getters and setters

    public void setOrderDetails(Map<Product, Integer> orderDetails) {
        this.orderDetails = new HashMap<>(orderDetails);
    }

    public Integer getId() {
        return id;
    }

    public void calculateTotalPrice() {
        totalPrice = orderDetails.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}

class ECommercePlatform {
    private Map<Integer, User> users;
    private Map<Integer, Product> products;
    private Map<Integer, Order> orders;

    public ECommercePlatform() {
        this.users = new HashMap<>();
        this.products = new HashMap<>();
        this.orders = new HashMap<>();
    }

    // methods for adding users, products, creating orders, listing items, etc.

    public void updateProductStock(Product product, int newStock) {
        product.setStock(newStock);
    }

    public List<Product> getSortedProductsByName() {
        List<Product> productList = new ArrayList<>(products.values());
        productList.sort(Product.NameComparator);
        return productList;
    }

    public List<Product> getSortedProductsByPrice() {
        List<Product> productList = new ArrayList<>(products.values());
        productList.sort(Comparator.comparing(Product::getPrice));
        return productList;
    }

    public List<Product> getSortedProductsByStock() {
        List<Product> productList = new ArrayList<>(products.values());
        productList.sort(Product.StockComparator);
        return productList;
    }

    public List<Product> filterProductsByStock(int minimumStock) {
        return products.values().stream()
                .filter(product -> product.getStock() >= minimumStock)
                .collect(Collectors.toList());
    }

    public List<Product> recommendProducts(User user) {
        // Implement recommendation logic based on user's cart or order history
        // ...

        return Collections.emptyList(); // Placeholder, replace with actual recommendations
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Order createOrder(User user) {
        Order order = new Order(orders.size() + 1, user.getId());
        order.setOrderDetails(user.getCart());
        order.calculateTotalPrice();
        orders.put(order.getId(), order);
        user.getCart().clear();
        return order;
    }

    public void processOrder(Order order) {
        // Implement order processing logic
        // ...
    }

    public String listUsers() {
        return users.values().toString();
    }

    public String listProducts() {
        return products.values().toString();
    }

    public String listOrders() {
        return orders.values().toString();
    }
}

public class ECommerceDemo {
    public static void main(String[] args) {
        // Initialize ECommercePlatform, add users, and products
        ECommercePlatform platform = new ECommercePlatform();
        User user1 = new User(1, "User1");
        User user2 = new User(2, "User2");
        Product product1 = new Product(1, "Product1", 10.99, 50);
        Product product2 = new Product(2, "Product2", 5.99, 30);

        platform.addUser(user1);
        platform.addUser(user2);
        platform.addProduct(product1);
        platform.addProduct(product2);

        // Simulate user interactions, cart updates, order processing, and recommendations
        user1.addToCart(product1, 3);
        user1.addToCart(product2, 2);
        platform.updateProductStock(product1, 47);
        platform.updateProductStock(product2, 28);
        Order order = platform.createOrder(user1);
        platform.processOrder(order);
        List<Product> recommendedProducts = platform.recommendProducts(user1);

        // Display final state of users, products, and orders
        System.out.println("Users: " + platform.listUsers());
        System.out.println("Products: " + platform.listProducts());
        System.out.println("Orders: " + platform.listOrders());
        System.out.println("Recommended Products: " + recommendedProducts);
    }
}
