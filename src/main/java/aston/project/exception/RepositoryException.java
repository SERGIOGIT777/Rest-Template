package aston.project.exception;

import java.sql.SQLException;

public class RepositoryException extends RuntimeException{

    public RepositoryException(SQLException e){
        super(e);
    }
}
