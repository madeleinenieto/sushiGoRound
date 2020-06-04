package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private JComboBox selection;
	private JButton refreshBoard;
	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		String[] boards = new String[] {"Scoreboard", "Food Sold", "Food Spoiled"};
		
		refreshBoard = new JButton();
		refreshBoard.setActionCommand("Refresh");
		refreshBoard.addActionListener(this);
		
		selection = new JComboBox(boards);
		selection.setActionCommand("Board Change");
		selection.addActionListener(this);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());
		add(display, BorderLayout.CENTER);
		display.setText(makeScoreboardHTML());
		display.setText(makeConsumedBoardHTML());
		display.setText(makeSpoiledBoardHTML());

		JPanel upper = new JPanel();
		upper.add(selection);
		upper.setBackground(Color.GRAY);
		add(upper, BorderLayout.SOUTH);
	}

	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowBalanceComparator());
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
		}
		return sb_html;
	}
	
	private String makeConsumedBoardHTML() {
		String cb_html = "<html>";
		cb_html += "<h1>ConsumedBoard</h1>";

		// Create an array of all chefs and sort by food consumed.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowFoodConsumedComparator());
		
		for (Chef c : chefs) {
			cb_html += c.getName() + " ($" + Math.round(c.getFoodConsumed()*100.0)/100.0 + ") <br>";
		}
		return cb_html;
	}
	
	private String makeSpoiledBoardHTML() {
		String spb_html = "<html>";
		spb_html += "<h1>SpoiledBoard</h1>";

		// Create an array of all chefs and sort by food spoiled.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new LowToHighFoodSpoiledComparator());
		
		for (Chef c : chefs) {
			spb_html += c.getName() + " ($" + Math.round(c.getFoodSpoiled()*100.0)/100.0 + ") <br>";
		}
		return spb_html;
	}

	public void refresh() {
		switch ((String)selection.getSelectedItem()) {
			case "Scoreboard":
				display.setText(makeScoreboardHTML());
				break;
			case "Consumed Food":
				display.setText(makeConsumedBoardHTML());
				break;
			case "Spoiled Food":
				display.setText(makeSpoiledBoardHTML());
				break;
		}
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		refresh();
	}
}
