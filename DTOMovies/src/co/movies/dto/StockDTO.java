package co.movies.dto;

import co.movies.crosscutting.util.numeric.UtilNumeric;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText; 

public class StockDTO {
	private int id;
	private String name;
	private int unit;
	private ProductDTO Mekato;

	public StockDTO() {
		super();
		setName(UtilText.EMPTY);
		setMekato(new ProductDTO());
		setUnit(0);
	}
	public StockDTO(int id, String name, int unit, ProductDTO Mekato) {
		super();
		setId(id);
		setName(name);
		setMekato(Mekato);
		setUnit(unit);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = UtilText.getDefault(name);
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = (int) UtilNumeric.getUtilNumeric().getDefault(unit);
	}

	public ProductDTO getMekato() {
		return Mekato;
	}

	public void setMekato(ProductDTO Mekato) {
		this.Mekato = UtilObject.getUtilObject().getDefault(Mekato, new ProductDTO());
	}
	
}
