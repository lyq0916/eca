package com.lyq.eca.service;

import com.lyq.eca.dao.BidProjectDao;
import com.lyq.eca.dao.ConstProjectDao;
import com.lyq.eca.dao.ProjectDao;
import com.lyq.eca.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProjectService {
    @Autowired
    ProjectDao projectDao;
    @Autowired
    BidProjectDao bidProjectDao;
    @Autowired
    ConstProjectDao constProjectDao;

    public Project findByPid(int pid){return projectDao.findByPid(pid);};

    public boolean findByPname(String pname) {
        return projectDao.findByPname(pname);
    }

    public Project getByPname(String pname) {
        return projectDao.getByPname(pname);
    }

    public int getPidByPname(String pname) {
        Project p = projectDao.getByPname(pname);
        return p.getPid();
    }

    public void add(Project project) {
        project.setState("申报中");
        projectDao.save(project);
        int pid = projectDao.getlast();
        BidProject bp = new BidProject(pid, "已报备");
        bidProjectDao.save(bp);
    }

    public List<Project> findall() {
        return projectDao.findAll();
    }

    public List<Project> search(String pname) {
        return projectDao.search(pname);
    }


    public List<Object[]> groupByType() {
        return projectDao.groupByType();
    }

    //分割省市区
    public List<Map<String, String>> addressResolution(String address) {
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null, city = null, county = null;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> row = null;
        while (m.find()) {
            row = new LinkedHashMap<String, String>();
            province = m.group("province");
            row.put("province", province == null ? "" : province.trim());

            city = m.group("city");
            String removeStr = "市";
            String str = (city == null ? "" : ("市辖区".equals(city) ? province.trim() : city.trim())).replace(removeStr, "");
            row.put("city", str);

            county = m.group("county");
            row.put("county", county == null ? "" : county.trim());
            list.add(row);
        }
        return list;
    }

    public Map<String, Integer> getAddress() {
        List<Project> projectList = projectDao.findAll();
        Project p;
        //使用可以改变大小的数组ArrayList
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < projectList.size(); i++) {
            p = projectList.get(i);
            String address = p.getAddress();
            List<Map<String, String>> list = addressResolution(address);
            String city = list.get(0).get("city");
            if (map.containsKey(city)) {
                map.put(city, map.get(city) + 1);
            } else {
                map.put(city, 1);
            }
        }
        return map;
    }

    public List<Object[]> groupByState(){
        return projectDao.groupByState();
    }

    public List<BidBean> getBidBeans() {
        List<BidProject> bidProjects = bidProjectDao.findAll();
        List<BidBean> bidBeans = new ArrayList<>();
        for (int i = 0; i < bidProjects.size(); i++) {
            BidBean bidBean = new BidBean();
            bidBean.setPid(bidProjects.get(i).getPid());
            Project project=projectDao.findByPid(bidProjects.get(i).getPid());
            bidBean.setPname(project.getPname());
            bidBean.setProject(project);
            bidBean.setState(bidProjects.get(i).getState());
            bidBeans.add(bidBean);
        }
        return bidBeans;
    }

    public List<ConstBean> getConstBeans(){
        List<ConstProject> constProjects=constProjectDao.findAll();
        List<ConstBean> constBeans=new ArrayList<>();
        for(int i=0;i<constProjects.size();i++){
            ConstBean constBean=new ConstBean();
            constBean.setPid(constProjects.get(i).getPid());
            Project project=projectDao.findByPid(constProjects.get(i).getPid());
            constBean.setPname(project.getPname());
            constBean.setProject(project);
            constBean.setState(constProjects.get(i).getState());
            constBean.setEstart(constProjects.get(i).getEstart());
            constBean.setStart(constProjects.get(i).getStart());
            constBean.setEend(constProjects.get(i).getEend());
            constBean.setEnd(constProjects.get(i).getEnd());
            constBean.setBudget(project.getBudget());
            constBean.setCost(constProjects.get(i).getCost()==null?0:constProjects.get(i).getCost());
            constBeans.add(constBean);
        }
        return constBeans;
    }

    public void updateBidProject(String state,int pid){
        bidProjectDao.updateStateByPid(state,pid);
        System.out.println(state);
        if("建设中".equals(state)){
            System.out.println(state);
            projectDao.updateState(state,pid);
        }
    }

    public void addConstProject(int pid,Date start,Date end){
        ConstProject constProject=new ConstProject();
        constProject.setPid(pid);
        constProject.setState("准备中");
        constProject.setEstart(start);
        constProject.setEend(end);
        constProjectDao.save(constProject);
    }

    public void startConstProject(Date start,int pid){
        constProjectDao.start(start,pid);
    }
    public void endConstProject(Date end,int pid){
        constProjectDao.end(end,pid);
        try{
            projectDao.updateState("已完工",pid);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
