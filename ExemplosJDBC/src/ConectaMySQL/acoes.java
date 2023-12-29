package ConectaMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class acoes {
	
	private int id;
	private String usuario;
	private String senha;
	
	public acoes(int id_p) {
		
		this.id = id_p;
		
	}
	
	public acoes(String us, String se) {
		
		this.usuario = us;
		this.senha = se;
		
	}
	
	public acoes(int id_p, String us, String se) {
		// TODO Auto-generated constructor stub
		
		this.id = id_p;
		this.usuario = us;
		this.senha = se;
		
	}
	
	//Inicio METODO SALVAR
	public void salvar() {
		
		try {
			
			Connection con = Conexcaosql.faz_conexao();
			String sql = "insert into dados_senhas(usuario, senha) value (?, ?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			
			stmt.setString(1, usuario);
			stmt.setString(2, senha);
			
			stmt.execute();
			
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null, "Dado, Cadastrado!");
			
			
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	//Fim do metodo salvar
	
	public void atualizar() {
		
		try {
			Connection con = Conexcaosql.faz_conexao();
			
			String sql = "update dados_senhas set usuario=?, senha=? where id=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, usuario);
			stmt.setString(2, senha);
			stmt.setInt(3, id);
			
			stmt.execute();
			stmt.close();
			con.close();
			
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	
	public void exluir() {
		
		try {
			Connection con = Conexcaosql.faz_conexao();
			
			String sql = "delete from dados_senhas where id=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
			con.close();
			
			JOptionPane.showMessageDialog(null, "Dado Excluido!");
			
		
		
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

}


