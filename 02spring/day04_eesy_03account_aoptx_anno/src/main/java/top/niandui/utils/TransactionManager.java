package top.niandui.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，他包含了，开启事务、提交事务、回滚事务和释放连接
 *
 * 因为使用注解配置springAOP时，执行循序是，前置》最终》后置/异常，不能控制事务，因此使用环绕通知
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* top.niandui.service.impl.*.*(..))")
    public void pt() {}
    /**
     * 开启事务
     */
//    @Before("pt()")
    public void beginTransaction() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
//    @AfterReturning("pt()")
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
//    @AfterThrowing("pt()")
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
//    @After("pt()")
    public void release() {
        try {
            connectionUtils.getThreadConnection().close(); // 还回连接池中
            connectionUtils.removeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Around("pt()")  // 环绕通知
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        try {
            Object rtValue = null;
            Object[] args = pjp.getArgs();
            beginTransaction();
            rtValue = pjp.proceed(args);
            commit();
            return rtValue;
        } catch (Throwable throwable) {
            rollback();
            throw new RuntimeException(throwable);
        } finally {
            release();
        }
    }

}
