package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.WheelGameFrame;
import view.interfaces.GameEngineCallback;

public class NewGameMenuListener implements ActionListener
{
	private WheelGameFrame wheelGameFrame;
	private GameEngine gameEngine;
	
	public NewGameMenuListener(WheelGameFrame wheelGameFrame, GameEngine gameEngine) 
	{
		this.wheelGameFrame = wheelGameFrame;
		this.gameEngine = gameEngine;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				// dispose of old game
				wheelGameFrame.dispose();
				
				GameEngine gameEngine = new GameEngineImpl();
				WheelGameFrame wheelGameFrame = new WheelGameFrame(gameEngine);
				
				GameEngineCallback gameEngineCallbackGUI = new GameEngineCallbackGUI(wheelGameFrame, gameEngine);
				GameEngineCallback gameEngineCallback = new GameEngineCallbackImpl();
				
				gameEngine.addGameEngineCallback(gameEngineCallbackGUI);
				gameEngine.addGameEngineCallback(gameEngineCallback);
			}
		});
	}
}
