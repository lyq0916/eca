package com.lyq.eca.service;

import com.lyq.eca.dao.CostMaintainDao;
import com.lyq.eca.dao.ProjectDao;
import com.lyq.eca.pojo.CostMaintain;
import com.lyq.eca.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CostMaintainService{
    @Autowired
    CostMaintainDao costMaintainDao;

    @Autowired
    ProjectDao projectDao;

    public void add(CostMaintain costMaintain){costMaintainDao.save(costMaintain);};

    //public List<CostMaintain> findByPidAndUpdate_date(int pid, Date date){return costMaintainDao.findByPidAndUpdate_date(pid, date);};

    public List<CostMaintain> findAll(){return costMaintainDao.findAll();};

    public List<CostMaintain> findByPid(int pid){return costMaintainDao.findByPid(pid);};

    public List<CostMaintain> findByUpdatedate(Date update_date){return costMaintainDao.findByUpdatedate(update_date);};

    public List<CostMaintain> findByPidAndUpdatedate(int pid,Date update_date){return costMaintainDao.findByPidAndUpdatedate(pid,update_date);};

    public List<Object[]> findByCurdate(){return costMaintainDao.findByCurdate();};

    public List<Object[]> costgroupByPid(){
        Project project=new Project();
        List<Object[]> result=costMaintainDao.costgroupByPid();
        for(int i=0;i<costMaintainDao.costgroupByPid().size();i++){
            project=projectDao.findByPid(Integer.parseInt(costMaintainDao.costgroupByPid().get(i)[0].toString()));
            result.get(i)[0]=project.getPname();
            result.get(i)[1]=costMaintainDao.costgroupByPid().get(i)[1];
        }
        return result;
    }

    public List<Object[]> groupByTypeFroPid(int pid){
        return costMaintainDao.groupByTypeFroPid(pid);
    }

}
