package com.fortheby.aunctionitem.model;

import org.springframework.web.multipart.MultipartFile;

public class ItemLogo {
	private MultipartFile itemLogo;

	public MultipartFile getItemLogo() {
		return itemLogo;
	}

	public void setItemLogo(MultipartFile itemLogo) {
		this.itemLogo = itemLogo;
	}

}
