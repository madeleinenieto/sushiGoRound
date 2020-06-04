package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401.sushi.*;
import sushigame.model.Belt;

public class BeltViewWidget extends JPanel {
	private Belt belt;
	private JLabel[] belt_labels;
	
	public BeltViewWidget(Belt belt) {
		this.belt = belt;
		this.belt_labels = new JLabel[belt.getSize()];
		setLayout(new GridLayout(belt.getSize(), 2));
		for (int i = 0; i < belt.getSize(); i++) {
			JLabel plateLabel = new JLabel("");
			plateLabel.setMinimumSize(new Dimension(600, 60));
			plateLabel.setPreferredSize(new Dimension(600, 60));
			plateLabel.setOpaque(true);
			plateLabel.setBackground(Color.GRAY);
			add(plateLabel);
			belt_labels[i] = plateLabel;
		}
		refresh();
	}
	
	public JLabel[] refresh() {
		for (int i = 0; i < belt.getSize(); i++) {
			Plate plate = belt.getPlateAtPosition(i);
			JLabel plateLabel = belt_labels[i];
			
			if (plate == null) {
				plateLabel.setText("");
				plateLabel.setBackground(Color.GRAY);
			} else {
				String rollInfo = "";
				Sushi contents = plate.getContents();
				IngredientPortion[] ingredients = plate.getContents().getIngredients();
				String[] ingredientName = new String[ingredients.length];
				double[] ingredientAmount = new double[ingredients.length];
				
				for (int j = 0; j < ingredients.length; j++) {
					if (plate != null) {
						if (contents.getName().contains("nigiri") || contents.getName().contains("sashimi")) {
							rollInfo = ",";
						} else {
							ingredientName[j] = ingredients[j].getName();
							ingredientAmount[j] += ((int)((ingredients[j].getAmount() * 100) + 0.5)) / 100.0;
							rollInfo += ingredientAmount[j] + " oz of " + ingredientName[j] + ", ";
						}
					}
				}
				
				if (contents.getName().contains("nigiri") || contents.getName().contains("sashimi")) {
					plateLabel.setText("<html>" + "Roll Info: " + contents.getName() + rollInfo + 
							" Chef: " + belt.getPlateAtPosition(i).getChef().getName() + 
							", Age of Plate: " + belt.getAgeOfPlateAtPosition(i) + "</html>");
				} else {
					plateLabel.setText("<html>" + "Roll Info: " + contents.getName() + ", Ingredients: " + rollInfo + 
							" Chef: " + belt.getPlateAtPosition(i).getChef().getName() + 
							", Age of Plate: " + belt.getAgeOfPlateAtPosition(i) + "</html>");
				}
				
				switch (plate.getColor()) {
				case RED:
					plateLabel.setBackground(Color.RED); break;
				case GREEN:
					plateLabel.setBackground(Color.GREEN); break;
				case BLUE:
					plateLabel.setBackground(Color.BLUE); break;
				case GOLD:
					plateLabel.setBackground(Color.YELLOW); break;
				}
			}
		}
		return belt_labels;
	}
}
