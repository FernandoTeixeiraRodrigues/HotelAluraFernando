package br.com.alura.controles;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.alura.modelo.Usuarios;
import views.Login;
import views.MenuUsuario;
public class UsuarioControles implements ActionListener {
	
	private Login vista;
	
	public UsuarioControles(Login vista) {
		
       this.vista = vista;
			
	}	

@Override
public void actionPerformed(ActionEvent e) {
	
	

	String nome = vista.getNome();
	String senha = vista.getSenha();
	
	if (Usuarios.validarUsuario(nome, senha)) {
		
		MenuUsuario menu = new MenuUsuario();
		menu.setVisible(true);
		vista.dispose();
		
	}else {
		JOptionPane.showMessageDialog(vista, "Usuário ou Senha Incorretos Tente Novamente!");
	}
	
	
				
		

	
}	
	
}
