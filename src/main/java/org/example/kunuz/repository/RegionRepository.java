package org.example.kunuz.repository;

import org.example.kunuz.entity.RegionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer>, PagingAndSortingRepository<RegionEntity, Integer> {


        Optional<RegionEntity> getByIdAndVisible(Integer id, Boolean visible);
        Page<RegionEntity> findAllByVisible(Pageable pageable, Boolean visible );

        @Transactional
        @Modifying
        @Query("update RegionEntity set visible=false where id=:id")
        public Integer delete(@Param("id") Integer id);

}
