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
		for (int c = 0; c < 5; c++) {
			long nano = System.nanoTime();
			for (int i = 0; i < 1000000; i++) {
				ABlock printblock = BlockManager.getInstance().getBlocks().get("PrintBlock").loadParams(
						new Argument("msg", "메세지"),
						new Argument("player", "PeraSite"));

				ABlock printblock2 = BlockManager.getInstance().getBlocks().get("PrintBlock").loadParams(
						new Argument("msg", "메세지"),
						new Argument("player", "PeraSite"));
			}
			long subbed = System.nanoTime() - nano;
			System.out.println(subbed + "(" + subbed / 1000000 + "ms)");
		}
	}
}