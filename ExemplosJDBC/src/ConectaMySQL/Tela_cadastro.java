package ConectaMySQL;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class Tela_cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfUsuario;
	private JTextField tfSenha;
	private JTextField tfBusca;
	private JTable tbDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastro frame = new Tela_cadastro();
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
	public Tela_cadastro() {
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 46, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setBounds(10, 90, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(10, 133, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(64, 43, 73, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(64, 87, 171, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfSenha = new JTextField();
		tfSenha.setBounds(64, 130, 171, 20);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 305, 414, 72);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(tfUsuario.getText().equals("") || tfSenha.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Usuario/senha nao encontrado");
				}else {
				
				
				try {
					
					Connection con = Conexcaosql.faz_conexao();
					String sql = "insert into dados_senhas(usuario, senha) value (?, ?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, tfSenha.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Dado, Cadastrado!");
					
					tfUsuario.setText("");
					tfSenha.setText("");
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			}
		});
		btnNewButton.setBounds(10, 21, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");
				}else {
				try {
					Connection con = Conexcaosql.faz_conexao();
					
					String sql = "update dados_senhas set usuario=?, senha=? where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, tfSenha.getText());
					stmt.setString(3, tfId.getText());
					
					stmt.execute();
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
				
				
				
			}
		});
		btnAtualizar.setBounds(111, 21, 89, 23);
		panel.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID!");
				}else {
				try {
					Connection con = Conexcaosql.faz_conexao();
					
					String sql = "delete from dados_senhas where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfId.getText());
					
					stmt.execute();
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Dado Excluido!");
					
					tfId.setText("");
					tfSenha.setText("");
					tfUsuario.setText("");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			}	
		});
		btnExcluir.setBounds(213, 21, 89, 23);
		panel.add(btnExcluir);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Abrir dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 388, 414, 57);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		tfBusca = new JTextField();
		tfBusca.setBounds(93, 24, 86, 20);
		panel_1.add(tfBusca);
		tfBusca.setColumns(10);
		
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfBusca.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID");
				}else {
					
				
				try {
					Connection con = Conexcaosql.faz_conexao();
					
					String sql = "select *from dados_senhas where id like ?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, "%"+tfBusca.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						tfId.setText(rs.getString("id"));
						tfUsuario.setText(rs.getString("usuario"));
						tfSenha.setText(rs.getString("senha"));
						
						
						
					}
					
					rs.close();
					con.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnAbrir.setBounds(10, 23, 73, 23);
		panel_1.add(btnAbrir);
		
		JButton btnListarDados = new JButton("Listar Dados");
		btnListarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexcaosql.faz_conexao();
					
					String sql = "Select *from dados_senhas";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object[] {rs.getString("id"), rs.getString("usuario"), rs.getString("senha")});
						
					}
					
					rs.close();
					con.close();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnListarDados.setBounds(278, 23, 126, 23);
		panel_1.add(btnListarDados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 210, 414, 84);
		contentPane.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Id", "Usu\u00E1rio", "Senha"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tbDados);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Ações");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                if(tfUsuario.getText().equals("") || tfSenha.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Usuario/senha nao encontrado");
				}else {
					
					acoes ac = new acoes(tfUsuario.getText(), tfSenha.getText());
					ac.salvar();
					
					tfUsuario.setText("");
					tfSenha.setText("");
					
					
				}
                
                
                
				
				
				
				
			}
		});
		mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmSalvar);
		
		JMenuItem mntmAtualizar = new JMenuItem("Atualizar");
		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");
				}else {
					
					acoes ac = new acoes(Integer.parseInt(tfId.getText()), tfUsuario.getText(), tfSenha.getText());
					ac.atualizar();
					
					
					
					
					
					
					
				}
				
				
				
			}
		});
		mntmAtualizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmAtualizar);
		
		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID!");
				}else {
					
					acoes ac = new acoes(Integer.parseInt(tfId.getText()), tfUsuario.getText(), tfSenha.getText());
					ac.exluir();
					
				}
				
				
				
				
			}
		});
		mntmExcluir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnNewMenu.add(mntmExcluir);
	}
}
