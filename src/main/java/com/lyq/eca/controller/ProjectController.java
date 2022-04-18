package com.lyq.eca.controller;

import com.lyq.eca.pojo.CostMaintain;
import com.lyq.eca.pojo.Project;
import com.lyq.eca.pojo.Result;
import com.lyq.eca.service.CostMaintainService;
import com.lyq.eca.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {
    /*
     * 每个service类都要加上@Autowired ！！！
     * */
    @Autowired
    ProjectService projectService;
    @Autowired
    CostMaintainService costMaintainService;

    @CrossOrigin
    @PostMapping(value = "api/projectadd")
    @ResponseBody
    public Result projectAdd(@RequestBody Project project) {
        try {
            System.out.println(project);
            projectService.add(project);
            return new Result(200);
        } catch (Exception e) {
            return new Result(300);
        }
    }

    //request错误分析
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String messageNotReadable(HttpMessageNotReadableException exception, HttpServletResponse response) {
        System.out.println(exception);
        return "";
    }

    @CrossOrigin
    @GetMapping(value = "api/projectshow")
    @ResponseBody
    public List<Project> projectshow() {
        try {
            List<Project> p = new ArrayList<>();
            p = projectService.findall();
            //System.out.println(p.get(0).toString());
            return p;
        } catch (Exception e) {
            return null;
        }
    }

    @CrossOrigin
    @GetMapping(value = "api/searchproject")
    @ResponseBody
    public List<Project> searchproject(@RequestParam("pname") String pname) {
        if ("".equals(pname)) {
            return projectService.findall();
        } else {
            //return projectService.findByPnameLikeOrAddressLike(pname);
            return projectService.search(pname);
        }
    }


    @CrossOrigin
    @PostMapping(value = "api/costadd")
    @ResponseBody
    public Result costadd(@RequestBody CostMaintain costMaintain) {
        try {
            System.out.println(costMaintain.toString());
            costMaintainService.add(costMaintain);
            return new Result(200);
        } catch (Exception e) {
            System.out.println(e);
            return new Result(400);
        }
    }

    @CrossOrigin
    @GetMapping(value = "api/costshow")
    @ResponseBody
    public List<CostMaintain> costshow() {
        try {
            List<CostMaintain> c = costMaintainService.findAll();
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    /*  @CrossOrigin
      @PostMapping(value = "api/costshowbypid")
      @ResponseBody
      public List<CostMaintain> costshowpid(@RequestBody CostMaintain costMaintain){
          //System.out.println(costMaintain.getPid());
          return costMaintainService.findByPid(costMaintain.getPid());
      }

      @CrossOrigin
      @PostMapping(value = "api/costshowbydate")
      @ResponseBody
      public List<CostMaintain> costshowbydate(@RequestBody CostMaintain costMaintain) throws ParseException {
          SimpleDateFormat trans=new SimpleDateFormat("yyyy-MM-dd");
          String s=trans.format(costMaintain.getUpdatedate());
          System.out.println(s);
          Date date=trans.parse(s);
          //System.out.println(date);
          return costMaintainService.findByUpdatedate(date);
      }

      @CrossOrigin
      @PostMapping(value = "api/costshowbydp")
      @ResponseBody
      public List<CostMaintain> costshowbydp(@RequestBody CostMaintain costMaintain) throws ParseException {
          System.out.println(costMaintain.getPid());
          System.out.println(costMaintain.getUpdatedate());
          //int pid=projectService.getPidByPname(pname);

          //写的有点麻烦。。。日期转日期 格式变化
          SimpleDateFormat trans=new SimpleDateFormat("yyyy-MM-dd");
          String s=trans.format(costMaintain.getUpdatedate());
          System.out.println(s);
          Date date=trans.parse(s);
          System.out.println(date);
          return costMaintainService.findByPidAndUpdatedate(costMaintain.getPid(),date);
      }
  */
    @CrossOrigin
    @GetMapping(value = "api/searchcost")
    @ResponseBody
    public List<CostMaintain> searchcost(@RequestParam("pname") String pname, @RequestParam("date") String date) throws ParseException {
        System.out.println(pname);
        List<CostMaintain> c = new ArrayList<>();
        if ("".equals(pname) && (date.equals("null") || "".equals(date))) {
            return costMaintainService.findAll();
        } else if (!("".equals(pname)) && (date.equals("null") || "".equals(date))) {
            List<Project> p = projectService.search(pname);
            int pid;
            for (int i = 0; i < p.size(); i++) {
                pid = p.get(i).getPid();
                c.addAll(costMaintainService.findByPid(pid));
            }
            return c;
        } else if ("".equals(pname) && !(date.equals("null") || "".equals(date))) {
            SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = trans.parse(date);
            return costMaintainService.findByUpdatedate(date1);
        } else {
            /*日期格式转换*/
            SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = trans.parse(date);
            List<Project> p = projectService.search(pname);
            int pid;
            for (int i = 0; i < p.size(); i++) {
                pid = p.get(i).getPid();
                c.addAll(costMaintainService.findByPidAndUpdatedate(pid, date1));
            }
            //System.out.println(c);
            return c;
        }
    }

    @CrossOrigin
    @GetMapping(value = "api/groupbytype")
    @ResponseBody
    public List<Object[]> groupbytype() {
        return projectService.groupByType();
    }

    @CrossOrigin
    @GetMapping(value = "api/costtype")
    @ResponseBody
    public List<CostMaintain> costtype() {
        return costMaintainService.findByCurdate();
    }

    @CrossOrigin
    @GetMapping(value = "api/getaddress")
    @ResponseBody
    public Map<String, Integer> getaddress() {
        return projectService.getAddress();
    }
}
