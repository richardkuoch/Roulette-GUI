package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.interfaces.GameEngine;
import view.WheelGameFrame;

public class ExitMenuListener implements ActionListener
{
	private WheelGameFrame wheelGameFrame;
	private GameEngine gameEngine;
	
	public ExitMenuListener(WheelGameFrame wheelGameFrame, GameEngine gameEngine) 
	{
		this.wheelGameFrame = wheelGameFrame;
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.exit(0);
	}
}
