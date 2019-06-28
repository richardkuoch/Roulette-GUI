package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import model.interfaces.GameEngine;
import controller.NewGameMenuListener;
import controller.ExitMenuListener;
import controller.RemovePlayerListener;

public class MenuBarPanel extends JMenuBar 
{
	private JMenu file;
	private JMenu playerOptions;
	private JMenuItem newGame;
	private JMenuItem exit;
	private JMenuItem removePlayer;

	public MenuBarPanel(WheelGameFrame wheelGameFrame,GameEngine gameEngine) 
	{
		// create menu option called File
		file = new JMenu("File");
		add(file);
		file.addSeparator();
		
		// new game added to file option
		NewGameMenuListener newGameMenuListener = new NewGameMenuListener(wheelGameFrame, gameEngine);
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(newGameMenuListener);
		
		// exit game added to file option
		ExitMenuListener exitMenuListener = new ExitMenuListener(wheelGameFrame, gameEngine);
		exit = new JMenuItem("Exit");
		exit.addActionListener(exitMenuListener);
		
		file.add(newGame);
		file.add(exit);
		
		// create menu option called Player Options
		playerOptions = new JMenu("Player Options");
		add(playerOptions);
		playerOptions.addSeparator();
	
		// add remove player to Player Options
		RemovePlayerListener removePlayerListener = new RemovePlayerListener(wheelGameFrame, gameEngine);
		removePlayer = new JMenuItem("Remove Player");
		removePlayer.addActionListener(removePlayerListener);
				
		playerOptions.add(removePlayer);	
	}

}
