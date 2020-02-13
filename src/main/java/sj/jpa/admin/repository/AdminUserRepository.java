package sj.jpa.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.jpa.admin.model.entity.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
}
