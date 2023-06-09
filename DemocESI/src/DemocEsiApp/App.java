package DemocEsiApp;

import vista.MenuPropuestaPanel;

import vista.MenuJornadaPanel;
import vista.MenuMaterialPanel;

import javax.swing.JFrame;

import vista.MenuPrincipalPanel;
import vista.*;
public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
			JFrame marco  = new JFrame();
			marco.setBounds(350, 100, 800, 600);
			marco.setVisible(true);
			marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			marco.setContentPane(new MenuPrincipalPanel(marco));
			marco.validate(); 
			
	}

}
