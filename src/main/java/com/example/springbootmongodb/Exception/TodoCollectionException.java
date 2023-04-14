package com.example.springbootmongodb.Exception;

public class TodoCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public TodoCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Not found" + id;
    }

    public static String TodoAlreadyExistsException() {
        return "Todo already exists";
    }

}
