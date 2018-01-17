package com.taylor.reputation.seckill.exception;


import com.taylor.reputation.seckill.result.Result;

public class GlobalException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private Result.CodeMsg cm;
	
	public GlobalException(Result.CodeMsg cm) {
		super(cm.toString());
		this.cm = cm;
	}

	public Result.CodeMsg getCm() {
		return cm;
	}

}
