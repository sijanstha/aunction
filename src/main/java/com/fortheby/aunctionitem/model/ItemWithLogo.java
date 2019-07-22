package com.fortheby.aunctionitem.model;

import com.fortheby.aunctionitem.entity.Item;

public class ItemWithLogo {

	private Item item;
	private ItemLogo logo;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemLogo getLogo() {
		return logo;
	}

	public void setLogo(ItemLogo logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return "ItemWithLogo [item=" + item + ", logo=" + logo + "]";
	}

	

}
