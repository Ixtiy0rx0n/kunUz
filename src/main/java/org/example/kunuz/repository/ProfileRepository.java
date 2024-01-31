package org.example.kunuz.repository;

import org.example.kunuz.entity.ProfileEntity;
import org.example.kunuz.entity.RegionEntity;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.enums.ProfileStatus;
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

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.name=:name,p.surname=:surname,p.role=:role,p.status=:status where p.id=:id")
    Integer update(String name, String surname, ProfileRole role, ProfileStatus status, Integer id);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set visible=false where id=:id")
    Integer deleteByIdProfile(Integer id);
    Page<ProfileEntity> findAllByVisible(Pageable pageable, Boolean visible );

    @Transactional
    @Modifying
    @Query("Update ProfileEntity  set status =?2 where id = ?1")
    void updateStatus(Integer id, ProfileStatus active);

}


