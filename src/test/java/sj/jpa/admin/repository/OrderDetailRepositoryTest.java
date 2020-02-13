package sj.jpa.admin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sj.jpa.admin.model.entity.OrderDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = OrderDetail.builder()
                .status("WAITING")
                .arrivalDate(LocalDateTime.now().plusDays(2))
                .quantity(1)
                .totalPrice(BigDecimal.valueOf(900000))
                //.orderGroupId(1L) // 어떠한 장바구니에
                //.itemId(406L)     // 어떠한 상품
                .createdAt(LocalDateTime.now())
                .createdBy("AdminServer")
                .build();

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        assertThat(newOrderDetail).isNotNull();
    }

}