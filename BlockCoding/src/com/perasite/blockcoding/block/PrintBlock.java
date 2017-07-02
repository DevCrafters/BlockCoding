package com.perasite.blockcoding.block;

import java.util.Arrays;
import java.util.List;

import com.perasite.blockcoding.Argument;

/**
 * Created by user on 2017-07-01.
 */
public class PrintBlock extends ABlock {

	private static final long serialVersionUID = 1L;

	public PrintBlock(Argument... args) {
		super(args);
	}

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
	
	@Override
	public List<String> getDescription() {
		return Arrays.asList("메세지를 플레이어에게 전송합니다.");
	}
}
