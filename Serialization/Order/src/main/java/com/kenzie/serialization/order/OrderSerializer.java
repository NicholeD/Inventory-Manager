package com.kenzie.serialization.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles the serialization and deserialization of Order object.
 */
public class OrderSerializer {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Deserializes the json string to an Order object.
     * @param json string representing an Order object
     * @return an Order object instantiated by the json provided
     * @throws OrderSerializationException if the provided JSON cannot be deserialized to an Order object
     */
    public static Order toOrder(String json) {

        try {
            Order deserializedOrder = MAPPER.readValue(json, Order.class);
            return deserializedOrder;
        } catch (JsonProcessingException e) {
            throw new OrderSerializationException("Order failed", e);
        }
    }

    /**
     * Serializes the provided Order as a string in JSON.
     * @param order the order to serialize
     * @return the order in JSON format
     * @throws OrderSerializationException if the order cannot be written to JSON
     */
    public static String toJSON(Order order) {
        try {
            String serializedCharacter = MAPPER.writeValueAsString(order);
            return serializedCharacter;
        } catch (Exception e) {
            throw new OrderSerializationException("Order failed.", e);
        }
    }
}
