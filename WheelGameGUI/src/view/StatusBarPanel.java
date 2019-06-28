package view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import model.interfaces.GameEngine;

public class StatusBarPanel extends JPanel
{
	private final int rows = 1;
	private final int cols = 3;
	
	private JLabel gameLabel = new JLabel("Welcome to Wheel Game!", JLabel.CENTER);
	private JLabel playerStatusLabel = new JLabel("Player Status: No players yet", JLabel.CENTER);
	private JLabel numberOfPlayersLabel = new JLabel("Number of players: 0" , JLabel.CENTER);
	
	public StatusBarPanel(WheelGameFrame wheelGameFrame, GameEngine gameEngine)
	{
		this.setLayout(new GridLayout(rows, cols));
		
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		
		this.gameLabel.setBorder(blackBorder);
		this.playerStatusLabel.setBorder(blackBorder);
		this.numberOfPlayersLabel.setBorder(blackBorder);
			
		add(this.gameLabel);
		add(this.playerStatusLabel);
		add(this.numberOfPlayersLabel);
	}
	
	public JLabel getPlayerStatus()
	{
		return playerStatusLabel;
	}
	
	public JLabel getNumberOfPlayers()
	{
		return numberOfPlayersLabel;
	}
	
}
