package com.cl.mybatis.app04;

import org.apache.ibatis.session.SqlSession;

import com.cl.mybatis.util.MybatisUtil;

/**
 * 持久层
 * @author L
 * @date 2016年3月24日
 */
public class StudenDao {

	/**
	 * 新增学生
	 * @author L
	 * @date 2016年3月24日
	 * @param student
	 * @throws Exception
	 */
	public void add1() throws Exception{
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.getSqlSession();
			//事物开始（默认事物开始）事物在配置文件里交给jdbc管理
			//读取映射文件StudentMapper.xml中的sql语句
			int i=sqlSession.insert("com.cl.mybatis.app04.Student.add1");
			//事物提交
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();//事物回滚
			e.printStackTrace();
		}finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	private void add2(Student student) {
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.getSqlSession();
			//事物开始（默认事物开始）事物在配置文件里交给jdbc管理
			//读取映射文件StudentMapper.xml中的sql语句
			int i=sqlSession.insert(student.getClass().getName()+".add2",student);
			//事物提交
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();//事物回滚
			e.printStackTrace();
		}finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		StudenDao studenDao=new StudenDao();
//		studenDao.add1();
		studenDao.add2(new Student(5, "小白", 7000D));
	}




	
}
