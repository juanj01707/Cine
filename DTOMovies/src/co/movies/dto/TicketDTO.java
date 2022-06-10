package co.movies.dto;

import java.util.ArrayList;
import java.util.List;

import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class TicketDTO {
	

	private int ticketId;
	private String ticketIdNumber;
	private FuntionDTO funtion;
	private int price;

	public TicketDTO() {
		super();
		setTicketId(ticketId);
		setTicketIdNumber(UtilText.EMPTY);
		setFuntion(new FuntionDTO());
		setPrice(price);
	}
	
	

	public TicketDTO(int ticketId, String ticketIdNumber, FuntionDTO funtion, int price) {
		super();
		setTicketId(ticketId);
		setTicketIdNumber(ticketIdNumber);
		setFuntion(funtion);
		setPrice(price);
	}



	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketIdNumber() {
		return ticketIdNumber;
	}

	public void setTicketIdNumber(String ticketIdNumber) {
		this.ticketIdNumber = UtilText.getDefault(ticketIdNumber);
	}

	public FuntionDTO getFuntion() {
		return funtion;
	}

	public void setFuntion(FuntionDTO funtion) {
		this.funtion = UtilObject.getUtilObject().getDefault(funtion, new FuntionDTO());
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

public void validateName(List<String> validationMessages){
		
		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());
		
		
		if(UtilText.isEmpty(getTicketIdNumber())) {
			validationMessages.add("Name of id type is required!!!");
		} else if(UtilText.getDefault(getTicketIdNumber()).length() > 50) {
			validationMessages.add("lenght of name of id type must be less o equals to 50 characters!!!");
		} else if(UtilText.getDefault(getTicketIdNumber()).matches("^[a-zA-ZñÑáÁéÉíÍóÓúÚ ]*$")) {
			validationMessages.add("Name of id type contais invalid characters!!!");
		}
	}




}
