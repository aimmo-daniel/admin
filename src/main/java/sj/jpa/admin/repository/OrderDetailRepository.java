package sj.jpa.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.jpa.admin.model.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
