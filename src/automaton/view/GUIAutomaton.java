package automaton.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import automaton.controller.AutomatonController;
import automaton.model.Grid;
import automaton.model.TypeAutomaton;
import automaton.model.TypeExtension;
import automaton.observer.Observer;

public class GUIAutomaton extends JFrame implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel automateFenetre;
	private JPanel automate;
	private GridBagConstraints gc;
	private JLabel titre;
	private JLabel generationLabel;
	private JLabel generation;
	private JLabel lblChoiceTimeT;
	private JTextField timeT;
	private JButton[][] gridJB;
	private JButton start;
	private JButton next;
	
	private Image life;
	private Image dead;
	private Image old;
	
	private Thread automatique;
	private int threadTime = 1000;
	private boolean threadActive = false;
	boolean run = true;
	
	private Grid grid;
	private AutomatonController controller;
	private int gen;
	
	public GUIAutomaton(TypeAutomaton typeA, TypeExtension typeE, int x, int y, int dimCell) {
		controller = new AutomatonController();
		controller.setAutomate(typeA, typeE, x, y);
		grid = controller.getGrid();
		grid.addObserver(this);
		gen = controller.getGeneration();
		setTitle(typeA.toString());
		
		
		
		
		//DESIGN
		gc = new GridBagConstraints();
		automateFenetre = new JPanel();  
		automateFenetre.setLayout(new GridBagLayout());
		automate = new JPanel();
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(3,1));
		automate.setLayout(new GridLayout(x, y));
		lblChoiceTimeT = new JLabel("  Temps d'actualisation : ");
		lblChoiceTimeT.setFont(new Font("Helvetica", Font.BOLD, 11));
	    timeT = new JTextField(Integer.toString(threadTime));
	    
		if (gridJB != null) {
			Component[] componentList = automate.getComponents();
			for (Component c : componentList) {
				if (c instanceof JButton)
					automate.remove(c);
			}
		}
		
		// Composant
		titre = new JLabel(typeA.toString());
		titre.setFont(new Font("Helvetica", Font.BOLD, 11));
		generationLabel = new JLabel("Génération : ");
		generationLabel.setFont(new Font("Helvetica", Font.BOLD, 11));
		generation = new JLabel("0");
		generation.setFont(new Font("Helvetica", Font.BOLD, 11));
		generation.setForeground(Color.RED);

		try {
			life = ImageIO.read(getClass().getResource("./life.png"));
			dead = ImageIO.read(getClass().getResource("./dead.png"));
			old = ImageIO.read(getClass().getResource("./old.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Bouton
		next = new JButton("Suivant");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawGrille(controller.nextStep());
			}
		});
		
		start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				threadTime = Integer.parseInt(timeT.getText());
				if (start.getText()=="Start") {
					if(threadActive==false) {
						automatique.start();
						threadActive=true;
					}else {
						run = !run;
					}
					start.setText("Pause");
				}else {
					run = !run;
					start.setText("Start");
				}
				
			}
		});
		
		// Automate
		gridJB = new JButton[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				
				JButton b = setBtn(i, j, dimCell, grid.getCells()[i][j].getState());
				automate.add(b);
				gridJB[i][j] = b;
			}
		}
			
				
	
		
		// Affectation / Mise en page
		gc.gridx = 1;
		gc.gridy = 1;
		automateFenetre.add(titre, gc);
		 
		gc.gridx = 3;
		gc.gridy = 1;
		automateFenetre.add(generationLabel, gc);
		
		gc.gridx = 4;
		gc.gridy = 1;
		automateFenetre.add(generation, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		automateFenetre.add(automate, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		automateFenetre.add(next, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		automateFenetre.add(start, gc);
		
		gc.gridx = 1;
		gc.gridy = 5;
		automateFenetre.add(timeT, gc);
	

		// Exit Programme 
		WindowListener ecouteur = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		};
		addWindowListener(ecouteur);
		
		// Config Fentre
		setSize(800, 700);
		setLocationRelativeTo(null); // centre la fentre
		setContentPane(automateFenetre);
		setVisible(true); 
		
		
		//Thread
		automatique = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!automatique.isInterrupted()) {
					if (run) {
						try {
							Thread.sleep((int) (threadTime));
							controller.nextStep();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}
			}
		});
	}


	

	
	/**
	 * Met à jour la grille
	 */
	@Override
	public void update() {
		drawGrille(controller.getGrid());
		
	}
	
	/**
	 * Défini le bouton : taille / etat
	 * @param i
	 * @param j
	 * @param dimCell
	 * @param s
	 * @return
	 */
	public JButton setBtn(int i, int j, int dimCell, String s) {
		JButton btn = new JButton();
		btn.setPreferredSize(new Dimension(dimCell, dimCell));
		btn.setName(i + " " + j);
		switch (s) {
		case "DEAD":
			btn.setIcon(new ImageIcon(dead));
			break;
		case "LIFE":
			btn.setIcon(new ImageIcon(life));
			break;
		case "OLD":
			btn.setIcon(new ImageIcon(old));
			break;
		default:
			btn.setIcon(new ImageIcon(dead));
		}
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = btn.getName();
				String[] split = str.split(" ");
				int a = Integer.parseInt(split[0]);
				int b = Integer.parseInt(split[1]);
				System.out.println(a + " " + b);
				drawGrille(controller.nextEtat(a, b));
			}
		});
		return btn;
	}

	
	/**
	 * Actualise la grille afficher (avec la grille recu)
	 * @param grid
	 */
	private void drawGrille(Grid grid) { 
		generation.setText(Integer.toString(controller.getGeneration()));
		for (int i = 0; i < grid.getHeight(); i++)
			for (int j = 0; j < grid.getWidth(); j++) {
				String etat = grid.getCells()[i][j].getState();
				switch (etat) {
				case "LIFE":
					gridJB[i][j].setIcon(new ImageIcon(life));
					break;
				case "DEAD":
					gridJB[i][j].setIcon(new ImageIcon(dead));
					break;
				case "OLD":
					gridJB[i][j].setIcon(new ImageIcon(old));
					break;
				}
			}
		
	}
	
	
}
