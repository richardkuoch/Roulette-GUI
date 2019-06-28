package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.WheelGameFrame;

public class RemovePlayerListener implements ActionListener
{
    private WheelGameFrame wheelGameFrame;
    private GameEngine gameEngine;

    public RemovePlayerListener(WheelGameFrame wheelGameFrame, GameEngine gameEngine)
    {
        this.wheelGameFrame = wheelGameFrame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String playerId = JOptionPane.showInputDialog(wheelGameFrame, "Enter player ID:", "Remove Player", JOptionPane.QUESTION_MESSAGE);

        if (playerId == null)
        {
            return;
        }

        Player player = gameEngine.getPlayer(playerId);
        while (player == null)
        {
            playerId = JOptionPane.showInputDialog(wheelGameFrame, "No player with that ID. Enter player ID:", "Remove Player",
                    JOptionPane.QUESTION_MESSAGE);

            if (playerId == null)
            {
                return;
            }

            player = gameEngine.getPlayer(playerId);
        }

        // remove player from game engine
        gameEngine.removePlayer(player);
        
        // update status bar for number of players
        wheelGameFrame.getStatusBar().getNumberOfPlayers().setText("Number of Players: " + gameEngine.getAllPlayers().size());
        // update visible players on GUI
        wheelGameFrame.updateVisiblePlayers();
        
        // no players in the game then deactivate spin button
        if(gameEngine.getAllPlayers().isEmpty()) 
        {
            wheelGameFrame.getToolBar().disableSpinButton();	
        }
        
        for(Player players: gameEngine.getAllPlayers())
        {
        	if(players.getBet() <= 0)
        	{
        		wheelGameFrame.getToolBar().disableSpinButton();	
        	}
        }
    }
}