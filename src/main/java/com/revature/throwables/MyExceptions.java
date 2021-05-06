package com.revature.throwables;

public class MyExceptions extends Exception{
	
	public MyExceptions(String errorMessage, Throwable err) {
		super(errorMessage,err);
	}
	
	public MyExceptions(String message) {
		super(message);
	}

}
