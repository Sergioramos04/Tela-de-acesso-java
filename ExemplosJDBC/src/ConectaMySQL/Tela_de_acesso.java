package ConectaMySQL;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_de_acesso extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final String Conexao = null;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_de_acesso frame = new Tela_de_acesso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_de_acesso() {
		setResizable(false);
		setTitle("Tela de acesso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 294, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(20, 46, 82, 48);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.RED);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSenha.setBounds(20, 107, 82, 48);
		contentPane.add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUsuario.setBounds(98, 52, 135, 38);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(98, 105, 135, 38);
		contentPane.add(pfSenha);
		
		JButton btEntrar = new JButton("Entrar");
		btEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexcaosql.faz_conexao();
					
					
					String sql = "select *from dados_senhas where usuario=? and senha=?";
					
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, new String(pfSenha.getPassword()));
					
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						
						Tela_cadastro exibir = new Tela_cadastro();
						exibir.setVisible(true);
						setVisible(false);
						
					}else {
						
						JOptionPane.showConfirmDialog(null, "Usuário/Senha incorreto");
						
					}
					
					stmt.close();
					con.close();
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btEntrar.setBackground(new Color(0, 0, 0));
		btEntrar.setForeground(new Color(255, 0, 0));
		btEntrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btEntrar.setBounds(98, 159, 135, 30);
		contentPane.add(btEntrar);
	}
}
