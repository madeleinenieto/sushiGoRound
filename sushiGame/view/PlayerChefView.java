package sushigame.view;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import comp401.sushi.*;

public class PlayerChefView extends JPanel implements ActionListener {
	private List<ChefViewListener> listeners;
	private int belt_size;
	private Sushi sushi;
	private JComboBox plateColor;
	private JComboBox platePosition;
	private JSlider goldSlider;
	private JSlider avocadoSlider;
	private JSlider crabSlider;
	private JSlider eelSlider;
	private JSlider riceSlider;
	private JSlider salmonSlider;
	private JSlider seaweedSlider;
	private JSlider shrimpSlider;
	private JSlider tunaSlider;
	private JTextField rollText;
	private JButton rollButton;
	private JComboBox nigiriIngredients;
	private JButton makeNigiri;
	private JComboBox sashimiIngredients;
	private JButton makeSashimi;
	
	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
				
		add(new JLabel("Color:"));
		plateColor = new JComboBox(new String[]{"Red", "Green", "Blue", "Gold"});
		plateColor.addActionListener(this);
		add(plateColor);
		
		add(new JLabel("Position:"));
		platePosition = new JComboBox();
		for (int i = 0; i < 20; i++) {
			platePosition.addItem(i);
		} 
		add(platePosition);
		
		// the gold plates prices are specified
		Hashtable goldPrice = new Hashtable();
		for (int j = 5; j <= 10; j++) {
			goldPrice.put((j * 10), new JLabel(Integer.toString(j)));
		}
		
		add(new JLabel("Gold Plate Price ($):"));
		goldSlider = new JSlider(JSlider.HORIZONTAL, 50, 100, 50);
		goldSlider.setPaintTicks(true);
		goldSlider.setPaintLabels(true);
		goldSlider.setLabelTable(goldPrice);
		goldSlider.setMajorTickSpacing(10);
		goldSlider.setMinorTickSpacing(5);
		add(goldSlider);	

		// any type of sushi roll is custom made
		Hashtable ingredientLabel = new Hashtable();
		for (int k = 0; k <= 3; k++) {
			ingredientLabel.put((k * 50), new JLabel(Double.toString(((double) k) * 0.5)));
		}

		avocadoSlider = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		avocadoSlider.setPaintTicks(true);
		avocadoSlider.setPaintLabels(true);
		avocadoSlider.setMajorTickSpacing(50);
		avocadoSlider.setMinorTickSpacing(10);
		avocadoSlider.setLabelTable(ingredientLabel);
		add(new JLabel("Avocado (oz):"));
		add(avocadoSlider);
		
		riceSlider = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		riceSlider.setPaintTicks(true);
		riceSlider.setPaintLabels(true);
		riceSlider.setMajorTickSpacing(50);
		riceSlider.setMinorTickSpacing(10);
		riceSlider.setLabelTable(ingredientLabel);
		add(new JLabel("Rice (oz):"));
		add(riceSlider);
		
		seaweedSlider = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		seaweedSlider.setPaintTicks(true);
		seaweedSlider.setPaintLabels(true);
		seaweedSlider.setMajorTickSpacing(50);
		seaweedSlider.setMinorTickSpacing(10);
		seaweedSlider.setLabelTable(ingredientLabel);
		add(new JLabel("Seaweed (oz):"));
		add(seaweedSlider);

		eelSlider = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		eelSlider.setPaintTicks(true);
		eelSlider.setPaintLabels(true);
		eelSlider.setMajorTickSpacing(50);
		eelSlider.setMinorTickSpacing(10);
		eelSlider.setLabelTable(ingredientLabel);
		add(new JLabel("Eel (oz):"));
		add(eelSlider);
		
		crabSlider = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		crabSlider.setPaintTicks(true);
		crabSlider.setPaintLabels(true);
		crabSlider.setMajorTickSpacing(50);
		crabSlider.setMinorTickSpacing(10);
		crabSlider.setLabelTable(ingredientLabel);
		add(new JLabel("Crab (oz):"));
		add(crabSlider);

		shrimpSlider = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		shrimpSlider.setPaintTicks(true);
		shrimpSlider.setPaintLabels(true);
		shrimpSlider.setMajorTickSpacing(50);
		shrimpSlider.setMinorTickSpacing(10);
		shrimpSlider.setLabelTable(ingredientLabel);
		add(new JLabel("Shrimp (oz):"));
		add(shrimpSlider);

		salmonSlider = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		salmonSlider.setPaintTicks(true);
		salmonSlider.setPaintLabels(true);
		salmonSlider.setMajorTickSpacing(50);
		salmonSlider.setMinorTickSpacing(10);		
		salmonSlider.setLabelTable(ingredientLabel);
		add(new JLabel("Salmon (oz):"));
		add(salmonSlider);

		tunaSlider = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		tunaSlider.setPaintTicks(true);
		tunaSlider.setPaintLabels(true);
		tunaSlider.setMajorTickSpacing(50);
		tunaSlider.setMinorTickSpacing(10);
		tunaSlider.setLabelTable(ingredientLabel);
		add(new JLabel("Tuna (oz):"));
		add(tunaSlider);
		
		rollText = new JTextField();
		add(new JLabel("Roll Name:"));
		add(rollText);

		rollButton = new JButton("Make Roll");
		rollButton.setActionCommand("roll");
		rollButton.addActionListener(this);
		add(rollButton);	

		// sashimi is custom made
		add(new JLabel("Type of Nigiri:"));
		
		nigiriIngredients = new JComboBox(new String[]{"Eel", "Shrimp", "Crab", "Salmon", "Tuna"});
		nigiriIngredients.addActionListener(this);
		add(nigiriIngredients);

		makeNigiri = new JButton("Make Nigiri");
		makeNigiri.setActionCommand("nigiri");
		makeNigiri.addActionListener(this);
		add(makeNigiri);

		// sashimi is custom made
		add(new JLabel("Type of Sashimi:"));

		sashimiIngredients = new JComboBox(new String[]{"Eel", "Shrimp", "Crab", "Salmon", "Tuna"});
		sashimiIngredients.addActionListener(this);
		add(sashimiIngredients);

		makeSashimi = new JButton("Make Sashimi");
		makeSashimi.setActionCommand("sashimi");
		makeSashimi.addActionListener(this);
		add(makeSashimi);
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi sushi, int platePosition) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(sushi, platePosition);
		}
	}

	private void makeGreenPlateRequest(Sushi sushi, int platePosition) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(sushi, platePosition);
		}
	}

	private void makeBluePlateRequest(Sushi sushi, int platePosition) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(sushi, platePosition);
		}
	}
	
	private void makeGoldPlateRequest(Sushi sushi, int platePosition, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(sushi, platePosition, price);
		}
	}
	
	private void makePlateRequest() {
		switch (plateColor.getSelectedIndex()) {
		case 0:
			makeRedPlateRequest(sushi, platePosition.getSelectedIndex());
			break;
		case 1:
			makeGreenPlateRequest(sushi, platePosition.getSelectedIndex());
			break;
		case 2:
			makeBluePlateRequest(sushi, platePosition.getSelectedIndex());
			break;
		case 3:
			makeGoldPlateRequest(sushi, platePosition.getSelectedIndex(), goldSlider.getValue() / 10.0);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		
		case "roll":
			
			String rollTextInfo = rollText.getText();
			if (rollTextInfo == null) {
				rollTextInfo = "Player Roll";
			}
			
			ArrayList<IngredientPortion> ingredients = new ArrayList<IngredientPortion>();
			
			if (avocadoSlider.getValue() / 100.0 > 0) {
				ingredients.add(new AvocadoPortion(avocadoSlider.getValue() / 100.0));
			}
			if (riceSlider.getValue() / 100.0 > 0) {
				ingredients.add(new RicePortion(riceSlider.getValue() / 100.0));
			}
			if (seaweedSlider.getValue() / 100.0 > 0) {
				ingredients.add(new SeaweedPortion(seaweedSlider.getValue() / 100.0));
			}
			if (eelSlider.getValue() / 100.0 > 0) {
				ingredients.add(new EelPortion(eelSlider.getValue() / 100.0));
			}
			if (crabSlider.getValue() / 100.0 > 0) {
				ingredients.add(new CrabPortion(crabSlider.getValue() / 100.0));
			}
			if (shrimpSlider.getValue() / 100.0 > 0) {
				ingredients.add(new ShrimpPortion(shrimpSlider.getValue() / 100.0));
			}
			if (salmonSlider.getValue() / 100.0 > 0) {
				ingredients.add(new SalmonPortion(salmonSlider.getValue() / 100.0));
			}
			if (tunaSlider.getValue() / 100.0 > 0) {
				ingredients.add(new TunaPortion(tunaSlider.getValue() / 100.0));
			}
			
			int a = ingredients.size();
			IngredientPortion[] finalIngredients = new IngredientPortion[a];
			for (int i = 0; i < a; i++) {
				finalIngredients[i] = ingredients.get(i);
			}
			
			if (finalIngredients.length == 0) {
				break;
			}

			sushi = new Roll(rollTextInfo, finalIngredients);
			makePlateRequest();
	
			break;
			
		
		case "nigiri":
			switch (nigiriIngredients.getSelectedIndex()) {
			case 0:
				sushi = new Nigiri(Nigiri.NigiriType.EEL);
				makePlateRequest();
				break;
			case 1:
				sushi = new Nigiri(Nigiri.NigiriType.SHRIMP);
				makePlateRequest();
				break;
			case 2:
				sushi = new Nigiri(Nigiri.NigiriType.CRAB);
				makePlateRequest();
				break;
			case 3:
				sushi = new Nigiri(Nigiri.NigiriType.SALMON);
				makePlateRequest();
				break;
			case 4:
				sushi = new Nigiri(Nigiri.NigiriType.TUNA);	
				makePlateRequest();
				break;
			}
			break;
		
		
		case "sashimi":
			switch(sashimiIngredients.getSelectedIndex()) {
			case 0:
				sushi = new Sashimi(Sashimi.SashimiType.EEL);
				makePlateRequest();
				break;
			case 1:
				sushi = new Sashimi(Sashimi.SashimiType.SHRIMP);
				makePlateRequest();
				break;
			case 2:
				sushi = new Sashimi(Sashimi.SashimiType.CRAB);
				makePlateRequest();
				break;
			case 3:
				sushi = new Sashimi(Sashimi.SashimiType.SALMON);
				makePlateRequest();
				break;
			case 4:
				sushi = new Sashimi(Sashimi.SashimiType.TUNA);
				makePlateRequest();
				break;
			}
			break;
		}
	}
}
