package org.example.kunuz.repository;

import org.example.kunuz.entity.ProfileEntity;
import org.example.kunuz.entity.RegionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer>, PagingAndSortingRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByEmail(String email);
    Optional<ProfileEntity> findByEmailAndPassword(String email, String password);
    Optional<ProfileEntity> getByIdAndVisible(Integer id, Boolean visible);

    Page<ProfileEntity> findAllByVisible(Pageable pageable, Boolean visible );

    @Transactional
    @Modifying
    @Query("update ProfileEntity set visible=false where id=:id")
    public Integer delete(@Param("id") Integer id);

}
