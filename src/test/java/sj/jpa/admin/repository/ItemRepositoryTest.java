package sj.jpa.admin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sj.jpa.admin.model.entity.Item;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = Item.builder()
                .status("UNREGISTERED")
                .name("삼성 노트북")
                .title("삼성 노트북 A100")
                .content("2019년형 노트북 입니다.")
                .price(9000000)
                .brandName("삼성")
                .registeredAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .createdBy("Partner01")
                //.partnerId(1L)
                .build();

        Item newItem = itemRepository.save(item);

        assertThat(newItem).isNotNull();
    }

    @Test
    public void read() {
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        assertThat(item.isPresent()).isTrue();
    }



}