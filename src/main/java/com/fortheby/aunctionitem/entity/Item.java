package com.fortheby.aunctionitem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fortheby.admin.entity.Category;
import com.fortheby.login.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private int itemLotNumber;

	@Column(name = "title")
	private String title;

	@Column(name = "artist_name")
	@NotEmpty
	private String artistName;

	@Column(name = "year_produced")
	private String dateOfCreation;

	@Column(name = "classification")
	private String classification;

	@Column(name = "description", length=3000)
	private String description;

	@Column(name = "aunction_start_date")
	private String aunctionStartDate;

	@Column(name = "aunction_end_date")
	private String aunctionEndDate;

	@Column(name = "estimated_price")
	private double estimatePrice;

	@JsonIgnore
	@Column(name = "image_path")
	private String imagePath;

	// Following are additional details based on category
	@Column(name = "length_cm")
	private double lengthInCm;

	@Column(name = "height_cm")
	private double heightInCm;

	@Column(name = "medium_used")
	private String meduimUsed;

	@Column(name = "is_framed")
	private boolean isFramed;

	@Column(name = "approx_weight")
	private double approxWeight;

	@Column(name = "image_type")
	private String imageType;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "uploaded_by", referencedColumnName = "user_id")
	private User user;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getItemLotNumber() {
		return itemLotNumber;
	}

	public void setItemLotNumber(int itemLotNumber) {
		this.itemLotNumber = itemLotNumber;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAunctionStartDate() {
		return aunctionStartDate;
	}

	public void setAunctionStartDate(String aunctionStartDate) {
		this.aunctionStartDate = aunctionStartDate;
	}

	public String getAunctionEndDate() {
		return aunctionEndDate;
	}

	public void setAunctionEndDate(String aunctionEndDate) {
		this.aunctionEndDate = aunctionEndDate;
	}

	public double getEstimatePrice() {
		return estimatePrice;
	}

	public void setEstimatePrice(double estimatePrice) {
		this.estimatePrice = estimatePrice;
	}

	public double getLengthInCm() {
		return lengthInCm;
	}

	public void setLengthInCm(double lengthInCm) {
		this.lengthInCm = lengthInCm;
	}

	public double getHeightInCm() {
		return heightInCm;
	}

	public void setHeightInCm(double heightInCm) {
		this.heightInCm = heightInCm;
	}

	public String getMeduimUsed() {
		return meduimUsed;
	}

	public void setMeduimUsed(String meduimUsed) {
		this.meduimUsed = meduimUsed;
	}

	public boolean isFramed() {
		return isFramed;
	}

	public void setFramed(boolean isFramed) {
		this.isFramed = isFramed;
	}

	public double getApproxWeight() {
		return approxWeight;
	}

	public void setApproxWeight(double approxWeight) {
		this.approxWeight = approxWeight;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Item [itemLotNumber=" + itemLotNumber + ", title=" + title + ", artistName=" + artistName
				+ ", dateOfCreation=" + dateOfCreation + ", classification=" + classification + ", description="
				+ description + ", aunctionStartDate=" + aunctionStartDate + ", aunctionEndDate=" + aunctionEndDate
				+ ", estimatePrice=" + estimatePrice + ", imagePath=" + imagePath + ", lengthInCm=" + lengthInCm
				+ ", heightInCm=" + heightInCm + ", meduimUsed=" + meduimUsed + ", isFramed=" + isFramed
				+ ", approxWeight=" + approxWeight + ", imageType=" + imageType + ", category=" + category + ", user="
				+ user + "]";
	}

}
