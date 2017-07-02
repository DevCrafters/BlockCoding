package com.perasite.blockcoding.manager;

import java.util.List;

import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;
import com.perasite.blockcoding.util.ItemBuilder;

public class ScriptManager {

}

class Script {

	private List<ScriptItem> items = Lists.newArrayList();
	private String name = "";

	public Script(String name) {
		this.name = name;
	}

}

class ScriptItem {

	private ItemStack item = null;

	public ScriptItem(ItemBuilder builder) {
		item = builder;
	}

}