package com.cl.mybatis.app04;

/**
 * create table students(
   id  int(5) primary key,
   name varchar(10),
   sal double(8,2)
);

 * @author L
 * @date 2016年3月24日
 */
public class Student {
	private Integer id;// 编号
	private String name;// 姓名
	private Double sal;// 薪水

	public Student() {
	}

	public Student(int id, String name, Double sal) {
		this.id=id;
		this.name=name;
		this.sal=sal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSal() {
		return sal;
	}

	public void setSal(Double sal) {
		this.sal = sal;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sal=" + sal + "]";
	}
	
}
