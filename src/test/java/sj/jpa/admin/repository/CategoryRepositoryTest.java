package sj.jpa.admin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sj.jpa.admin.model.entity.Category;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = Category.builder()
                .type(type)
                .title(title)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .build();

        Category newCategory = categoryRepository.save(category);

        assertThat(newCategory).isNotNull();
        assertThat(newCategory.getType()).isEqualTo(type);
        assertThat(newCategory.getTitle()).isEqualTo(title);
    }

    @Test
    public void read() {
        String type = "COMPUTER";
        Optional<Category> optionalCategory = categoryRepository.findByType(type);

        optionalCategory.ifPresent(c -> {
            assertThat(c.getType()).isEqualTo(type);
            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());
        });

    }


}
