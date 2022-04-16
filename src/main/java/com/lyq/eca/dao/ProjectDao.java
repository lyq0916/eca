package com.lyq.eca.dao;

import com.lyq.eca.pojo.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectDao extends JpaRepository<Project,Integer>{
    Boolean findByPname(String pname);
    Project getByPname(String pname);
    Project getByAddress(String Address);
    Project getByType(String type);
    List<Project> findAll();
    /*弃--报错*/
    List<Project> findByPnameContaining(String pname);
    List<Project> findAllByPnameLikeOrAddressLike(String pname,String address);
    @Query("select p from Project p where p.pname like %:name%")
    List<Project> search(@Param("name") String name);

    /*生成饼状图*/
    @Query(value = "select p.type,count(*) from Project p group by p.type",nativeQuery = true)
    List<Object[]> groupByType();
}
