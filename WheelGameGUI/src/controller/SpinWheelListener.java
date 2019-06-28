package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.interfaces.GameEngine;
import view.WheelGameFrame;

public class SpinWheelListener implements ActionListener 
{
	private WheelGameFrame wheelGameFrame;
	private GameEngine gameEngine;
	
	public SpinWheelListener(WheelGameFrame wheelGameFrame, GameEngine gameEngine)
	{
		this.wheelGameFrame = wheelGameFrame;
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// as per assignment specification 
		final int INITIAL_DELAY = 1;
		final int FINAL_DELAY = 200;
		final int DELAY_INCREMENT = 4;
		
		new Thread()
		{
			@Override
			public void run()
			{
				try 
				{
					// disable add and remove player buttons during wheel spin
					wheelGameFrame.getToolBar().deactivateAddPlayerButton();
					wheelGameFrame.getToolBar().disableRemovePlayerButton();
					
					// call long running GameEngine methods on separate thread
					gameEngine.spin(INITIAL_DELAY, FINAL_DELAY, DELAY_INCREMENT);
					
					// enable add and remove player buttons after wheel spin
					wheelGameFrame.getToolBar().enableAddPlayerButton();
					wheelGameFrame.getToolBar().enableRemovePlayerButton();
					// disable spin button after wheel spin
					wheelGameFrame.getToolBar().disableSpinButton();	
				}catch(IllegalArgumentException iae)
				{
					JOptionPane.showMessageDialog(wheelGameFrame, iae.getMessage(), "Wheel Spin Error", JOptionPane.ERROR_MESSAGE);
					wheelGameFrame.getToolBar().showSpinButton();
				}
			}
		}.start();
	}

}
