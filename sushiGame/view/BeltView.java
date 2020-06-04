package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401.sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
	private JLabel[] belt_labels;
	private BeltViewWidget widget;

	public BeltView(Belt b) {
		this.belt = b;
		this.belt_labels = new JLabel[belt.getSize()];
		this.widget = new BeltViewWidget(b);
		belt.registerBeltObserver(this);
		add(widget);
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		belt_labels = widget.refresh();
	}
}
