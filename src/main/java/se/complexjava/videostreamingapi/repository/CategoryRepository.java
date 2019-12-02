package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.complexjava.videostreamingapi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
