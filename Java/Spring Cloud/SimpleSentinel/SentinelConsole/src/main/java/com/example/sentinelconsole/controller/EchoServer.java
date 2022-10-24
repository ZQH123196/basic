package com.example.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("EchoServer")
public class EchoServer {

    String helloResource = "hello";
    String echoResource = "echo";


    @PostConstruct
    public void setUpSentinel() {

        FlowRule ruleHello = new FlowRule();
        ruleHello.setResource(helloResource);
        // 设置限流规则类型 QPS
        ruleHello.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置 QPS 的阈值，即每秒可通过的请求个数
        ruleHello.setCount(1);

        FlowRule ruleEcho = new FlowRule();
        ruleEcho.setResource(echoResource);
        // 设置限流规则类型 QPS
        ruleEcho.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置 QPS 的阈值，即每秒可通过的请求个数
        ruleEcho.setCount(1);


        List<FlowRule> flowRules = new ArrayList<>();
        flowRules.add(ruleHello);
        flowRules.add(ruleEcho);
        FlowRuleManager.loadRules(flowRules);

    }

    @RequestMapping("hello")
    public String hello() {
        System.out.println("into hello!");
        try (Entry entry = SphU.entry(helloResource)) {
            Thread.sleep(3*1000);
            return "hello world!";
        } catch (BlockException e) { // 此处做降级处理
            e.printStackTrace();
            System.out.println("系统繁忙，请稍后再试!");
            return "系统繁忙，请稍后再试!";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("echo")
    public String echo(@RequestParam String str) {
        try (Entry entry = SphU.entry(helloResource)) {
            Thread.sleep(3*1000);
            return str;
        } catch (BlockException e) { // 此处做降级处理
            e.printStackTrace();
            return "系统繁忙，请稍后再试!";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
