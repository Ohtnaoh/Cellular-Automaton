package automaton.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIError extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIError(String ErreurMsg) {
		JFrame Erreur = new JFrame("Erreur");
		JPanel ErreurPan = new JPanel();
		BorderLayout layout = new BorderLayout();
		Erreur.setLayout(layout);


		// Elément 
		JLabel lblErr = new JLabel(ErreurMsg);
		JButton btnErr = new JButton("Recommencer");
		
		
		ErreurPan.add(lblErr, BorderLayout.NORTH);
		ErreurPan.add(btnErr, BorderLayout.CENTER);
		
		// Config Fenêtre
		Erreur.setSize(250, 100);
		Erreur.setLocationRelativeTo(null); // centre la fentre
		Erreur.setContentPane(ErreurPan);
		Erreur.setVisible(true);
		
		btnErr.addActionListener(new ActionListener(){
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		Erreur.setVisible(false);

			}});
	}

}
