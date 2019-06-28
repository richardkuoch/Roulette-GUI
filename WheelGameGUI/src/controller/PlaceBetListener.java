package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.WheelGameFrame;
import view.SummaryPanel;

public class PlaceBetListener implements ActionListener
{
    private WheelGameFrame wheelGameFrame;
    private GameEngine gameEngine;
    private SummaryPanel summaryPanel;
    private Player player;
    private JTextField betField;
    private JComboBox<BetType> betTypeField;

    public PlaceBetListener(WheelGameFrame wheelGameFrame, GameEngine gameEngine,SummaryPanel summaryPanel)
    {
        this.wheelGameFrame = wheelGameFrame;
        this.gameEngine = gameEngine;
        this.summaryPanel = summaryPanel;
        this.player = summaryPanel.getPlayer();
        this.betField = summaryPanel.getBetField();
        this.betTypeField = summaryPanel.getBetTypeField();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        int betAmount = Integer.parseInt(betField.getText());
        BetType betType = (BetType) betTypeField.getSelectedItem();

        // attempt to place bet, show error message if failed
        if (!gameEngine.placeBet(player, betAmount, betType))
        {
            JOptionPane.showMessageDialog(wheelGameFrame, "Unable to place bet "
                    + "for player with ID " + player.getPlayerId());
            return;
        }
        
        // update player summary panel with current bet placed info
        summaryPanel.getCurrentBet().setText("Bet placed for "
                + betAmount + " points on " + betType);
        
        // disable bet button until turn over
        summaryPanel.getBetButton().setEnabled(false);
        
         
        for(Player players: gameEngine.getAllPlayers())
        {
        	
        	// enable spin button if at least one player has successfully placed bet
        	if(player.getBet() > 0 || players.getBet() > 0) 
        	{
                // activate spin button
                wheelGameFrame.getToolBar().enableSpinButton();	
        	}
        	// disable spin button if player has not placed bet or bet invalid
        	else if(players.getBet() <= 0)
        	{
        		 // deactivate spin button
                wheelGameFrame.getToolBar().disableSpinButton();	
        	} 
        }
    }
}