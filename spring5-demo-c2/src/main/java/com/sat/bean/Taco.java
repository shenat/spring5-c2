package com.sat.bean;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import lombok.Data;

//墨西哥卷
@Data
public class Taco {

  // end::allButValidation[]
  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  // tag::allButValidation[]
  private String name;
  // end::allButValidation[]
  @Size(min=1, message="You must choose at least 1 ingredient")
  // tag::allButValidation[]
  private List<String> ingredients;
	
	

}
