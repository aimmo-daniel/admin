package sj.jpa.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.jpa.admin.model.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByType(String type);

}
