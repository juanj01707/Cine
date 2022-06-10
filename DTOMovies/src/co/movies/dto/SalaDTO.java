package co.movies.dto;

import java.util.ArrayList;
import java.util.List;

import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class SalaDTO {
	private int salaId;
	private String salaName;
	private SedeDTO sede;
	private SalaStatementDTO salaStatement;
	private ChairDTO chair;
	

	public SalaDTO() {
		super();
		setSalaId(salaId);
		setSalaName(UtilText.EMPTY);
		setSede(new SedeDTO());
		setSalaStatement(new SalaStatementDTO());
		setChair(new ChairDTO());
	}

	public SalaDTO(int salaId, String salaName, SedeDTO sede, SalaStatementDTO salaStatement, ChairDTO chair) {
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
	public SalaStatementDTO getSalaStatement() {
		return salaStatement;
	}
	public void setSalaStatement(SalaStatementDTO salaStatement) {
		this.salaStatement = UtilObject.getUtilObject().getDefault(salaStatement, new SalaStatementDTO());
	}
	public ChairDTO getChair() {
		return chair;
	}
	public void setChair(ChairDTO chair) {
		this.chair = UtilObject.getUtilObject().getDefault(chair, new ChairDTO());
	}

public void validateName(List<String> validationMessages){
		
		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());
		
		
		if(UtilText.isEmpty(getSalaName())) {
			validationMessages.add("Name of sala is required!!!");
		} else if(UtilText.getDefault(getSalaName()).length() > 50) {
			validationMessages.add("lenght of name of sala must be less o equals to 50 characters!!!");
		} else if(UtilText.getDefault(getSalaName()).matches("^[a-zA-ZñÑáÁéÉíÍóÓúÚ ]*$")) {
			validationMessages.add("Name of sala contais invalid characters!!!");
		}
	}
	


}
