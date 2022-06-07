package co.movies.dto;

import co.movies.crosscutting.util.numeric.UtilNumeric;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class StockDTO {
	private int id;
	private String name;
	private int unit;
	private MekatoDTO Mekato;

	public StockDTO() {
		super();
		setName(UtilText.EMPTY);
		setMekato(new MekatoDTO());
		setUnit(0);
	}
	public StockDTO(int id, String name, int unit, MekatoDTO Mekato) {
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

	public MekatoDTO getMekato() {
		return Mekato;
	}

	public void setRawMaterial(MekatoDTO Mekato) {
		this.Mekato = UtilObject.getUtilObject().getDefault(Mekato, new MekatoDTO());
	}
	
}
