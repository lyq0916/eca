package com.lyq.eca.service;

import com.lyq.eca.dao.ProjectDao;
import com.lyq.eca.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectDao projectDao;

    public boolean findByPname(String pname){return projectDao.findByPname(pname);};

    public Project getByPname(String pname){return projectDao.getByPname(pname);};

    public int getPidByPname(String pname){Project p=projectDao.getByPname(pname);return p.getPid();};

    public void add(Project project) {projectDao.save(project);};

    public List<Project> findall() {return projectDao.findAll();};

    public List<Project> findByPnameContaining(String pname){return projectDao.findByPnameContaining(pname);};

    public List<Project> findByPnameLikeOrAddressLike(String keyword){System.out.println(keyword+"1");System.out.println(projectDao.findAllByPnameLikeOrAddressLike('%'+keyword+'%','%'+keyword+'%'));System.out.println(keyword+"2"); return projectDao.findAllByPnameLikeOrAddressLike('%'+keyword+'%','%'+keyword+'%');};

    public List<Project> search(String pname){return projectDao.search(pname);};

    public List<Object[]> groupByType(){return projectDao.groupByType();};
}
