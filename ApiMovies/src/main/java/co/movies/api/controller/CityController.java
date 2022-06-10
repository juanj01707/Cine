package co.movies.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.movies.api.controller.response.Response;
import co.movies.api.controller.validator.Validator;
import co.movies.api.controller.validator.city.CreatedCityValidator;
import co.movies.api.controller.validator.idtype.CreateIdTypeValidator;

import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscuttingmovies.exception.MoviesException;
import co.movies.crosscuttingmovies.exception.enumeration.ExceptionType;
import co.movies.dto.CityDTO;
import co.movies.dto.IdTypeDTO;


@RestController
@RequestMapping("/api/v1/city")
public class CityController {

	@GetMapping("/dummy")
	public CityDTO getDummy() {
		return new CityDTO();
	}

	@PostMapping
	public ResponseEntity<Response<CityDTO>> create(@RequestBody CityDTO dto) {

		Validator<CityDTO> validator = new CreatedCityValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<CityDTO> response = new Response<>();
		ResponseEntity<Response<CityDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		if (messages.isEmpty()) {
			try {
				IdTypeFacade facade = new IdTypeFacadeImpl();
				facade.create(dto);
				;
				messages.add("Id type was created succesfully!");
				statusCode = HttpStatus.OK;

			} catch (MoviesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("there was a problem trying to register the new city. Please, try again... ");
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getTechnicalMessage());
					exception.getRootException().printStackTrace();
				} else {
					messages.add(exception.getMessage());
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getUserMessage());
					exception.getRootException().printStackTrace();
				}
			} catch (Exception exception) {
				messages.add("ther was an unexpected problem trying to register the new city. Please, try again...");
				exception.printStackTrace();
			}

		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<CityDTO>>(response, statusCode);

		return responseEntity;
	}

	@PutMapping("/{id}")
	public void update(@PathVariable("id") int id, @RequestBody CityDTO dto) {
		System.out.print("Estoy en actualizar!!");

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		System.out.print("Estoy en eliminar!!");
	}

	@GetMapping("/{id}")
	public void findById(@PathVariable("id") int id) {
		System.out.print("Estoy en consultar por id!!");
	}

	@GetMapping
	public ResponseEntity<Response<CityDTO>> find() {

		List<String> messages = new ArrayList<>();
		Response<CityDTO> response = new Response<>();
		ResponseEntity<Response<CityDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		if (messages.isEmpty()) {
			try {
				IdTypeFacade facade = new IdTypeFacadeImpl();
				response.setData(facade.find(new IdTypeDTO()));
				messages.add("Id type were found succesfully!");
				statusCode = HttpStatus.OK;

			} catch (MoviesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("there was a problem trying to register the new city. Please, try again... ");
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getTechnicalMessage());
					exception.getRootException().printStackTrace();
				} else {
					messages.add(exception.getMessage());
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getUserMessage());
					exception.getRootException().printStackTrace();
				}
			} catch (Exception exception) {
				messages.add("ther was an unexpected problem trying to register the new city. Please, try again...");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);

		return responseEntity;
	}
}
