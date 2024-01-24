package org.example.kunuz.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.kunuz.entity.ArticleTypeEntity;
import org.example.kunuz.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer>, PagingAndSortingRepository<CategoryEntity, Integer> {

}
