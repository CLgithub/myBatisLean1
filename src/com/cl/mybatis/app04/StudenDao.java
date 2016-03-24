package com.cl.mybatis.app04;

import java.util.List;

import org.apache.ibatis.jdbc.SQL;
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
	
	/**
	 * 新增学生2
	 * @author L
	 * @date 2016年3月24日
	 * @param student
	 */
	private void add2(Student student) {
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.getSqlSession();
			//事物开始（默认事物开始）事物在配置文件里交给jdbc管理
			//读取映射文件StudentMapper.xml中的sql语句
			int i=sqlSession.insert(Student.class.getName()+".add2",student);
			//事物提交
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();//事物回滚
			e.printStackTrace();
		}finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	/**
	 * 根据id查找Student
	 * @author L
	 * @date 2016年3月24日
	 */
	private Student findStudentByKye(int id){
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.getSqlSession();
			return sqlSession.selectOne(Student.class.getName()+".findStudentByKye", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisUtil.closeSqlSession();
		}
		return null;
	}
	
	/**
	 * 查询所有
	 * @author L
	 * @date 2016年3月24日
	 * @return
	 */
	private List<Student> findAll(){
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.getSqlSession();
			return sqlSession.selectList(Student.class.getName()+".findAll");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisUtil.closeSqlSession();
		}
		return null;
	}
	
	/**
	 * 更新学生
	 * @author L
	 * @date 2016年3月24日
	 * @param student
	 */
	private void updateStudent(Student student) {
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.getSqlSession();
			sqlSession.update(Student.class.getName()+".updateStudent", student);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	/**
	 * 根据id删除学生
	 * @author L
	 * @date 2016年3月24日
	 * @param id
	 */
	private void deleteStudentByID(Student student) {
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.getSqlSession();
			sqlSession.delete(Student.class.getName()+".deleteStudentByID", student);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	public static void main(String[] args) throws Exception {
		StudenDao studenDao=new StudenDao();
//		studenDao.add1();
//		studenDao.add2(new Student(6, "小黑", 7000D));
		
//		Student student=studenDao.findStudentByKye(2);
//		System.out.println(student);
		
//		List<Student> list=studenDao.findAll();
//		for(Student student:list){
//			System.out.println(student);
//		}
		
//		Student student=studenDao.findStudentByKye(1);
//		student.setName("小明");
//		student.setSal(9000D);
//		studenDao.updateStudent(student);
//		System.out.println(studenDao.findStudentByKye(1));
		
		studenDao.deleteStudentByID(studenDao.findStudentByKye(2));
	}

	

	

	




	
}
