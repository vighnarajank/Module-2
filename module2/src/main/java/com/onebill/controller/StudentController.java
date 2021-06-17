package com.onebill.controller;

//Required Packages Imported
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onebill.bean.Student;
import com.onebill.response_bean.GradeResponse;
import com.onebill.response_bean.MarkResponse;
import com.onebill.response_bean.StudentResponse;
import com.onebill.service.StudentService;

/* Rest Controller 'StudentController' having 
 * all the controllers as  methods to respond to client's request.
 *  
 * Get Mapping : '/getStudent?id=_ID_Present_in_DB_to_be_given_', '/getStudents'
 * */
@RestController
public class StudentController {


	@Autowired
	StudentService studentService;	//Injecting studentService object

	/*
	 *  Controller to input data of single student to student table
	 * 
	 * Input :
	 * Type = Body(raw format) JSON/XML
	 * Data = Student data type with name, email, marks as attributes
	 */
	@PostMapping(path = "/addStudent", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public StudentResponse addStudentDetail(@RequestBody Student student) {

		StudentResponse studentResponse = new StudentResponse();

		if(studentService.addDetail(student)) {
			studentResponse.setStatusCode(200);
			studentResponse.setMessage("Insertion Successful");
			studentResponse.setDescription("Student " + student.getName() + "'s Data Inserted");
		} 
		else {
			studentResponse.setStatusCode(400);
			studentResponse.setMessage("Insertion UnSuccessful");
			studentResponse.setDescription("Could not Insert Data");
		}

		return studentResponse;
	}

	/* 
	 * Controller to Fetch data for single student from student table
	 * 
	 * Input :
	 * Type = Parameter
	 * Data = DB Table's primary key as 'id' and value
	 */
	@GetMapping(path = "/getStudent", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public StudentResponse getStudentDetail(Integer id){

		Student student = studentService.getDetail(id);
		StudentResponse studentResponse = new StudentResponse();

		if(student != null) {
			studentResponse.setStatusCode(200);
			studentResponse.setMessage("Fetch Successful");
			studentResponse.setDescription("Student-" + id + " Detail Found");
			studentResponse.setStudentDetail(student);
		} 
		else {
			studentResponse.setStatusCode(404);
			studentResponse.setMessage("Fetch UnSuccessful");
			studentResponse.setDescription("No Student detail found for ID-" + id);
		}

		return studentResponse;
	}

	/* 
	 * Controller to Fetch data of all students
	 * Input : NA 
	 */
	@GetMapping(path = "/getStudents", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public StudentResponse getStudentsDetail(){

		List<Student> students =  studentService.getDetails();
		StudentResponse studentResponse = new StudentResponse();

		if(students != null) {
			studentResponse.setStatusCode(200);
			studentResponse.setMessage("Fetch Successful");
			studentResponse.setDescription("Student's Details Found");
			studentResponse.setStudentsDetail(students);
		} 
		else {
			studentResponse.setStatusCode(404);
			studentResponse.setMessage("Fetch UnSuccessful");
			studentResponse.setDescription("No Student details found");
		}

		return studentResponse;
	}

	@PutMapping(path = "/updateStudent", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public StudentResponse updateStudent(@RequestBody Student student) {
		StudentResponse studentResponse = new StudentResponse();
		if (studentService.updateDetail(student)) {
			studentResponse.setStatusCode(200);
			studentResponse.setMessage("Success");
			studentResponse.setDescription("Data Updated");

		} else {
			studentResponse.setStatusCode(400);
			studentResponse.setMessage("Failure");
			studentResponse.setDescription("Could not Update the data ");
		}
		return studentResponse;
	}

	@DeleteMapping(path = "/deleteStudent", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public StudentResponse deleteStudent(int id) {
		StudentResponse studentResponse = new StudentResponse();
		Student student = studentService.getDetail(id);

		if (studentService.deleteDetail(id)) {
			studentResponse.setStatusCode(200);
			studentResponse.setMessage("Success");
			studentResponse.setDescription("Student-" + id + " Deleted");
			studentResponse.setStudentDetail(student);

		} else {
			studentResponse.setStatusCode(400);
			studentResponse.setMessage("Failure");
			studentResponse.setDescription("No student at ID-" + id);
		}
		return studentResponse;
	}

	@GetMapping(path = "/getMark",  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public MarkResponse getMark(Integer id) {

		Student student = studentService.getDetail(id);
		MarkResponse markResponse = new MarkResponse();

		if (student != null) {
			markResponse.setStatusCode(200);
			markResponse.setMessage("Success");
			markResponse.setDescription("Aggregate Marks of Student-" + id);
			markResponse.setId(id);
			markResponse.setName(student.getName());
			markResponse.setMarks(student.getMarks());

		} 
		else {
			markResponse.setStatusCode(400);
			markResponse.setMessage("Failure");
			markResponse.setDescription("No student at ID-" + id);
		}
		return markResponse;
	}

	@GetMapping(path = "/getGrade",  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public GradeResponse getGrade(Integer id) {

		Student student = studentService.getDetail(id);
		GradeResponse gradeResponse = new GradeResponse();

		if (student != null) {
			gradeResponse.setStatusCode(200);
			gradeResponse.setMessage("Success");
			gradeResponse.setDescription("Aggregate Marks of Student-" + id);
			gradeResponse.setId(id);
			gradeResponse.setName(student.getName());
			gradeResponse.setGrade(student.getGrade());

		} 
		else {
			gradeResponse.setStatusCode(400);
			gradeResponse.setMessage("Failure");
			gradeResponse.setDescription("No student at ID-" + id);
		}
		return gradeResponse;
	}
}
