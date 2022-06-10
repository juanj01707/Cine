package co.movies.dto;

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






}
