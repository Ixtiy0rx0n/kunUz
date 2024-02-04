package org.example.kunuz.repository;

import org.example.kunuz.entity.ArticleEntity;
import org.example.kunuz.entity.AttachEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface AttachRepository extends CrudRepository<AttachEntity, String> {
}
