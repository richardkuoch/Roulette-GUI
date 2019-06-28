package view;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import controller.AddPlayerListener;
import controller.RemovePlayerListener;
import controller.SpinWheelListener;

public class ToolBarPanel extends JToolBar
{
	private WheelGameFrame wheelGameFrame;
	private GameEngine gameEngine;
	private JButton addPlayerButton;
	private JButton removePlayerButton;
	private JButton spinButton;
		
	public ToolBarPanel(WheelGameFrame wheelGameFrame, GameEngine gameEngine)
	{
		this.setLayout(new FlowLayout());
		
		AddPlayerListener addPlayer = new AddPlayerListener(wheelGameFrame, gameEngine);
		addPlayerButton = new JButton("Add Player");
		addPlayerButton.addActionListener(addPlayer);
		add(addPlayerButton);
		
		RemovePlayerListener removePlayer = new RemovePlayerListener(wheelGameFrame, gameEngine);
		removePlayerButton = new JButton("Remove Player");
		removePlayerButton.addActionListener(removePlayer);
		add(removePlayerButton);
		
		SpinWheelListener spinWheel = new SpinWheelListener(wheelGameFrame, gameEngine);
		spinButton = new JButton("Spin");
		spinButton.addActionListener(spinWheel);
		add(spinButton);
		//disable spin button a player has placed a bet
		disableSpinButton(); 
	}
			
	public JButton getAddPlayerButton() 
	{
		return addPlayerButton;
	}
	
	// enable add player JButton
    public void enableAddPlayerButton()
    {
        addPlayerButton.setEnabled(true);
    }
	
	// disable add player JButton
    public void deactivateAddPlayerButton()
    {
        addPlayerButton.setEnabled(false);
        addPlayerButton.setToolTipText("Cannot add player while wheel is spinning!");
    }
    
	public JButton getRemovePlayerButton()
	{
		return removePlayerButton;
	}
	
	// disable remove player JButton
    public void disableRemovePlayerButton()
    {
        removePlayerButton.setEnabled(false);
        removePlayerButton.setToolTipText("Cannot remove player while wheel is spinning!");
    }
    
	// enable remove player JButton
    public void enableRemovePlayerButton()
    {
        removePlayerButton.setEnabled(true);
    }
    
    public JButton getSpinButton()
    {
    	return spinButton;
    }
	
	// display spin JButton
    public void showSpinButton()
    {
        this.add(spinButton);
        this.revalidate();
    }
    
    // enable spin JButton
    public void enableSpinButton()
    {
        spinButton.setEnabled(true);
    }

    // disable spin JButton
    public void disableSpinButton()
    {
        spinButton.setEnabled(false);
        spinButton.setToolTipText("Cannot spin wheel yet! A player must place a bet first!");
    } 
    
    // automatic spin 
    public void automaticSpin()
    {
    	for(Player player: gameEngine.getAllPlayers())
    	{
    		if(player.getBet() <= 0 && gameEngine.getAllPlayers().size() >= 1)
    		{
    			return;
    		}
    	}
    	spinButton.doClick();
    }
}
