package com.perasite.blockcoding;

import org.bukkit.plugin.java.JavaPlugin;

import com.perasite.blockcoding.block.ABlock;
import com.perasite.blockcoding.block.TalkBlock;
import com.perasite.blockcoding.manager.BlockManager;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}

	public static void main(String[] args) {
		ABlock talkblock = new TalkBlock();
		BlockManager.getInstance().registerBlock(talkblock);
		System.out.println(talkblock.loadParams(
				new Argument("msg", "메세지"),
				new Argument("sender", "Notch"),
				new Argument("receiver", "PeraSite")).serialize());

		System.out.println("리스트: " +  BlockManager.getInstance().getBlocks().keySet());
	}
}