package com.lyq.eca.dao;

import com.lyq.eca.pojo.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ProjectDao extends JpaRepository<Project,Integer>{
    Boolean findByPname(String pname);
    Project getByPname(String pname);
    Project findByPid(int pid);
    Project getByAddress(String Address);
    Project getByType(String type);
    List<Project> findAll();
    /*弃--报错*/
    //List<Project> findByPnameContaining(String pname);
    //List<Project> findAllByPnameLikeOrAddressLike(String pname,String address);
    @Query("select p from Project p where p.pname like %:name%")
    List<Project> search(@Param("name") String name);

    /*生成饼状图*/
    @Query(value = "select p.type,count(*) from Project p group by p.type",nativeQuery = true)
    List<Object[]> groupByType();

    @Query(value = "SELECT LAST_INSERT_ID()",nativeQuery = true)
    int getlast();

    @Transactional
    @Modifying
    @Query(value = "update project set state=?1 where pid=?2",nativeQuery = true)
    void updateState(String state,int pid);

    @Query(value = "select state,count(*) from project group by state",nativeQuery = true)
    List<Object[]> groupByState();
}
