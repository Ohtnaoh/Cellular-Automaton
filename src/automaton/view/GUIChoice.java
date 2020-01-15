package automaton.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import automaton.model.TypeAutomaton;
import automaton.model.TypeExtension;

public class GUIChoice extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String TAILLEX = "50";
	private String TAILLEY = "50";
	private String DIMCELL = "10";
	

	/**
	 * Permet de définir les contraintes 
	 * @param gbc
	 * @param x
	 * @param y
	 * @param direction
	 * @return
	 */
	private GridBagConstraints addConstraints(GridBagConstraints gbc, int x, int y, int direction) {
		
		 gbc.gridx = x;
	     gbc.gridy = y;
	     
	     switch (direction) {
			case 1:
				gbc.fill = GridBagConstraints.HORIZONTAL;
				break;
			case 2:
				gbc.fill = GridBagConstraints.VERTICAL;
				break;
			default:
				throw new IllegalArgumentException("This number is not valid for the direction"); 
			}

		return gbc;
	}
	
	public GUIChoice(String titre) {
		super(titre);
		
	    JPanel choixFenetre = new JPanel();
	    GridBagLayout layout = new GridBagLayout();
	    choixFenetre.setLayout(layout);
	    GridBagConstraints org = new GridBagConstraints();
	    
	    JLabel lblChoixAuto = new JLabel("   Choix de l'automate :   ");
	    lblChoixAuto.setFont(new Font("Helvetica", Font.BOLD, 11));
	    JComboBox<?> comboBoxAuto = new JComboBox<Object>(TypeAutomaton.values());
	    comboBoxAuto.setFont(new Font("Helvetica", Font.BOLD, 11));
	    
	    JLabel lblChoixExt = new JLabel("   Choix de l'extension :   ");
	    lblChoixExt.setFont(new Font("Helvetica", Font.BOLD, 11));
	    JComboBox<?> comboExt = new JComboBox<Object>(TypeExtension.values());
	    comboExt.setFont(new Font("Helvetica", Font.BOLD, 11));
	    
	    JLabel lblChoixDimGrille = new JLabel("   Dimension de la grille de l'automate (case) :   ");
	    lblChoixDimGrille.setFont(new Font("Helvetica", Font.BOLD, 11));
	    JTextField dimGrillexy = new JTextField(TAILLEX);
	    dimGrillexy.setPreferredSize(new Dimension(40, 20));
	    JLabel x = new JLabel(" x");
	    x.setPreferredSize(new Dimension(14, 10));
	    x.setFont(new Font("Helvetica", Font.BOLD, 11));
	    JTextField dimGrilleyx = new JTextField(TAILLEY);
	    dimGrilleyx.setPreferredSize(new Dimension(40, 20));
	    dimGrilleyx.setEnabled(true); // On empêche l'écriture dans la TextField

	    JLabel lblChoixDimCellule = new JLabel("   Dimension des céllules(px) :   ");
	    lblChoixDimCellule.setFont(new Font("Helvetica", Font.BOLD, 11));
	    JTextField dimCellule = new JTextField(DIMCELL);
	    dimCellule.setFont(new Font("Helvetica", Font.BOLD, 11));
	    dimCellule.setPreferredSize(new Dimension(30, 20));
	    
	    JButton buttonGo = new JButton("Démarer l'automate !");
	    buttonGo.setFont(new Font("Helvetica", Font.BOLD, 13));
	    buttonGo.setPreferredSize(new Dimension(0, 30));
	    
	   
	    
	    choixFenetre.add(lblChoixAuto, addConstraints(org, 0, 0, 1));
	    choixFenetre.add(comboBoxAuto, addConstraints(org, 1, 0, 1));
	    
	    choixFenetre.add(lblChoixExt, addConstraints(org, 2, 0, 1));
	    choixFenetre.add(comboExt, addConstraints(org, 3, 0, 1));
	    
	    choixFenetre.add(lblChoixDimGrille, addConstraints(org, 4, 0, 1));
	    
	    choixFenetre.add(dimGrillexy, addConstraints(org, 5, 0, 1));
	    choixFenetre.add(x, addConstraints(org, 6, 0, 1));    
	    choixFenetre.add(dimGrilleyx, addConstraints(org, 7, 0, 1));
	    
	    choixFenetre.add(lblChoixDimCellule, addConstraints(org, 8, 0, 1));
	    choixFenetre.add(dimCellule, addConstraints(org, 9, 0, 1));
	    
	    choixFenetre.add(buttonGo, addConstraints(org, 8, 1, 1));
	    
	    // Config
	    setSize(1100,100);
	    setLocationRelativeTo(null); // centre la fenêtre
	    setContentPane(choixFenetre);
	    setVisible(true); 
	    
	    
	    // Action
	    WindowListener ecouteur = new WindowAdapter() {
	         public void windowClosing(WindowEvent e){
	            System.exit(0);
	         }
	    };
	    addWindowListener(ecouteur);
	    
	    buttonGo.addActionListener(new ActionListener(){
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		String dimGrilley = dimGrilleyx.getText();
	    		String dimGrillex = dimGrillexy.getText();
	    		String dimCell = dimCellule.getText();
	    	
	    		if (dimGrilley.equals("") || dimGrillex.equals("") || dimCell.equals("") || dimCell.equals("")) {
	    			new GUIError("L'un des champs ne correspond pas");
	    			
	    		}else {
	    			System.out.println(comboBoxAuto.getSelectedItem().toString() + "      "+comboExt.getSelectedItem().toString() + "      "+
		    				dimGrilleyx.getText() +"      "+ dimGrillexy.getText() +"      "+ dimCellule.getText());
	    			//choixFenetre.setVisible(false);
	    			new GUIAutomaton((TypeAutomaton)comboBoxAuto.getSelectedItem(), (TypeExtension)comboExt.getSelectedItem() ,
	    					Integer.parseInt(dimGrillex), Integer.parseInt(dimGrilley), Integer.parseInt(dimCell));
	    			
	    		}  		
			}});
	}

}
