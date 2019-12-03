package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
