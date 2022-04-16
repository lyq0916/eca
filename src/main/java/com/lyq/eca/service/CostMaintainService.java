package com.lyq.eca.service;

import com.lyq.eca.dao.CostMaintainDao;
import com.lyq.eca.pojo.CostMaintain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CostMaintainService{
    @Autowired
    CostMaintainDao costMaintainDao;

    public void add(CostMaintain costMaintain){costMaintainDao.save(costMaintain);};

    //public List<CostMaintain> findByPidAndUpdate_date(int pid, Date date){return costMaintainDao.findByPidAndUpdate_date(pid, date);};

    public List<CostMaintain> findAll(){return costMaintainDao.findAll();};

    public List<CostMaintain> findByPid(int pid){return costMaintainDao.findByPid(pid);};

    public List<CostMaintain> findByUpdatedate(Date update_date){return costMaintainDao.findByUpdatedate(update_date);};

    public List<CostMaintain> findByPidAndUpdatedate(int pid,Date update_date){return costMaintainDao.findByPidAndUpdatedate(pid,update_date);};

    public List<CostMaintain> findByCurdate(){return costMaintainDao.findByCurdate();};
}
