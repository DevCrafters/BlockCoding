package com.perasite.blockcoding.block;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 2017-07-01.
 */
public class PrintBlock extends ABlock {

	private static final long serialVersionUID = 1L;
//
//	public PrintBlock(HashMap<String, String> args) {
//		super(args);
//	}
//	
//	
//	
//	public PrintBlock(Argument... args) {
//		super(args);
//		System.out.println("Arg[]");
//	}

	@Override
	public String getName() {
		return "PrintBlock";
	}

	@Override
	public boolean execute() {
		
		return false;
	}

	@Override
	public List<String> getFieldList() {
		return Arrays.asList("msg", "player");
	}
}
