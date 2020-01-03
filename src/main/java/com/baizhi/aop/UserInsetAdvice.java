package com.baizhi.aop;

import com.baizhi.service.EchartsService;
import io.goeasy.GoEasy;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class UserInsetAdvice {

    @Autowired
    private EchartsService echartsService;

    @Pointcut(value = "execution(void com.baizhi.service.UserService.add*(..))")
    public void expression() {
    }


    @After("expression()")
    public void after() {
        List<Integer> integers = echartsService.queryMonthByRegister();
        GoEasy goEasy = new GoEasy("", "");
        goEasy.publish("month", integers.toString());
    }

}
