package sj.jpa.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.jpa.admin.model.entity.Category;
import sj.jpa.admin.model.entity.Partner;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    List<Partner> findByCategory(Category category);
}
