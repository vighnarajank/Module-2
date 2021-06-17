package com.onebill.DAO;

import java.util.List;

import com.onebill.bean.Student;

public interface DAO {

	public Student getDetail(int id);
	
	public List<Student> getDetails();
	
	public boolean addDetail(Student student);
	
	public boolean updateDetail(Student student);
	
	public boolean deleteDetail(int id);

}
