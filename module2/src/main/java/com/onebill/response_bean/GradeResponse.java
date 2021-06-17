package com.onebill.response_bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.onebill.bean.Student;

import lombok.Data;


/*
 * Response Class for displaying Grade
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"statusCode" ,"message" ,"description"})
public class GradeResponse {

	private int statusCode;

	private String message;

	private String description;

	private Student studentDetail;

	private int id;

	private String name;

	private String grade;

}
