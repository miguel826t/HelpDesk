package br.mrc.helpdesk.infrastructure.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.mrc.helpdesk.infrastructure.services.exceptions.DataIntegrityViolationException;
import br.mrc.helpdesk.infrastructure.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectnotFoundException(ObjectNotFoundException ex,HttpServletRequest request){
		
		StandardError error = new StandardError(
				System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(),
				"Object Not Found", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,HttpServletRequest request){
		
		StandardError error = new StandardError(
				System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),
				"Violação de dados", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex,HttpServletRequest request){
		
		ValidationError errors = new ValidationError(
				System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Validation error","Erro na validação dos campos",request.getRequestURI());
		
		for(FieldError e : ex.getBindingResult().getFieldErrors()) {
			errors.addError(e.getField(),e.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
}
