package view;

import javax.swing.SwingUtilities;
import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;
import view.WheelGameFrame;

public class GameEngineCallbackGUI implements GameEngineCallback 
{
	private WheelGameFrame wheelGameFrame;
	private GameEngine gameEngine;
	private ToolBarPanel toolBar;
	private WheelPanel wheelPanel;
	
	public GameEngineCallbackGUI(WheelGameFrame wheelGameFrame, GameEngine gameEngine) 
	{
		this.wheelGameFrame = wheelGameFrame;
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void nextSlot(Slot slot, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run()
			{
				// do GUI update on UI thread
				wheelGameFrame.getWheelPanel().updateBallPosition(slot);
			}		
		});
		
	}
	
	@Override
	public void result(Slot result, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run()
			{
				// do GUI update on UI thread
				wheelGameFrame.getWheelPanel().updateBallPosition(result);	
			}
			
		});
	
		for (SummaryPanel summaryPanel : wheelGameFrame.getPlayerSummaryPanels()) 
		{

            SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                	// do GUI update on UI thread
                    summaryPanel.betResults();
                }
            });
		}	
	}
}
