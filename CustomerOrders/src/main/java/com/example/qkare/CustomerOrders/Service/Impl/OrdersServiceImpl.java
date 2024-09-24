package com.example.qkare.CustomerOrders.Service.Impl;

import com.example.qkare.CustomerOrders.Model.Entity.Customer;
import com.example.qkare.CustomerOrders.Model.Entity.Orders;
import com.example.qkare.CustomerOrders.Model.Entity.Product;
import com.example.qkare.CustomerOrders.Repository.OrdersRepository;
import com.example.qkare.CustomerOrders.Repository.ProductRepository;
import com.example.qkare.CustomerOrders.Service.CustomerService;
import com.example.qkare.CustomerOrders.Service.OrdersService;
import com.example.qkare.CustomerOrders.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Override
   public Orders createOrder(Orders order) {
        Customer customer = customerService.findCustomerById(order.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        Orders createdOrder = new Orders();
        createdOrder.setOrderNo(createRandomNumber());
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setCargoName(order.getCargoName());
        createdOrder.setCustomer(customer);

        Set<OrderItem> orderItems = new HashSet<>();

        for (OrderItem orderItem : order.getOrderItems()) {
            // Veritabanından sipariş edilen ürünü bul
            Product productFromDb = productService.findById(orderItem.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found!"));

            // Stok kontrolü: Sipariş edilen miktar kadar stokta var mı?
            if (productFromDb.getStock() < orderItem.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + productFromDb.getTitle());
            }

            // Stok azaltma: Sipariş edilen miktar kadar ürünü stoktan düş
            productFromDb.decreaseStock(orderItem.getQuantity());

            // OrderItem oluştur ve gerekli bilgileri ekle
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setOrder(createdOrder); // Sipariş ile ilişkilendir
            newOrderItem.setProduct(productFromDb); // Ürün ile ilişkilendir
            newOrderItem.setQuantity(orderItem.getQuantity()); // Sipariş edilen miktarı belirle

            // OrderItem'ı sipariş listesine ekle
            orderItems.add(newOrderItem);

            // Ürünü güncelle ve kaydet
            productService.save(productFromDb);
        }

        createdOrder.setOrderItems(orderItems);
        return ordersRepository.save(createdOrder);
    }

    @Override
    public Integer createRandomNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return number;
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders deliverOrder(Integer orderNo) {
        Orders existingOrder = ordersRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        existingOrder.setDeliveryDate(LocalDateTime.now());
        return ordersRepository.save(existingOrder);
    }

    @Override
    public Orders cancelOrder(Integer orderNo) {
        Orders existingOrder = ordersRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        if (!existingOrder.isCanceled() && existingOrder.getDeliveryDate() == null) {
            existingOrder.setCanceled(true); // Sipariş iptal ediliyor

            // Sipariş öğelerindeki ürünlerin stoğunu artır
            for (OrderItem orderItem : existingOrder.getOrderItems()) {
                Product product = orderItem.getProduct();
                int quantity = orderItem.getQuantity(); // Sipariş edilen miktar
                System.out.println("Get Quantity: " + quantity);
                // Stoğu miktar kadar artır
                product.increaseStock(quantity);
                productService.save(product); // Ürünü kaydet
            }

            // İptal edilen siparişi kaydet
            return ordersRepository.save(existingOrder);
        }

        return null;
    }

    @Override
    public List<Orders> getOrdersByCustomerId(Long customerId) {
        Customer existingCustomer = customerService.findCustomerById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
        return ordersRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Orders> getOrdersByProductId(Long productId) {
        Product existingProduct = productService.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        return ordersRepository.findByproductsId(productId);
    }
}
