package tech.elastic.enums;

import java.util.HashMap;
import java.util.Map;

public enum OrderState {
    ORDER_RECEIVED(1),

    IN_PROGRESS(2),

    ORDER_COMPLETED(200),

    ERROR(100),

    JOIN_SHIPMENT(50),

    SUSPENDED(99),

    ORDER_INITIALIZED (10),

    CREDIT_CHOICE(11),

    CREDIT_CHECKING(12),

    CREDIT_APPROVED(13),

    CREDIT_DENIED(14),

    PAYMENT_PROCESSING(21),

    PAYMENT_CONFIRMED(22),

    PAYMENT_DECLINED(23),

    PACKING_FORK(31),

    ORDER_PACKAGING_START(32),

    ORDER_PACKAGING_DONE(33),


    SEND_BILL_START(34),

    SEND_BILL_DONE(35),


    READY_TO_SHIP_JOIN(41),

    SHIPPED(42),

    IN_TRANSIT(43),

    REACHED_DESTINATION(44),

    CANCELLED(61),

    RETURNED(62),

    DELIVERED(63);

    private final int rank;

    OrderState(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    // Lookup table
    private static final Map<String, OrderState> lookup = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (OrderState os : OrderState.values()) {
            lookup.put(os.name().toLowerCase(), os);
        }
    }

    // This method can be used for reverse lookup purpose
    public static OrderState fromString(String state) {
        OrderState foundState = lookup.get(state.trim().toLowerCase());
        if (foundState == null) {
            throw new IllegalArgumentException("No OrderState with text " + state + " found");
        }
        return foundState;
    }
}
