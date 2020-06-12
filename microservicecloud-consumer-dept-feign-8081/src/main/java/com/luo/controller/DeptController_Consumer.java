package com.luo.controller;

import com.luo.entities.Dept;
import com.luo.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController_Consumer {

    @Autowired
    private DeptClientService service;

    @RequestMapping(value = "/consumer/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return service.add(dept);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return service.get(id);
    }

    @RequestMapping(value="/consumer/dept/list")
    public List<Dept> list(){
        return service.list();
    }

//    @RequestMapping(value = "consumer/dept/discovery")
//    public Object discovery(){
//        return service.;
//    }
}
