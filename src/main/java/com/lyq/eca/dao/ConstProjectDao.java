package com.lyq.eca.dao;

import com.lyq.eca.pojo.ConstProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ConstProjectDao extends JpaRepository<ConstProject, Integer> {
    List<ConstProject> findAll();
   /* @Transactional
    @Modifying
    @Query(value = "insert into const_project values(?1,'准备中',?2,null,?3,null,null)",nativeQuery = true)
    void Insert(int pid,)*/
    @Transactional
    @Modifying
    @Query(value = "update const_project set start_date=?1 , state='开工中' where pid=?2",nativeQuery = true)
    void start(Date startDate,int pid);

    @Transactional
    @Modifying
    @Query(value = "update const_project set end_date=?1 , state='已完工' where pid=?2",nativeQuery = true)
    void end(Date endDate,int pid);
}
