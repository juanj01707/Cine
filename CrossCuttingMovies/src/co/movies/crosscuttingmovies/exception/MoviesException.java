package co.movies.crosscuttingmovies.exception;

import static co.movies.crosscutting.util.object.UtilObject.getUtilObject;

import co.movies.crosscutting.exception.GeneralException;
import co.movies.crosscuttingmovies.exception.enumeration.ExceptionLocation;
import co.movies.crosscuttingmovies.exception.enumeration.ExceptionType;

public class MoviesException extends GeneralException {

	private static final long serialVersionUID = 625249639280789375L;

	private ExceptionType type;
	private ExceptionLocation location;

	private MoviesException(String userMessage, String technicalMessage, Exception rootException, ExceptionType type, ExceptionLocation location) {
		super(userMessage, technicalMessage, rootException);
		setType(type);
		setLocation(location);
	}

	public static MoviesException buildUserException(String userMessage) {
		return new MoviesException(userMessage, userMessage, null, ExceptionType.BUSINESS, null);
	}

	public static MoviesException buildTechnicalException(String technicalMessage) {
		return new MoviesException(null, technicalMessage, null, ExceptionType.TECHNICAL, null);
	}

	public static MoviesException buildTechnicalException(String technicalMessage, Exception rootException, ExceptionLocation location) {
		return new MoviesException(null, technicalMessage, rootException, ExceptionType.TECHNICAL, location);
	}

	public static MoviesException buildTechnicalDataException(String technicalMessage) {
		return new MoviesException(null, technicalMessage, null, ExceptionType.TECHNICAL, ExceptionLocation.DATA);
	}
	
	public static MoviesException buildTechnicalBusinessLogicException(String technicalMessage) {
		return new MoviesException(null, technicalMessage, null, ExceptionType.TECHNICAL, ExceptionLocation.BUSINESS_LOGIC);
	}
	
	public static MoviesException buildBusinessLogicException(String BusinessMessage) {
		return new MoviesException(BusinessMessage, null, null, ExceptionType.BUSINESS, ExceptionLocation.BUSINESS_LOGIC);
	}

	public static MoviesException buildTechnicalDataException(String technicalMessage, Exception rootException) {
		return new MoviesException(null, technicalMessage, rootException, ExceptionType.TECHNICAL, ExceptionLocation.DATA);
	}

	public static MoviesException build(String userMessage, String technicalMessage) {
		return new MoviesException(userMessage, technicalMessage, null, null, null);
	}

	public static MoviesException build(String userMessage, String technicalMessage, Exception rootException) {
		return new MoviesException(userMessage, technicalMessage, rootException, null, null);
	}

	private void setType(ExceptionType type) {
		this.type = getUtilObject().getDefault(type, ExceptionType.GENERAL);
	}

	private void setLocation(ExceptionLocation location) {
		this.location = getUtilObject().getDefault(location, ExceptionLocation.GENERAL);
	}

	public ExceptionType getType() {
		return type;
	}

	public ExceptionLocation getLocation() {
		return location;
	}
}