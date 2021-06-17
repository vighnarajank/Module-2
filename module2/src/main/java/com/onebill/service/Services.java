package com.onebill.service;

import java.util.List;

import com.onebill.bean.Student;

/*
 * Services provided by the DAO
 */
public interface Services {

	public Student getDetail(int id);

	public List<Student> getDetails();

	public boolean addDetail(Student student);

	public boolean updateDetail(Student student);

	public boolean deleteDetail(int id);

}
