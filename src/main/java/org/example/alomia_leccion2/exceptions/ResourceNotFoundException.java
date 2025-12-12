package org.example.alomia_leccion2.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message)  {
        super(message);
    }
}
