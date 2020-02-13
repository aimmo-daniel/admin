package sj.jpa.admin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sj.jpa.admin.model.entity.OrderGroup;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderGroupRepositoryTest {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create() {
        OrderGroup orderGroup = OrderGroup.builder()
                .status("COMPLETE")
                .orderType("ALL")
                .revAddress("서울시 강남구")
                .revName("홍길동")
                .paymentType("CARD")
                .totalPrice(BigDecimal.valueOf(900000))
                .totalQuantity(1)
                .orderAt(LocalDateTime.now().minusDays(2))
                .arrivalDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .createdBy("AdminServer")
                .build();

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        assertThat(newOrderGroup).isNotNull();
    }

}
