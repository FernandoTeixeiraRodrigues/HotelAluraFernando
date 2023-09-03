package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import br.com.alura.controles.HospedesControles;
import br.com.alura.controles.ReservasControles;
import br.com.alura.modelo.Hospedes;
import br.com.alura.modelo.Reservas;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	private ReservasControles reservasControles;
	private HospedesControles hospedesControles;
	private ReservasView reservasView;
	String reservas;
	String hospedes;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Buscar() throws SQLException {
		
		this.setReservasView(new ReservasView());
		this.reservasControles = new ReservasControles();
		this.setHospedesControles(new HospedesControles());
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		mostrarTabelaReservas();
		
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		mostrarTabelaHospedes();
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparTabela();
				if(txtBuscar.getText().equals("")) {
					mostrarTabelaReservas();
					mostrarTabelaHospedes();
				}else {
					buscarTabelaReservasId();
					mostrarTabelaHospedesId();
					}
			}	
			});

		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e ) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHospedes = tbHospedes.getSelectedRow();
				
				if(filaReservas >= 0) {
					AtualizarReservas();
					limparTabela();
					mostrarTabelaReservas();
					mostrarTabelaHospedes();
					
				}else if(filaHospedes >= 0 ) {
					AtualizarHospedes();
					limparTabela();
					mostrarTabelaHospedes();
					mostrarTabelaReservas();
					
					
					
					
				}
				
			}
		
			
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
			int filaReservas = tbReservas.getSelectedRow();
			int filaHospedes = tbHospedes.getSelectedRow();
			
			
			if (filaReservas >= 0) {
				reservas = tbReservas.getValueAt(filaReservas, 0).toString();
				int confirmar = JOptionPane.showConfirmDialog(null, "Deseja excluir a reserva?");
				if (confirmar == JOptionPane.YES_OPTION) {
					String Valor = tbReservas.getValueAt(filaReservas, 0).toString();
					reservasControles.Deletar(Integer.valueOf(Valor));
					JOptionPane.showMessageDialog(contentPane, "Registro Deletado com Sucesso!");
					limparTabela();
					mostrarTabelaReservas();
					mostrarTabelaHospedes();
					
					
					
					
				}
			}else if(filaHospedes >= 0) {
				hospedes = tbHospedes.getValueAt(filaHospedes, 0).toString();
				int confirmarH = JOptionPane.showConfirmDialog(null, "Deseja excluir esse Hospede ?");
				if (confirmarH == JOptionPane.YES_OPTION) {
					String Valor = tbHospedes.getValueAt(filaHospedes, 0).toString();
					hospedesControles.Deletar(Integer.valueOf(Valor));
					JOptionPane.showMessageDialog(contentPane, "Hospede Deletado com Sucesso!");
					limparTabela();
					mostrarTabelaHospedes();
					mostrarTabelaReservas();
			}
			}else {
				
				JOptionPane.showMessageDialog(null, "Houve um erro ao Deletar");
			}
			}
		});
			
	
			
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}
	
	private List<Reservas> mostrarReservas(){
		return this.reservasControles.mostra();
		}
	
	private List<Reservas> buscarIdReservas(){
		return this.reservasControles.buscar(txtBuscar.getText());
		}
	
		private void mostrarTabelaReservas()  {
			
			List<Reservas> reserva = mostrarReservas();
			modelo.setRowCount(0);
			try {
				for(Reservas reservas : reserva) {
					modelo.addRow(new Object[] {
							reservas.getId(), reservas.getDataE(), reservas.getDataS(), 
							reservas.getValor(), reservas.getFormaPaga()
							
					
					});
					
					
				}
			}catch (Exception e) {
				throw e ;
			}
		
		
		}
			
			
		
		
		
		
		
private void buscarTabelaReservasId()  {
			List<Reservas> reserva = buscarIdReservas();
			try {
				for(Reservas reservas : reserva) {
					modelo.addRow(new Object[] {
							reservas.getId(), reservas.getDataE(), reservas.getDataS(), 
							reservas.getValor(), reservas.getFormaPaga()});
					
				}
			} catch (Exception e) {
				throw e;
				
				
			}
			
			
		}

	private void AtualizarReservas() {
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresent(linha ->{
			LocalDate DataE;
		    LocalDate DataS;
		    
		    try {
		    	
		    	DateTimeFormatter dateFormat= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    	DataE = LocalDate.parse(modelo.getValueAt(tbReservas
		    			.getSelectedRow(), 1).toString(), dateFormat);
		    	DataS = LocalDate.parse(modelo.getValueAt(tbReservas
		    			.getSelectedRow(), 2).toString(), dateFormat);
		    	
		    	
		    	
		    	
		    	
		    	
		    }catch(DateTimeException e) {
		    	throw new RuntimeException(e);
		    	
		    	
		    }
		    
		    
			this.reservasView.limparValor();
			String Valor = calcularValorReservas(DataE, DataS);
			String FormaPaga = (String) modelo.getValueAt(tbReservas.getSelectedRow(),4);
			Integer Id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			if (tbReservas.getSelectedColumn() ==0) {
				
				JOptionPane.showMessageDialog(this, "Não pode alterar ID");
			}else {
				this.reservasControles.atualizarReservas(DataE, DataS, Valor, FormaPaga, Id);
				JOptionPane.showMessageDialog(this,String.format("Registro Modificado com Sucesso!"));
			}
			
			
		});
	}
	
	//Hospedes CRUD;
 	
	private List<Hospedes> mostraHospedes(){
		return this.hospedesControles.mostraHospedes();
	}
public List<Hospedes> buscarIdHospedes(){
		
		return this.hospedesControles.buscarIdHospedes(txtBuscar.getText());
}

private void mostrarTabelaHospedes() {
	List<Hospedes> hospede = mostraHospedes();
	modeloHospedes.setRowCount(0);
	try {
		for(Hospedes hospedes : hospede) {
			modeloHospedes.addRow(new Object[] {
					hospedes.getId(), hospedes.getNome(), hospedes.getSobrenome(), 
					hospedes.getDataNascimento(), hospedes.getNacionalidade(), 
					hospedes.getTelefone(), hospedes.getIdReserva()
					
			
			});
			
			
		}
	}catch (Exception e) {
		throw e ;
	}


}
private void mostrarTabelaHospedesId() {
	List<Hospedes> hospede = buscarIdHospedes();
	modeloHospedes.setRowCount(0);
	try {
		for(Hospedes hospedes : hospede) {
			modeloHospedes.addRow(new Object[] {
					hospedes.getId(), hospedes.getNome(), hospedes.getSobrenome(), 
					hospedes.getDataNascimento(), hospedes.getNacionalidade(), 
					hospedes.getTelefone(), hospedes.getIdReserva()
					
			
			});
			
			
		}
	}catch (Exception e) {
		throw e ;
	}


}

public void AtualizarHospedes() {
	
	Optional.ofNullable(modeloHospedes.getValueAt
(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn())).ifPresentOrElse(filaHospedes ->{
		
		
		String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1);
	    String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);
		LocalDate dataNascimento = LocalDate.parse(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(),3).toString());
		String nacionalidade = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4);
		String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);
		Integer IdReserva = Integer.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 6).toString());
		Integer Id = Integer.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0).toString());
		if (tbHospedes.getSelectedColumn()  == 0 || tbHospedes.getSelectedColumn() == 6) {
			JOptionPane.showMessageDialog(this, "Não pode fazer essa alteração!");
			
			
		}else {
			
			this.hospedesControles.AtualizarHospedes(nome, sobrenome, dataNascimento , nacionalidade, telefone, IdReserva, Id);
			JOptionPane.showMessageDialog(this, String.format("Registro Modificado com Sucesso!"));
			}
		}
		
		, ()-> JOptionPane.showInternalMessageDialog(this, "Por Favor Cuidado "));
}



	
	public String calcularValorReservas(LocalDate DataE, LocalDate DataS) {
		
		if (DataE !=null && DataS !=null) {
			
			int dias = (int) ChronoUnit.DAYS.between(DataE, DataS);
			int noites = 80;
			int valor = dias * noites;
			return Integer.toString(valor);
			}else {
				return "";
			}
		
	}
	
	

private void limparTabela() {
	
	((DefaultTableModel)tbHospedes.getModel()).setRowCount(0);
	((DefaultTableModel)tbReservas.getModel()).setRowCount(0);
	
	
}
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}

		public ReservasView getReservasView() {
			return reservasView;
		}

		public void setReservasView(ReservasView reservasView) {
			this.reservasView = reservasView;
		}

		public HospedesControles getHospedesControles() {
			return hospedesControles;
		}

		public void setHospedesControles(HospedesControles hospedesControles) {
			this.hospedesControles = hospedesControles;
		}
}
