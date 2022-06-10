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
import co.movies.api.controller.validator.client.CreatedClientValidator;
import co.movies.api.controller.validator.product.CreatedProductValidator;
import co.movies.businesslogic.facade.ProductFacade;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscuttingmovies.exception.MoviesException;
import co.movies.crosscuttingmovies.exception.enumeration.ExceptionType;
import co.movies.dto.ClientDTO;
import co.movies.dto.IdTypeDTO;
import co.movies.dto.ProductDTO;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	

	@GetMapping("/dummy")
	public ProductDTO getDummy() {
		return new ProductDTO();
	}

	@PostMapping
	public ResponseEntity<Response<ProductDTO>> create(@RequestBody ProductDTO dto) {

		Validator<ProductDTO> validator = new CreatedProductValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<ProductDTO> response = new Response<>();
		ResponseEntity<Response<ProductDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		if (messages.isEmpty()) {
			try {
				ProductFacade facade = new ProductFacadeImpl();
				facade.create(dto);
				;
				messages.add("Id type was created succesfully!");
				statusCode = HttpStatus.OK;

			} catch (MoviesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("there was a problem trying to register the new product. Please, try again... ");
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
				messages.add("ther was an unexpected problem trying to register the new product. Please, try again...");
				exception.printStackTrace();
			}

		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<ProductDTO>>(response, statusCode);

		return responseEntity;
	}

	@PutMapping("/{id}")
	public void update(@PathVariable("id") int id, @RequestBody ClientDTO dto) {
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
	public ResponseEntity<Response<ProductDTO>> find() {

		List<String> messages = new ArrayList<>();
		Response<ProductDTO> response = new Response<>();
		ResponseEntity<Response<ProductDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		if (messages.isEmpty()) {
			try {
				ProductFacade facade = new ProductFacadeImpl();
				response.setData(facade.find(new IdTypeDTO()));
				messages.add("Id type were found succesfully!");
				statusCode = HttpStatus.OK;

			} catch (MoviesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("there was a problem trying to register the new product. Please, try again... ");
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
				messages.add("ther was an unexpected problem trying to register the new product. Please, try again...");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<>(response, statusCode);

		return responseEntity;
	}

}
