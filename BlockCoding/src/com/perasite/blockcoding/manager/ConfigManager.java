package com.perasite.blockcoding.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class ConfigManager extends YamlConfiguration {

	private File file;
	private boolean isExist = false;

	public ConfigManager(String path) {
		this(new File(path));
	}

	public ConfigManager(File path) {
		try {
			isExist = createFile(path);
			load(path);
			this.file = path;
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void addDefault(String path, Object value) {
		if (!isExist) {
			set(path, value);
			save();
		}
	}
	

	public void save() {
		try {
			Validate.notNull(file, "File cannot be null");
			Files.createParentDirs(file);
			String data = this.saveToString();
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);

			try {
				writer.write(data);
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String saveToString() {
		String data = new String();
		boolean first = true;
		for (String s : super.saveToString().split("\\\\u")) {
			if (s.length() >= 4 && !first) {
				data += (char) Integer.parseInt(s.substring(0, 4), 16);
				if (s.length() >= 5) {
					data += s.substring(4);
				}
			} else {
				data += s;
				first = false;
			}
		}
		return data;
	}

	private boolean createFile(File path) throws IOException {
		if (!path.exists()) {
			path.getParentFile().mkdirs();
			path.createNewFile();
			return false;
		}
		return true;
	}

}
