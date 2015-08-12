package com.bogle.frame.config.plugin;

import com.bogle.frame.config.component.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * 分页拦截器
 * 请在配置文件中注册该拦截器，可以设置拦截器属性
 * Created by lenovo on 2015/5/26.
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class}))
public class PageInterInterceptor implements Interceptor {

    private String id = ".+Page$";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 配置文件中SQL语句的ID
        String id = mappedStatement.getId();
        if (id.matches(this.id)) {
            BoundSql boundSql = statementHandler.getBoundSql();
            //原始的sql语句
            String sql = boundSql.getSql();

            //获取总页数
            String countSql = "select count(*) from (" + sql + ") a";
            Connection connection = (Connection)invocation.getArgs()[0];//获取拦截处的方法参数
            PreparedStatement countStatement = connection.prepareStatement(countSql);//获取准备的sqlstatement
            ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countStatement);
            ResultSet resultSet = countStatement.executeQuery();


            //分页ssql处理
            //方法的参数，
//            Map<String, Object> parameter = (Map<String, Object>) boundSql.getParameterObject();
//            Page page = (Page) parameter.get("page");
            Page page = (Page)boundSql.getParameterObject();
            if(resultSet.next()) {
                page.setTotal(resultSet.getLong(1));
            }

            //改造后带分页的查询sql语句,从page中获取limi的参数 sql = sql + limit + startIndex,size;
            String pageSql = sql + " limit " + (page.getPage() - 1) * page.getSize() + " , " + page.getSize();
            //修改原来的seql为带分页查询的sql语句
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 在注册拦截器时注册属性，可以通过该方法获取
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        String id = properties.getProperty("id");
        if(id != null && !"".equals(id.trim())) {
            this.id = id;
        }

    }
}
