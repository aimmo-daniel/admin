package sj.jpa.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.jpa.admin.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
