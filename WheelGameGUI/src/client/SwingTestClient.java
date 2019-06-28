package client;

import javax.swing.SwingUtilities;
import model.interfaces.GameEngine;
import model.GameEngineImpl;
import view.WheelGameFrame;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

public class SwingTestClient {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
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
