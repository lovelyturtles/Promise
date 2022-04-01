package comp3350.group6.promise.persistence.hsqldb;

/*
    Not used for iteration 1.
 */
public class PersistenceException extends RuntimeException{
    public PersistenceException(final Exception cause){
        super(cause);
    }
}
