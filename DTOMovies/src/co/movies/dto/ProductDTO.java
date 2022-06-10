package co.movies.dto;

import java.util.ArrayList;
import java.util.List;

import co.movies.crosscutting.util.object.UtilObject;
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

	public void validateName(List<String> validationMessages) {

		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());

		if (UtilText.isEmpty(getProductName())) {
			validationMessages.add("Name of product is required!!!");
		} else if (UtilText.getDefault(getProductName()).length() > 50) {
			validationMessages.add("lenght of name of product must be less o equals to 50 characters!!!");
		} else if (UtilText.getDefault(getProductName()).matches("^[a-zA-ZñÑáÁéÉíÍóÓúÚ ]*$")) {
			validationMessages.add("Name of product contais invalid characters!!!");
		}
	}

}
