package com.workshop.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException { // this "RUNTimeException the exeptions occurred we do not need resolve
// if the class extends teh "Exception" we need resolve the Exception

public static final long serialVersionUID = 1L;

public ObjectNotFoundException(String msg)
{   
    super(msg);

}
    
}
