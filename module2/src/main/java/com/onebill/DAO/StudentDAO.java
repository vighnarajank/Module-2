package com.onebill.DAO;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.onebill.bean.Student;
import com.onebill.exceptions.StudentNotFound;

@Repository
public class StudentDAO implements DAO{

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("student");
	Scanner sc = new Scanner(System.in);

	@Override
	public Student getDetail(int id) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Student studentDetail = entityManager.find(Student.class, id);

		entityManager.close();

		if(studentDetail != null)
			return studentDetail;
		else
			throw new StudentNotFound("No Student detail Found for this ID");
	}

	@Override
	public List<Student> getDetails() {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("from Student");
		List<Student> studentDetails = query.getResultList();

		entityManager.close();

		if(studentDetails != null)
			return studentDetails;
		else
			throw new StudentNotFound("No Student details found");
	}

	@Override
	public boolean addDetail(Student student) {

		System.out.println("Enter mark 1 : ");
		int m1 = sc.nextInt();
		System.out.println("Enter mark 2 : ");
		int m2 = sc.nextInt();
		System.out.println("Enter mark 3 : ");
		int m3 = sc.nextInt();

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			int result = (m1+m2+m3)/30;
			student.setMarks(result);

			/*
			 * This block of code calculates the grade and puts it to the database by
			 * calculating the marks
			 */
			try {
				if(result >= 9.1 && result <= 10) {
					student.setGrade("A+");
				}
				else if(result >= 8.1 && result <= 9.0) {
					student.setGrade("A");
				}
				else if(result >= 7.1 && result <= 8.0) {
					student.setGrade("B");
				}
				else if(result >= 6.1 && result <= 7.0) {
					student.setGrade("C");
				}
				else if(result >= 5.1 && result <= 6.0) {
					student.setGrade("D");
				}
				else if(result >= 4.1 && result <= 5.0) {
					student.setGrade("E");
				}
				else if(result < 4.0) {
					student.setGrade("F");
				}
			}catch (Exception e) {
				System.out.println("Invalid Marks");
			}
			entityManager.persist(student);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateDetail(Student student) {
		System.out.println("Enter mark 1 : ");
		int m1 = sc.nextInt();
		System.out.println("Enter mark 2 : ");
		int m2 = sc.nextInt();
		System.out.println("Enter mark 3 : ");
		int m3 = sc.nextInt();

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			int result = (m1+m2+m3)/30;
			Student studentdb = entityManager.find(Student.class, student.getUserid());
			studentdb.setMarks(result);
			try {
				if(result >= 9.1 && result <= 10) {
					studentdb.setGrade("A+");
				}
				else if(result >= 8.1 && result <= 9.0) {
					studentdb.setGrade("A");
				}
				else if(result >= 7.1 && result <= 8.0) {
					studentdb.setGrade("B");
				}
				else if(result >= 6.1 && result <= 7.0) {
					studentdb.setGrade("C");
				}
				else if(result >= 5.1 && result <= 6.0) {
					studentdb.setGrade("D");
				}
				else if(result >= 4.1 && result <= 5.0) {
					studentdb.setGrade("E");
				}
				else if(result < 4.0) {
					studentdb.setGrade("F");
				}
				else {
					throw new Exception();
				}
			}catch (Exception e) {
				System.out.println("Invalid Marks");
			}
			studentdb.setEmail(student.getEmail());
			studentdb.setName(student.getName());
			
			entityManager.persist(studentdb);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		}

	}

	@Override
	public boolean deleteDetail(int id) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			Student stud = entityManager.find(Student.class, id);
			entityManager.remove(stud);
			transaction.commit();
			entityManager.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		}
	}
	
}
