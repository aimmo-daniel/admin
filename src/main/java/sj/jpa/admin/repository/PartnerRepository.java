package sj.jpa.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.jpa.admin.model.entity.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
