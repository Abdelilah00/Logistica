////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.alexy.repositorys;

import com.alexy.models.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public interface IBaseJpaRepository<TEntity extends BaseEntity> extends JpaRepository<TEntity, Serializable> {


    /*@Override
    @Query("select e from #{#entityName} e where e.deletedAt is null")
    List<TEntity> findAll();*/

    //Look up deleted entities
    @Query("select e from #{#entityName} e where e.deletedAt is not null")
    List<TEntity> recycleBin();


    @Override
    @Query("update #{#entityName} e set e.deletedAt=current_timestamp where e.id=?1")
    @Modifying
    void deleteById(Serializable serializable);


    //Soft delete.
    @Modifying
    @Query("update #{#entityName} e set e.deletedAt=null where e.id=?1")
    void deleteById(long id);
}

