package ConectaMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexcaosql {
	
	
	public static Connection faz_conexao() throws SQLException {
		
		
		
		
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost/db_senhas", "root", "Kvacek12345");
			
			
			
			
		} catch (ClassNotFoundException e) {
			
			throw new SQLException(e.getException());
		}
		
	}
	
}
