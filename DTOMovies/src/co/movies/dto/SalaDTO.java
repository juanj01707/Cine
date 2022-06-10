package co.movies.dto;

import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class SalaDTO {
	private int salaId;
	private String salaName;
	private SedeDTO sede;
	private salaStatement salaStatement;
	private ChairDTO chair;
	

	public SalaDTO() {
		super();
		setSalaId(salaId);
		setSalaName(UtilText.EMPTY);
		setSede(new SedeDTO());
		setSalaStatement(new salaStatement());
		setChair(new ChairDTO());
	}

	public SalaDTO(int salaId, String salaName, SedeDTO sede, salaStatement salaStatement, ChairDTO chair) {
		super();
		setSalaId(salaId);
		setSalaName(salaName);
		setSede(sede);
		setSalaStatement(salaStatement);
		setChair(chair);
	}


	public int getSalaId() {
		return salaId;
	}
	public void setSalaId(int salaId) {
		this.salaId = salaId;
	}
	public String getSalaName() {
		return salaName;
	}
	public void setSalaName(String salaName) {
		this.salaName = UtilText.getDefault(salaName);
	}
	public SedeDTO getSede() {
		return sede;
	}
	public void setSede(SedeDTO sede) {
		this.sede = UtilObject.getUtilObject().getDefault(sede, new SedeDTO());
	}
	public salaStatement getEstadoSala() {
		return salaStatement;
	}
	public void setSalaStatement(salaStatement salaStatement) {
		this.salaStatement = UtilObject.getUtilObject().getDefault(salaStatement, new salaStatement());
	}
	public ChairDTO getChair() {
		return chair;
	}
	public void setChair(ChairDTO chair) {
		this.chair = UtilObject.getUtilObject().getDefault(chair, new ChairDTO());
	}

	
	


}
