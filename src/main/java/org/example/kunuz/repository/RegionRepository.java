package org.example.kunuz.repository;

import org.example.kunuz.entity.ArticleTypeEntity;
import org.example.kunuz.entity.RegionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer>, PagingAndSortingRepository<RegionEntity, Integer> {

}
