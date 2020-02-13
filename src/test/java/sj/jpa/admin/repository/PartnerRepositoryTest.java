package sj.jpa.admin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sj.jpa.admin.model.entity.Partner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PartnerRepositoryTest {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create() {
        String name = "Partner01";
        String status = "REGISTERED";
        String address = "서울시 강남구";
        String callCenter = "070-1111-2222";
        String partnerNumber = "010-1111-2222";
        String businessNumber = "1234567890123";
        String ceoName = "홍길동";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
        Long categoryId = 1L;

        Partner partner = Partner.builder()
                .name(name)
                .status(status)
                .address(address)
                .callCenter(callCenter)
                .partnerNumber(partnerNumber)
                .businessNumber(businessNumber)
                .ceoName(ceoName)
                .registeredAt(registeredAt)
                .createdAt(createdAt)
                .createdBy(createdBy)
                //.categoryId(categoryId)
                .build();

        Partner newPartner = partnerRepository.save(partner);

        assertThat(newPartner).isNotNull();

        assertThat(newPartner.getName()).isEqualTo(name);

    }

}
