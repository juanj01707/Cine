package co.movies.dto;

import co.movies.crosscutting.util.text.UtilText;

public class ProductDTO {
	
	private int productId;
	private String productIdNumber;
	private String productName;
	private int price;
	
	public ProductDTO() {
		super();
		setProductId(productId);
		setProductIdNumber(UtilText.EMPTY);
		setProductName(UtilText.EMPTY);
		setPrice(price);
	}
	
	public ProductDTO(int productId, String productIdNumber, String productName, int price) {
		super();
		setProductId(productId);
		setProductIdNumber(productIdNumber);
		setProductName(productName);
		setPrice(price);
	}




	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductIdNumber() {
		return productIdNumber;
	}
	public void setProductIdNumber(String productIdNumber) {
		this.productIdNumber = UtilText.getDefault(productIdNumber);
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = UtilText.getDefault(productName);
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	


}
