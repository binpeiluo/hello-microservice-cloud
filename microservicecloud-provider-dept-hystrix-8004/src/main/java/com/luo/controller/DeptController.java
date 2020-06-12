package com.luo.controller;

import com.luo.entities.Dept;
import com.luo.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService service;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value="/dept/add",method=RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return service.add(dept);

    }

    @RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = service.get(id);
        if(dept==null){
            throw new RuntimeException("该ID："+id+"没有没有对应的信息");
        }
        return service.get(id);
    }

    @RequestMapping(value="/dept/list",method=RequestMethod.GET)
    public List<Dept> list(){
        return service.list();
    }

    @RequestMapping(value = "dept/discovery",method = RequestMethod.GET)
    public Object discovery(){
        //client.getServices()的意思是获取eureka里面有多少个微服务
        //那么我们如果只有一个微服务是部门，后续我们在工作中可能会有越来越多的微服务
        //那么List查出来的就会有很多的微服务，然后进行遍历然后打印出来
        List<String> list = client.getServices();
        System.out.println("**********" + list);
        //client.getInstances()的意思就是在那么多的微服务里面，你指定只找哪一个微服务
        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance element : srvList) {
            //然后打印你指定要找的微服务的ID和主机和端口以及访问地址等信息
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id) {
        return new Dept().setDeptno(id).setDname("该ID："+id+"没有没有对应的信息,null--@HystrixCommand").setDb_source("no this database in MySQL");
    }
}
