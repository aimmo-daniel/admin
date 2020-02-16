package sj.jpa.admin.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sj.jpa.admin.model.enumclass.OrderType;
import sj.jpa.admin.model.enumclass.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupApiResponse {

    private Long id;

    private String status;

    private OrderType orderType;

    private String revAddress;

    private String revName;

    private PaymentType paymentType;

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private Long userId;
}
