package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.WheelGameFrame;

public class AddPlayerListener implements ActionListener 
{
	private WheelGameFrame wheelGameFrame;
	private GameEngine gameEngine;
	private int playerID = 1;
	private JTextField playerName = new JTextField(15);
	private JTextField initialPoints = new JTextField(10);
	
	public AddPlayerListener(WheelGameFrame wheelGameFrame, GameEngine gameEngine) 
	{
		this.wheelGameFrame = wheelGameFrame;
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try 
		{
			JPanel playerInputPanel = new JPanel();
			
			playerInputPanel.add(new JLabel("Name: "));
			playerInputPanel.add(playerName);
			
			playerInputPanel.add(Box.createHorizontalStrut(15)); 
			
			playerInputPanel.add(new JLabel("Points: "));
			playerInputPanel.add(initialPoints);
			
			int playerResponses = JOptionPane.showConfirmDialog(null, playerInputPanel, "Add Player", JOptionPane.OK_CANCEL_OPTION);
			
			if(playerResponses == JOptionPane.CANCEL_OPTION) 
			{
				return;
			}
			else if(playerName.getText().equals("")) 
			{
				JOptionPane.showMessageDialog(wheelGameFrame, "Error: Invalid name entered");
			}
			else if(Integer.parseInt(initialPoints.getText()) == 0 || Integer.parseInt(initialPoints.getText()) < 0 || initialPoints.getText().equals(""))
			{
				JOptionPane.showMessageDialog(wheelGameFrame, "Error: Invalid points entered");
			}
			else 
			{
				SimplePlayer newPlayer = new SimplePlayer(Integer.toString(playerID), playerName.getText(), Integer.parseInt(initialPoints.getText()));
				gameEngine.addPlayer(newPlayer);
				
				// assign new player a unique ID
				playerID = playerID + 1;
				wheelGameFrame.getStatusBar().getNumberOfPlayers().setText("Number of Players: " + gameEngine.getAllPlayers().size());
				wheelGameFrame.updateVisiblePlayers();
			}
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(wheelGameFrame, "Error: Points must be an integer number");
		}	
	}
}
