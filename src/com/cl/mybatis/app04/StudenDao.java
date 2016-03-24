package com.cl.mybatis.app04;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	 * 根据id查找Student2
	 * @author L
	 * @date 2016年3月24日
	 */
	private Student findStudentByKye2(int id){
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.getSqlSession();
			return sqlSession.selectOne(Student.class.getName()+".findStudentByKye2", id);
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
	
	/**
	 * 带分页查询记录
	 * @author L
	 * @param name 
	 * @date 2016年3月24日
	 * @param start 开始查询记录
	 * @param pageSize 每页查询条数
	 */
	private List<Student> findByNameWithPage(String name, int start,int pageSize) {
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.getSqlSession();
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("pName", "%"+name+"%");
			map.put("pStart", start);
			map.put("pPageSize", pageSize);
			return sqlSession.selectList(Student.class.getName()+".findByNameWithPage",map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisUtil.closeSqlSession();
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		StudenDao studenDao=new StudenDao();
//		studenDao.add1();
//		for(int i=1;i<=10;i++){
//			studenDao.add2(new Student(i, "姓名"+i, 7000D));
//		}
		
		
//		Student student=studenDao.findStudentByKye2(5);
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
		
//		studenDao.deleteStudentByID(studenDao.findStudentByKye(2));
		
		System.out.println("第一页");
		for(Student student: studenDao.findByNameWithPage("姓",0,3)){
			System.out.println(student);
		}
		System.out.println("第二页");
		for(Student student: studenDao.findByNameWithPage("姓",3,3)){
			System.out.println(student);
		}
		System.out.println("第三页");
		for(Student student: studenDao.findByNameWithPage("姓",6,3)){
			System.out.println(student);
		}
		System.out.println("第四页");
		for(Student student: studenDao.findByNameWithPage("姓",9,3)){
			System.out.println(student);
		}
		System.out.println("第五页");
		for(Student student: studenDao.findByNameWithPage("姓",12,3)){
			System.out.println(student);
		}
	}

	

	

	

	




	
}
