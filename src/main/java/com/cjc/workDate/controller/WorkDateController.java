package com.cjc.workDate.controller;

import com.cjc.workdataassistanceautoconfigurer.strategy.WorkTypeStrategy;
import com.cjc.workdataassistanceautoconfigurer.strategy.factory.WorkTypeStrategyFactory;
import com.cjc.workdataassistanceautoconfigurer.workDate.WorkDate;
import com.cjc.workdataassistanceautoconfigurer.workDate.WorkDateAssistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * json，ajax交互的一个很好的额学习经历
 */
@RestController
public class WorkDateController {

    @Autowired
    WorkDateAssistance workDateAssistance;

    @GetMapping("/hello")
    public @ResponseBody String hello(){
        return  "hello";
    }


    @GetMapping("/getWorkTypeList/{id}")
    public List<String> get(@PathVariable("id") int id) throws Exception {
        WorkTypeStrategy strategy = WorkTypeStrategyFactory.createStrategy(id);
        workDateAssistance.setWorkTypeStrategy(strategy);
        System.out.println("1111");
        List<String> workTypeCircleList = strategy.getWorkTypeCircleList();
        System.out.println(workTypeCircleList);
        return workTypeCircleList;
    }

    @GetMapping("/getWorkDate/{workType}")
    public  WorkDate getWorkDate(@PathVariable("workType") int workType){
        workDateAssistance.init(0,0,workType);
        WorkDate workDate = workDateAssistance.getWorkDate();
        System.out.println(workDate);
        return workDate;
    }

    @PostMapping("/getNextMonth")
    public WorkDate getNextMonth(@RequestBody WorkDate workDateReturn){
        workDateAssistance.init(1,workDateReturn.getNextMonth(),workDateReturn.getPreFirstDayWorkType());
        WorkDate workDate = workDateAssistance.getWorkDate();
        System.out.println(workDate);
        return workDate;
    }

}
