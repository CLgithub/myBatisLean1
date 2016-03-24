package com.cl.mybatis.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 工具类
 * @author AdminTC
 */
public class MybatisUtil {
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	private static SqlSessionFactory sqlSessionFactory;
	/**
	 * 加载位于src/mybatis.xml配置文件
	 * 创建sqlSessionFactory
	 */
	static{
		try {
			Reader reader = Resources.getResourceAsReader("mybatis.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 禁止外界通过new方法创建 
	 */
	private MybatisUtil(){}
	
	/**
	 * 获取SqlSession
	 */
	public static SqlSession getSqlSession(){
		SqlSession sqlSession = threadLocal.get();//从当前线程中获取SqlSession对象
		if(sqlSession == null){//如果SqlSession对象为空
			sqlSession = sqlSessionFactory.openSession();//在SqlSessionFactory非空的情况下，获取SqlSession对象
			threadLocal.set(sqlSession);//将SqlSession对象与当前线程绑定在一起
		}
		return sqlSession;//返回SqlSession对象
	}
	
//	public static SqlSession getSqlSession1(){
//		SqlSession sqlSession = null;
//		if(sqlSession==null){
//			sqlSession=sqlSessionFactory.openSession();
//		}
//		return sqlSession;
//	}
	
	/**
	 * 关闭SqlSession与当前线程分开
	 */
	public static void closeSqlSession(){
		SqlSession sqlSession = threadLocal.get();//从当前线程中获取SqlSession对象
		if(sqlSession != null){//如果SqlSession对象非空
			sqlSession.close();//关闭SqlSession对象
			threadLocal.remove();//分开当前线程与SqlSession对象的关系，目的是让GC尽早回收
		}
	}
	
//	public static void closeSqlSession(){
//		SqlSession sqlSession =null;//从当前线程中获取SqlSession对象
//		if(sqlSession != null){//如果SqlSession对象非空
//			sqlSession.close();//关闭SqlSession对象
////			threadLocal.remove();//分开当前线程与SqlSession对象的关系，目的是让GC尽早回收
//		}
//	}
	
	
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		Connection conn = MybatisUtil.getSqlSession().getConnection();
		System.out.println(conn!=null?"连接成功":"连接失败");
		MybatisUtil.closeSqlSession();
	}
}



