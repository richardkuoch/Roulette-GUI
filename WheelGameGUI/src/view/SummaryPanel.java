package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.enumeration.BetType;
import controller.PlaceBetListener;

public class SummaryPanel extends JPanel
{
    private WheelGameFrame wheelGameFrame;
    private GameEngine gameEngine;
    private Player player;
    private JLabel nameLabel;
    private JLabel nameField;
    
    private JLabel pointsLabel;
    private JLabel pointsField;
    private JLabel betTypeLabel;
    private JComboBox<BetType> betTypeField;
    private JLabel betLabel;
    private JTextField betField;
    private JLabel currentBetLabel;
    private JLabel currentBetField;
    
    private JButton placeBet;
    private JLabel betInfo;

    public SummaryPanel(WheelGameFrame wheelGameFrame, GameEngine gameEngine, Player player)
    {
    	this.setLayout(new GridBagLayout());
    	this.wheelGameFrame = wheelGameFrame;
        this.gameEngine = gameEngine;
        this.player = player;

        this.setBorder(BorderFactory.createTitledBorder("Player ID: " + player.getPlayerId()));

        nameLabel = new JLabel("Name: ", JLabel.TRAILING);
        nameField = new JLabel(player.getPlayerName());

        pointsLabel = new JLabel("Points: ", SwingConstants.TRAILING);
        pointsField = new JLabel(Integer.toString(player.getPoints()));

        betTypeLabel = new JLabel("Bet Type: ", SwingConstants.TRAILING);
        betTypeField = new JComboBox<>(BetType.values());
       
        currentBetLabel = new JLabel("Current Bet: ", JLabel.TRAILING);
        
        betLabel = new JLabel("Bet amount: ", SwingConstants.TRAILING);
        betField = new JTextField("50", 3);

        nameLabel.setLabelFor(nameField);
        pointsLabel.setLabelFor(pointsField);
        betTypeLabel.setLabelFor(betTypeField);
        betLabel.setLabelFor(betField);
        currentBetLabel.setLabelFor(currentBetField);
        
        placeBet = new JButton("Place bet");
        placeBet.addActionListener(new PlaceBetListener(this.wheelGameFrame, this.gameEngine, this));

        currentBetField = new JLabel("");
        betInfo = new JLabel("");

        if(this.player.getBet() > 0) 
        {
        	wheelGameFrame.getToolBar().enableSpinButton();
        }
        
        // create layout constraints
        GridBagConstraints labelLayout = new GridBagConstraints();
        labelLayout.gridx = 0;
        labelLayout.gridy = 0;
        labelLayout.anchor = GridBagConstraints.LINE_END;
        labelLayout.fill = GridBagConstraints.BOTH;
       
        GridBagConstraints fieldLayout = new GridBagConstraints();
        fieldLayout.gridx = 1;
        fieldLayout.gridy = 0;
        fieldLayout.anchor = GridBagConstraints.LINE_START;
        fieldLayout.fill = GridBagConstraints.BOTH;
        
        GridBagConstraints buttonLayout = new GridBagConstraints();
        buttonLayout.gridwidth = 2;
        buttonLayout.anchor = GridBagConstraints.PAGE_END;
        GridBagConstraints betInfoLayout = buttonLayout;

        // add components to the panel with specified constraints
        this.add(nameLabel, labelLayout);
        this.add(nameField, fieldLayout);
        labelLayout.gridy++;
        fieldLayout.gridy++;
        
        this.add(pointsLabel, labelLayout);
        this.add(pointsField, fieldLayout);
        labelLayout.gridy++;
        fieldLayout.gridy++;
        
        this.add(betTypeLabel, labelLayout);
        this.add(betTypeField, fieldLayout);
        labelLayout.gridy++;
        fieldLayout.gridy++;
        
        this.add(betLabel, labelLayout);
        this.add(betField, fieldLayout);
        labelLayout.gridy++;
        fieldLayout.gridy++;
        
        this.add(currentBetLabel, labelLayout);
        this.add(currentBetField, fieldLayout);
        labelLayout.gridy++;
        fieldLayout.gridy++;
        
        buttonLayout.gridy = labelLayout.gridy + 1;
        this.add(placeBet, buttonLayout);
        betInfoLayout.gridy = buttonLayout.gridy + 1;
        this.add(betInfo, betInfoLayout);
    }

    // update bet results for all players after spin has been completed
    public void betResults()
    {
        // set player fields to updated values
        nameField.setText(player.getPlayerName());
        int previousPoints = Integer.parseInt(pointsField.getText());
        int currentPoints = player.getPoints();
        pointsField.setText(Integer.toString(currentPoints));

        int updatedPoints = currentPoints - previousPoints;

        // update bet info field
        if (updatedPoints > 0)
        {
            betInfo.setText("Result: Bet won! " + updatedPoints + " points added.");
        }
        else if (updatedPoints < 0)
        {
            betInfo.setText("Result: Bet lost. " + Math.abs(updatedPoints) + " points removed.");
        }
        else
        {
            betInfo.setText("");
        }

        placeBet.setEnabled(true);
        wheelGameFrame.revalidate();
    }

    // return player object
    public Player getPlayer()
    {
        return player;
    }

    // return bet field
    public JTextField getBetField()
    {
        return betField;
    }

    // return bet type field
    public JComboBox<BetType> getBetTypeField()
    {
        return betTypeField;
    }

    // return place bet button
    public JButton getBetButton()
    {
        return placeBet;
    }
  
    // return current bet placed
    public JLabel getCurrentBet()
    {
        return currentBetField;
    }
}