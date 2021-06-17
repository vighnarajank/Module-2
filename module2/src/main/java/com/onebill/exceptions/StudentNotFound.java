package com.onebill.exceptions;

public class StudentNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentNotFound(String error) {
		super(error);
	}
}
