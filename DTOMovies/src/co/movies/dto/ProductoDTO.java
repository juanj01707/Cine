package co.movies.dto;

import java.util.ArrayList;

import java.util.List;

import co.movies.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class ProductoDTO {
	private int id;
	private String name;
	private List<StockDTO> stocks;

	public ProductoDTO() {
		super();
		setName(UtilText.EMPTY);
		setStocks(new ArrayList<StockDTO>());
	}
	public ProductoDTO(int id, String name, List<StockDTO> stocks) {
		super();
		setId(id);
		setName(name);
		setStocks(stocks);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return UtilText.getDefault(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<StockDTO> getStocks() {
		return stocks;
	}
	public void setStocks(List<StockDTO> stocks) {
		this.stocks = UtilObject.getUtilObject().getDefault(stocks, new ArrayList<StockDTO>());
	}
	
	
}

