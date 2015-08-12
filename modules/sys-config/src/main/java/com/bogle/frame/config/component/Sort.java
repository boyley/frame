package com.bogle.frame.config.component;

import java.io.Serializable;
import java.util.*;

/**
 * Created by lenovo on 2015/5/26.
 */
public class Sort implements Serializable, Iterable<Sort.Order> {

    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private List<Order> orders;

    public Sort(Order... orders) {
        this(Arrays.asList(orders));
    }

    public Sort(String... properties) {
        this(DEFAULT_DIRECTION, properties);
    }


    public Sort(Direction direction, String... properties) {
        this(direction, properties == null ? new ArrayList<String>() : Arrays.asList(properties));
    }

    public Sort(Direction direction, List<String> properties) {

        if (properties == null || properties.isEmpty()) {
            throw new IllegalArgumentException("You have to provide at least one property to sort by!");
        }

        this.orders = new ArrayList<Order>(properties.size());

        for (String property : properties) {
            this.orders.add(new Order(direction, property));
        }
    }

    public Sort(List<Order> orders) {

        if (null == orders || orders.isEmpty()) {
            throw new IllegalArgumentException("You have to provide at least one sort property to sort by!");
        }

        this.orders = orders;
    }

    public Order getOrderFor(String property) {

        for (Order order : this) {
            if (order.getProperty().equals(property)) {
                return order;
            }
        }

        return null;
    }

    @Override
    public Iterator<Order> iterator() {
        return this.orders.iterator();
    }

    public static class Order implements Serializable {
        private final Direction direction;
        private final String property;

        public Order(Direction direction, String property) {

            if (property == null || "".equals(property.trim())) {
                throw new IllegalArgumentException("Property must not null or empty!");
            }

            this.direction = direction == null ? DEFAULT_DIRECTION : direction;
            this.property = property;
        }

        public Direction getDirection() {
            return direction;
        }

        /**
         * Returns the property to order for.
         *
         * @return
         */
        public String getProperty() {
            return property;
        }
    }

    public static enum Direction {
        ASC, DESC;


        public static Direction fromString(String value) {

            try {
                return Direction.valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format(
                        "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
            }
        }
    }

}
