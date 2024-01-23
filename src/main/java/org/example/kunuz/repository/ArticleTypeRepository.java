package org.example.kunuz.repository;

import org.example.kunuz.entity.ArticleTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity, Integer> {

}
