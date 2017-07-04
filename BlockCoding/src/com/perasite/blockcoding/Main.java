package com.perasite.blockcoding;

import org.bukkit.plugin.java.JavaPlugin;

import com.perasite.blockcoding.block.ABlock;
import com.perasite.blockcoding.manager.BlockManager;
import com.perasite.blockcoding.util.Argument;

public class Main extends JavaPlugin {
	public void onEnable() {
	}

	public void onDisable() {
	}

	public static void main(String[] args) {
		long nano = System.nanoTime();
		for (int i = 0; i < 100000; i++) {
			ABlock printblock = BlockManager.getInstance().getBlocks().get("PrintBlock").loadParams(
					new Argument("msg", "메세지"),
					new Argument("player", "PeraSite"));
		}
		System.out.println((System.nanoTime() - nano) + "(" + (System.nanoTime() - nano) / 1000000 + "ms)");
	}
}