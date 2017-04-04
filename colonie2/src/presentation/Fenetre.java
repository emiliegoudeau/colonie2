package presentation;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ad.DAOException;
import entite.Atelier;
import entite.Enfant;
import metier.AtelierServiceImpl;


public class Fenetre extends JFrame {

	public Fenetre() {
		Container panneau = this.getContentPane();
	    panneau.setLayout (new GridLayout (3,3));
	    
	    JButton addEnfant = new JButton("Ajouter Enfant");
	    JButton addAtelier = new JButton("Ajouter Atelier");
	    JButton listEnfant = new JButton("Liste Enfant");
	    JButton listAtelier = new JButton("Liste Atelier");
	    JButton gererAtelier = new JButton("Gestion des Ateliers");
	    
	    
	    panneau.add(addEnfant);
	    panneau.add(addAtelier);
	    panneau.add(listEnfant);
	    panneau.add(listAtelier);
	    panneau.add(gererAtelier);

	    
	    addEnfant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					fenetreAddEnfant();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	    });
	    
	    addAtelier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetreAddAtelier();	
			}	
	    });
	    
	    listEnfant.addActionListener(new ActionListener() {
	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Enfant> liste= new ArrayList<Enfant>();
				try {
					liste = AtelierServiceImpl.getInstance().getListEnfant();
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fenetreListEnfant(liste);
			}	
	    });	
	    
	    
	    listAtelier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Atelier> lista= new ArrayList<Atelier>();
				try {
					lista = AtelierServiceImpl.getInstance().getListAtelier();
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fenetreListAtelier(lista);
			}
	    });	 
	    
	    
	    this.setTitle("Colonie EMM");
		this.setSize(700, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	
	/* *************************************************** */
	private Component fermerfenetre(JFrame f) {
		JButton fermer = new JButton("Fermer");
		
		fermer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					f.setVisible(false);	
					f.dispose();
			}	
	    });
		return fermer;
	}
	
	
	
	/* ****************** METHODES POUR AJOUTER ******************** */
	
	
	// Methode pour afficher la fenetre d'ajout d'un enfant
	private void fenetreAddEnfant() throws ParseException{
		JFrame f = new JFrame();  //Remplacer JFrame par ta nouvelle fenetre
		f.setSize(700, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		Container pAddEnfant = f.getContentPane();
		pAddEnfant.setLayout (new GridLayout (4,4,1,1));
		
		JTextField nom = new JTextField();
		JTextField prenom = new JTextField();
		JTextField date = new JTextField();
		JButton ajouter = new JButton("Ajouter");
		
		f.add(new JLabel("nom"));
		f.add(nom);
		f.add(new JLabel("prenom"));
		f.add(prenom);
		f.add(new JLabel("date (jj/mm/aaaa)"));
		f.add(date);
		f.add(ajouter);
		f.add(fermerfenetre(f));
		
		
		ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				 //Appel a la methode pour ajouter un enfant a la base de données
				try {

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date ddn = sdf.parse(date.getText());
					Enfant enfant = new Enfant(nom.getText(),prenom.getText(),ddn);
					AtelierServiceImpl.getInstance().ajoutEnfantBdd(enfant);
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
	    });	
		f.setVisible(true);
	}
	



	// Methode pour afficher la fenetre d'ajout d'un atelier
	private void fenetreAddAtelier() {
		JFrame f = new JFrame();  //Remplacer JFrame par ta nouvelle fenetre
		f.setSize(700, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		Container pAddAtelier = f.getContentPane();
		pAddAtelier.setLayout (new GridLayout (5,5));
		
		JTextField nom = new JTextField();
		JTextField ageMini = new JTextField();
		JTextField ageMax = new JTextField();
		JTextField nbPlaces = new JTextField();
		JButton ajouter = new JButton("Ajouter");
		
		f.add(new JLabel("nom"));
		f.add(nom);
		f.add(new JLabel("ageMini"));
		f.add(ageMini);
		f.add(new JLabel("ageMax"));
		f.add(ageMax);
		f.add(new JLabel("Nombres de places"));
		f.add(nbPlaces);
		f.add(ajouter);
		f.add(fermerfenetre(f));
		
		ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer a = Integer.parseInt(ageMini.getText());
				Integer b = Integer.parseInt(ageMax.getText());
				Integer c = Integer.parseInt(nbPlaces.getText());
				
				// Appel a la methode pour ajouter un atelier a la base de données
			}	
	    });	
		
		f.setVisible(true);
		
	}
	
	
	
	/* ************** METHODES D AFFICHAGE DES LISTES ******************* */
	
	
	// Methode pour afficher une fenetre avec la liste des enfants
	private void fenetreListEnfant(List<Enfant> liste) {
		JFrame f = new JFrame();  //Remplacer JFrame par ta nouvelle fenetre
		f.setSize(700, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		Container pAddAtelier = f.getContentPane();
		pAddAtelier.setLayout (new GridLayout (0,1));
		
		for (Enfant enfant : liste) {
			
			JLabel lab = new JLabel("");
			lab.setHorizontalAlignment(SwingConstants.CENTER);
			f.add(lab);
			lab.setText(enfant.getNom()+" "+enfant.getPrenom()+" né le : "+enfant.getDdn()+" ");
		}
		
		f.add(fermerfenetre(f));
		f.setVisible(true);
		
	}
	
	// Methode pour afficher une fenetre avec la liste des ateliers
	private void fenetreListAtelier(List<Atelier> lista) {
		JFrame f = new JFrame();  //Remplacer JFrame par ta nouvelle fenetre
		f.setSize(700, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		Container pAddAtelier = f.getContentPane();
		pAddAtelier.setLayout (new GridLayout (0,1));
		
		for (Atelier atelier : lista) {			
			JLabel lab = new JLabel(atelier.getNom());
			lab.setHorizontalAlignment(SwingConstants.CENTER);
			f.add(lab);
			lab.setText("Pour l'atelier "+atelier.getNom()+", il faut entre"+atelier.getAgeMini()+" et "+atelier.getAgeMaxi()+". Nombres de places : "+atelier.getNbPlace());		
		}
		
		f.add(fermerfenetre(f));
		
		f.setVisible(true);
	}
	
}
