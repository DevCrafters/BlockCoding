package com.perasite.blockcoding.block;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.perasite.blockcoding.manager.BlockManager;
import com.perasite.blockcoding.util.Argument;

/**
 * Created by user on 2017-07-01.
 */
public abstract class ABlock implements Serializable {

	private static final long serialVersionUID = -4447363949488375899L;
	protected HashMap<String, String> args = Maps.newHashMap();

	public ABlock(HashMap<String, String> args) {
		vaildArgument(args);
		this.args = args;
	}

	public ABlock() {

	}

	public ABlock(Argument... args) {
		loadParams(args);
	}

	@Override
	public String toString() {
		return serialize();
	}

	public HashMap<String, String> getArgument() {
		return args;
	}

	public String serialize() {
		vaildArgument(args);
		StringJoiner collect = new StringJoiner("╉");
		for (Map.Entry<String, String> e : this.getArgument().entrySet()) {
			String s = e.getKey() + "=" + e.getValue();
			collect.add(s);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(this.getName() + "[");
		sb.append(collect.toString());
		sb.append("]");

		return sb.toString();
	}

	public static ABlock deserialize(String blockData) {
		String args = blockData.substring(blockData.indexOf("[") + 1, blockData.indexOf("]"));
		String[] maps = args.split("╉");
		Map<String, String> map = Maps.newHashMap();
		for (String entry : maps) {
			if (!entry.contains("="))
				continue;
			String[] splited = entry.split("=");
			String key = splited[0];
			String value = splited[1];
			map.put(key, value);
		}
		String classname = blockData.substring(0, blockData.indexOf("["));
		if (!BlockManager.getInstance().getBlocks().containsKey(classname)) {
			throw new IllegalArgumentException("Block " + classname + " doesn't exist.");
		}
		return BlockManager.getInstance().getBlocks().get(classname);
	}

	public ABlock loadParams(Argument... arguments) {
		if (vaildArgument(arguments)) {
			for (Argument arg : arguments) {
				args.put(arg.getName(), String.valueOf(arg.getValue()));
			}
		}
		return this;
	}

	private boolean vaildArgument(HashMap<String, String> args) {
		List<Argument> list = Lists.newArrayList();
		for (Entry<String, String> entry : args.entrySet()) {
			list.add(new Argument(entry.getKey(), entry.getValue()));
		}
		return vaildArgument(list.toArray(new Argument[0]));
	}

	private boolean vaildArgument(Argument... args) {
		if (args == null) {
			throw new IllegalArgumentException("Arguments can't be null.");
		}
		List<String> argsToNames = Lists.newArrayList();
		for (Argument arg : args) {
			argsToNames.add(arg.getName());
		}
		
//		if(argsToNames.containsAll(getFieldList())) {
//			return true;
//		} else {
//			throw new IllegalArgumentException("Wrong parameters: " + argsToNames.toString());
//		}

		if (argsToNames.size() < getFieldList().size()) {
			List<String> tmp = new ArrayList<String>(getFieldList());
			tmp.removeAll(argsToNames);
			throw new IllegalArgumentException("Not enough parameters. Need more parameter : " + tmp.toString());
		}
		if (argsToNames.size() - getFieldList().size() > 0) {
			argsToNames.removeAll(getFieldList());
			throw new IllegalArgumentException("Unacceptable parameters: " + argsToNames.toString());
		}
		return true;
	}

	public abstract String getName();

	public abstract boolean execute();

	public abstract List<String> getFieldList();

	public abstract List<String> getDescription();

}