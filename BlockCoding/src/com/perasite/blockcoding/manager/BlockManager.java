package com.perasite.blockcoding.manager;

import java.util.HashMap;

import com.avaje.ebeaninternal.server.lib.util.InvalidDataException;
import com.google.common.collect.Maps;
import com.perasite.blockcoding.block.ABlock;
import com.perasite.blockcoding.block.PrintBlock;

public class BlockManager {

	private static BlockManager instance;
	private HashMap<String, ABlock> blocks = Maps.newHashMap();

	private BlockManager() {
		blocks.put(PrintBlock.class.getSimpleName(), new PrintBlock());
	}

	public void registerBlock(ABlock block) {
		if (!blocks.containsKey(block.getName())) {
			blocks.put(block.getName(), block);
		} else {
			throw new InvalidDataException(block.getClass().getSimpleName() + " is already loaded");
		}
	}

	public static synchronized BlockManager getInstance() {
		if (instance == null)
			instance = new BlockManager();
		return instance;
	}

	public HashMap<String, ABlock> getBlocks() {
		return new HashMap<String, ABlock>(blocks);
	}
}
