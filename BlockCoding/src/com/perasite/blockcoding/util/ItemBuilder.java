package com.perasite.blockcoding.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder extends ItemStack {

	public ItemBuilder(final Material mat) {
		super(mat);
	}

	public ItemBuilder(final ItemStack mat) {
		super(mat);
	}

	private String color(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

	public ItemBuilder amount(final int amount) {
		super.setAmount(amount);
		return this;
	}

	public ItemBuilder name(final String name) {
		final ItemMeta meta = super.getItemMeta();
		meta.setDisplayName(color("§f" + name));
		super.setItemMeta(meta);
		return this;
	}

	public ItemBuilder lore(final String name) {
		if(name == null || name.equals("")) {
			return this;
		}
		final ItemMeta meta = super.getItemMeta();
		List<String> lore = meta.getLore();
		if (lore == null) {
			lore = new ArrayList<String>();
		}
		lore.add(color("§f" + name));
		meta.setLore(lore);
		super.setItemMeta(meta);
		return this;
	}

	public ItemBuilder lore(final List<String> lore) {
		final ItemMeta meta = super.getItemMeta();
		meta.setLore(lore);
		super.setItemMeta(meta);
		return this;
	}

	public ItemBuilder durability(final int durability) {
		super.setDurability((short) durability);
		return this;
	}

	public ItemBuilder owner(String name) {
		try {
			SkullMeta meta = (SkullMeta) super.getItemMeta();
			meta.setOwner(name);
			super.setItemMeta(meta);
		} catch (Exception e) {
			return this;
		}
		return this;
	}

	public ItemBuilder enchantment(final Enchantment enchantment, final int level) {
		super.addUnsafeEnchantment(enchantment, level);
		return this;
	}

	public ItemBuilder enchantment(final Enchantment enchantment) {
		super.addUnsafeEnchantment(enchantment, 1);
		return this;
	}

	public ItemBuilder type(final Material material) {
		super.setType(material);
		return this;
	}

	public ItemBuilder clearLore() {
		final ItemMeta meta = super.getItemMeta();
		meta.setLore(new ArrayList<String>());
		super.setItemMeta(meta);
		return this;
	}

	public ItemBuilder clearEnchantments() {
		for (final Enchantment e : super.getEnchantments().keySet()) {
			super.removeEnchantment(e);
		}
		return this;
	}

	public ItemBuilder color(Color color) {
		if (super.getType() == Material.LEATHER_BOOTS || super.getType() == Material.LEATHER_CHESTPLATE
				|| super.getType() == Material.LEATHER_HELMET
				|| super.getType() == Material.LEATHER_LEGGINGS) {
			LeatherArmorMeta meta = (LeatherArmorMeta) super.getItemMeta();
			meta.setColor(color);
			super.setItemMeta(meta);
			return this;
		} else {
			throw new IllegalArgumentException("color() only applicable for leather armor!");
		}
	}

	public ItemStack build() {
		return this;
	}
}
