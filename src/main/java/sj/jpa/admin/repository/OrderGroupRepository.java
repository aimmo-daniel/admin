package sj.jpa.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.jpa.admin.model.entity.OrderGroup;

public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
}
