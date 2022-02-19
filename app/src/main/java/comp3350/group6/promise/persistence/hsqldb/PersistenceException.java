package comp3350.group6.promise.persistence.hsqldb;


public class PersistenceException extends RuntimeException{
    public PersistenceException(final Exception cause){
        super(cause);
    }
}
