package p1;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final int LONGUEUR = 60;
	public static final int LARGEUR = 40;

	public JPanel mainPan;
	public JPanel pan;
	public JPanel pan2;

	public JTextField fieldProfondeur;
	public JTextField fieldLargeur;

	public Fenetre() {
		super();

		// création des panel
		mainPan = new JPanel();
		pan = new JPanel();
		pan2 = new JPanel();

		// création des éléments
		JLabel labelProfondeur = new JLabel("Profondeur :");
		JLabel labelLargeur = new JLabel("Largeur :");
		fieldProfondeur = new JTextField();
		fieldLargeur = new JTextField();

		KeyAdapter keyA = new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		};

		fieldProfondeur.addKeyListener(keyA);
		fieldLargeur.addKeyListener(keyA);

		JButton button = new JButton("Go !");

		// ajout dans le panel
		pan.setLayout(new GridLayout(1, 5));
		pan.add(labelProfondeur);
		pan.add(fieldProfondeur);
		pan.add(labelLargeur);
		pan.add(fieldLargeur);
		pan.add(button);
		pan.setMaximumSize(new Dimension(200, 600));

		// ajout des panels
		mainPan.add(pan);
		mainPan.add(pan2);

		this.getContentPane().add(mainPan);

		this.setTitle("Ztalloc");
		this.setSize(1600, 900);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Création du graph
				if ((!fieldProfondeur.getText().equals(""))
						&& (!fieldLargeur.getText().equals(""))) {
					pan2.removeAll();
					Zta z = new Zta(1);
					Utils.initialiser(z, 1,
							Integer.parseInt(fieldProfondeur.getText()),
							Integer.parseInt(fieldLargeur.getText()));
					mxGraph graph = generer(z);
					mxGraphComponent graphComponent = new mxGraphComponent(
							graph);

					new mxHierarchicalLayout(graph).execute(graph
							.getDefaultParent());
					new mxParallelEdgeLayout(graph).execute(graph
							.getDefaultParent());
					pan2.add(graphComponent);
					pan2.revalidate();
					pan2.repaint();
				} else {
					messageChampsVide();
				}
			}
		});

		this.setVisible(true);

	}

	private mxGraph generer(Zta z) {
		mxGraph graph = new mxGraph();

		graph.getModel().beginUpdate();

		remplir(graph, z, null);
		//remplirEnBinaire(graph, z, null);

		graph.getModel().endUpdate();

		return graph;

	}

	public void messageChampsVide() {
		JOptionPane.showMessageDialog(this,
				"Les  deux champs doivent être remplis", "Champ(s) vide",
				JOptionPane.WARNING_MESSAGE);
	}

	private mxGraph remplir(mxGraph graph, Zta zta, Object premier) {

		Object parent = graph.getDefaultParent();
		if (premier == null) {
			premier = creerVertex(parent, graph, zta);
		}

		for (Zta z : zta.getL()) {
			Object suivant = creerVertex(parent, graph, z);
			graph.insertEdge(parent, null, "", premier, suivant);
			remplir(graph, z, suivant);
		}

		return graph;
	}
	
	private Object creerVertex(Object parent, mxGraph graph, Zta z){
		if(z.getValeur() % 3 == 0){
			return graph.insertVertex(parent, null, valeurAffiche(z), 0, 0, LONGUEUR, LARGEUR, "fillColor=#7EA84E");
		} else{
			return graph.insertVertex(parent, null, valeurAffiche(z), 0, 0, LONGUEUR, LARGEUR);
		}
		
	}
	
	private String valeurAffiche(Zta z){
		
		return z.getValeur() + "\n" + Integer.toString(z.getValeur(), 2);
		
	}

}
