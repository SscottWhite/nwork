package com.example.demo.otherstool.exception;


public class Authorize401Exception extends Exception{

    /**
	 *  * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	public Authorize401Exception(String msg, Throwable e){
        super(msg,e);
        e.printStackTrace();
    }

    public Authorize401Exception(Throwable e){
        super(e);
    }

    public Authorize401Exception(String msg){
        super(msg);
    }

}
