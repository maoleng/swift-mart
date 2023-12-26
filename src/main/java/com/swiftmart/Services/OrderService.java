package com.swiftmart.Services;

import com.swiftmart.Enums.OrderStatus;
import com.swiftmart.Models.Location;
import com.swiftmart.Models.Order;
import com.swiftmart.Models.Samples.OrderInfo;
import com.swiftmart.Models.User;
import com.swiftmart.Repositories.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderService extends BaseService
{

    public OrderService(Repository repository, MongoTemplate mongoTemplate)
    {
        super(repository, mongoTemplate);
    }

    public List<Order> getOrdersByUserId(String userId)
    {
        return repository.getOrderRepository().getOrdersByUserId(userId);
    }

    public Map<String, Object> getOrdersWithInfo(String date)
    {
        List<Date> dates = getDatesBetween(date == null ? "all" : date);
        Date startDate = dates.get(0);
        Date endDate = dates.get(1);

        List<Order> orders;
        if (startDate == null) {
            orders = repository.getOrderRepository().findAll();

            return addOrderInfo(orders);
        }

        orders = repository.getOrderRepository().getOrdersByCreatedAtBetweenOrderByCreatedAtDesc(startDate, endDate);

        return addOrderInfo(orders);
    }

    private List<Date> getDatesBetween(String date)
    {
        Date currentDate = new Date();
        Date startDate;
        Date endDate;
        if (date.equals("yesterday")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.DATE, -1);
            startDate = cal.getTime();
            endDate = currentDate;
        } else if (date.equals("today")) {
            startDate = currentDate;
            endDate = currentDate;
        } else if (date.equals("week")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.DATE, -6);
            startDate = cal.getTime();
            endDate = currentDate;
        } else if (date.equals("month")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.MONTH, -1);
            startDate = cal.getTime();
            endDate = currentDate;
        } else if (date.contains("to")) {
            String[] dateParts = date.split(" to ");
            startDate = parseDate(dateParts[0]);
            endDate = parseDate(dateParts[1]);
        } else {
            startDate = null;
            endDate = null;
        }

        List<Date> dates = new ArrayList<>();
        dates.add(startDate);
        dates.add(endDate);

        return dates;
    }

    private Map<String, Object> addOrderInfo(List<Order> orders)
    {
        Double revenue = 0.0;
        for (Order order : orders) {
            revenue += order.getTotal();
            order.setUser(repository.getUserRepository().findBy_id(order.getUserId()));
            order.setSale(repository.getUserRepository().findBy_id(order.getSaleId()));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("orders", orders);
        result.put("orderCount", orders.size());
        long successOrderCount = orders.stream().filter(order -> OrderStatus.SUCCESS.name().equals(order.getStatus())).count();
        result.put("successOrderCount", Math.toIntExact(successOrderCount));
        result.put("revenue", revenue);

        return result;
    }

    private Date parseDate(String dateString)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OrderInfo getOrderInfo(String orderId)
    {
        return new OrderInfo(
            repository.getOrderRepository().findBy_id(orderId),
            repository.getOrderProductRepository().getOrderProductsByOrderId(orderId)
        );
    }

    public Order create(User sale, User user, Double total)
    {
        Order order = new Order();
        order.setUserId(user.get_id());
        order.setSaleId(sale.get_id());
        order.setLocationId(sale.getLocationId());
        order.setTotal(total);
        order.setPaymentMethod("BANK");
        order.setReceivedMoney(0.0);
        order.setGaveBackMoney(0.0);
        order.setCreatedAt(new Date());

        repository.getOrderRepository().save(order);

        return order;
    }

    public void updateTimeout(Order order)
    {
        order.setStatus(OrderStatus.FAILED.name());
        repository.getOrderRepository().save(order);
    }

    public void updatePaymentBankSuccess(Order order)
    {
        order.setStatus(OrderStatus.SUCCESS.name());
        order.setReceivedMoney(order.getTotal());
        order.setGaveBackMoney(order.getTotal());
        repository.getOrderRepository().save(order);
    }

}