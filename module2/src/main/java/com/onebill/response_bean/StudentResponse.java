package com.onebill.response_bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.onebill.bean.Student;

import lombok.Data;

/*
 * Response Class for displaying Student Details
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"statusCode" ,"message" ,"description"})
public class StudentResponse {

	private int statusCode;

	private String message;

	private String description;

	private Student studentDetail;

	private List<Student> studentsDetail;

}
