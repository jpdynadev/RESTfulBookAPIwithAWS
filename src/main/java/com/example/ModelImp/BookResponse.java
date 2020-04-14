package com.example.ModelImp;

public class BookResponse {
	private String success;
	private Object o;
	private String comment;
	public BookResponse(String success, Object o, String comment) {
		super();
		this.success = success;
		this.o = o;
		this.comment = comment;
	}
	public BookResponse(String success, String comment) {
		super();
		this.success = success;
		this.o = null;
		this.comment = comment;
	}
	public BookResponse(String success) {
		super();
		this.success = success;
		this.o = null;
		this.comment = "";
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public Object getObject() {
		return o;
	}
	public void setObject(Object o) {
		this.o = o;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
