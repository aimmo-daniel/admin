package sj.jpa.admin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sj.jpa.admin.model.entity.AdminUser;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AdminUserRepositoryTest {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create() {
        AdminUser adminUser = AdminUser.builder()
                .account("AdminUser01")
                .password("AdminUser01")
                .status("REGISTERED")
                .role("PARTNER")
                //.createdAt(LocalDateTime.now())
                //.createdBy("AdminServer")
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        assertThat(newAdminUser).isNotNull();

        newAdminUser.setAccount("CHANGE");
        adminUserRepository.save(newAdminUser);
    }

}
