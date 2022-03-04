package comp3350.group6.promise.business;

public class EmptyInputException extends Exception {
    public EmptyInputException(String fieldName){
        super("The field [" + fieldName + "] should not be empty.");
    }
}
