package com.lyq.eca.dao;

import com.lyq.eca.pojo.CostMaintain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CostMaintainDao extends JpaRepository<CostMaintain,Integer>{
    List<CostMaintain> findByPid(int pid);
    List<CostMaintain> findByUpdatedate(Date updatedate);
    List<CostMaintain> findByPidAndUpdatedate(int pid,Date updatedate);

    @Query(value = "select * from cost_maintain where date(update_date)=curdate()",nativeQuery = true)
    List<CostMaintain> findByCurdate();
}
