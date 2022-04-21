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

    @Query(value = "select type,sum(money) from cost_maintain where date(update_date)=curdate() group by type;",nativeQuery = true)
    List<Object[]> findByCurdate();

    @Query(value = "select pid,sum(money) from cost_maintain group by pid",nativeQuery = true)
    List<Object[]> costgroupByPid();

    @Query(value = "select type,sum(money) from cost_maintain where pid=?1 group by type;",nativeQuery = true)
    List<Object[]> groupByTypeFroPid(int pid);
}
