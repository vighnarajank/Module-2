package com.onebill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.DAO.StudentDAO;
import com.onebill.bean.Student;

/*
 * Implementation of the Services Provided by DAO, 
 * that can be accessed by the Controller Class
 */
@Service
public class StudentService implements Services{

	@Autowired
	StudentDAO studentDAO;	//Injecting StudentDAO's object


	@Override
	public Student getDetail(int id) {
		return studentDAO.getDetail(id);
	}

	@Override
	public List<Student> getDetails() {
		return studentDAO.getDetails();
	}

	@Override
	public boolean addDetail(Student student) {
		return studentDAO.addDetail(student);
	}

	@Override
	public boolean updateDetail(Student student) {
		return studentDAO.updateDetail(student);
	}

	@Override
	public boolean deleteDetail(int id) {
		return studentDAO.deleteDetail(id);
	}	

}
