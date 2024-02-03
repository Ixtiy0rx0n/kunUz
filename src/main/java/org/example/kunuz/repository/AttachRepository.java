package org.example.kunuz.repository;

import org.example.kunuz.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

public interface AttachRepository extends CrudRepository<ArticleEntity, String> {
}
