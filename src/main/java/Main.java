import aston.project.db.ConnectionManager;
import aston.project.db.ConnectionManagerImpl;
import aston.project.util.InitSqlScheme;

public class Main {
    public static void main(String[] args) {
        ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
        InitSqlScheme.initSqlScheme(connectionManager);
        InitSqlScheme.initSqlData(connectionManager);

    }
}