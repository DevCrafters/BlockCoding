package com.perasite.blockcoding.block;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 2017-07-01.
 */
public class TalkBlock extends ABlock {

	private static final long serialVersionUID = 1L;

//	public TalkBlock(HashMap<String, String> args) {
//		super(args);
//	}
//
//	public TalkBlock() {
//		super();
//	}
//	
//	public TalkBlock(Argument... args) {
//		super();
//	}

	@Override
	public String getName() {
		return "TalkBlock";
	}

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public List<String> getFieldList() {
		return Arrays.asList("sender", "receiver", "msg");
	}

}
