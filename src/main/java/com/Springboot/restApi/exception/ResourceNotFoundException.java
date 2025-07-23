package com.Springboot.restApi.exception;
 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	private String ResourceName;
	private String FieldName;
	private Long FieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
		super(String.format("%s is not found %s : '%s'",resourceName,fieldName,fieldValue));
		ResourceName = resourceName;
		FieldName = fieldName;
		FieldValue = fieldValue;
	}
	public String getResourceName() {
		return ResourceName;
	}
	public String getFieldName() {
		return FieldName;
	}
	public Long getFieldValue() {
		return FieldValue;
	}
	
 
}
 