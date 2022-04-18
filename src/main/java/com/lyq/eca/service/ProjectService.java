package com.lyq.eca.service;

import com.lyq.eca.dao.ProjectDao;
import com.lyq.eca.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProjectService {
    @Autowired
    ProjectDao projectDao;

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
        projectDao.save(project);
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
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null, city = null, county = null;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> row = null;
        while (m.find()) {
            row = new LinkedHashMap<String, String>();
            province = m.group("province");
            row.put("province", province == null ? "" : province.trim());

            city = m.group("city");
            String removeStr="市";
            String str=(city == null ? "" : ("市辖区".equals(city)?province.trim():city.trim())).replace(removeStr,"");
            row.put("city",str);

            county = m.group("county");
            row.put("county", county == null ? "" : county.trim());
            list.add(row);
        }
        return list;
    }

    public Map<String,Integer> getAddress(){
        List<Project> projectList=projectDao.findAll();
        Project p;
        //使用可以改变大小的数组ArrayList
        Map<String,Integer> map=new HashMap<String,Integer>();
        for(int i=0;i<projectList.size();i++){
            p=projectList.get(i);
            String address=p.getAddress();
            List<Map<String,String>> list=addressResolution(address);
            String city=list.get(0).get("city");
            if(map.containsKey(city)){
                map.put(city,map.get(city)+1);
            }else{
                map.put(city,1);
            }
        }
        return map;
    }
}
