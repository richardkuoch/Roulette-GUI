package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class WheelGameFrame extends JFrame
{
    private GameEngine gameEngine;
    private MenuBarPanel menuBar;
    private ToolBarPanel toolBar;
    private StatusBarPanel statusBar;
    private JPanel topPanelContainer;
    private JPanel playerSummaryPanelsContainer;
    private Map<Player, SummaryPanel> playerSummaryPanels;
    private Collection<Player> visiblePlayers;
    private WheelPanel wheelPanel;

    public WheelGameFrame(GameEngine gameEngine)
    {
        super("Casino Style Gaming Wheel");

        this.gameEngine = gameEngine;

        this.menuBar = new MenuBarPanel(this, gameEngine);
        this.setJMenuBar(this.menuBar);

        this.toolBar = new ToolBarPanel(this, gameEngine);
      
        this.playerSummaryPanelsContainer = new JPanel();
        this.playerSummaryPanels = new HashMap<>();
        this.visiblePlayers = new ArrayList<Player>();
        
        this.topPanelContainer = new JPanel();
        this.topPanelContainer.setLayout(new BorderLayout());
        this.topPanelContainer.add(this.toolBar, BorderLayout.NORTH);
        this.topPanelContainer.add(this.playerSummaryPanelsContainer, BorderLayout.SOUTH);
        this.add(this.topPanelContainer, BorderLayout.NORTH);
                
        this.wheelPanel = new WheelPanel(this, gameEngine);
        this.add(wheelPanel, BorderLayout.CENTER);

        this.statusBar = new StatusBarPanel(this, gameEngine);
        this.add(statusBar, BorderLayout.SOUTH);

        this.setMinimumSize(new Dimension(720, 720));
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // update GUI to display added and removed players
    public void updateVisiblePlayers()
    {
        // add players
        for (Player player : this.gameEngine.getAllPlayers())
        {
            if (!this.visiblePlayers.contains(player))
            {
                addPlayerPanel(player);
                statusBar.getPlayerStatus().setText("Player Status: " + player.getPlayerName() +" with player ID " 
                									+ player.getPlayerId() + " was added!");
            }
        }
        // remove players
        for (Player player : this.getVisiblePlayers())
        {
            if (!this.gameEngine.getAllPlayers().contains(player))
            {
                removePlayerPanel(player);
                statusBar.getPlayerStatus().setText("Player Status: " + player.getPlayerName() +" with player ID " 
                									+ player.getPlayerId() + " was removed!");
            }
        }

        // update GUI to add/remove players
        this.revalidate();
        this.repaint();
        this.pack();
    }

    // add player summary panel to all the player panels container
    private void addPlayerPanel(Player player)
    {
        SummaryPanel playerSummaryPanel = new SummaryPanel(this, this.gameEngine, player);

        this.playerSummaryPanels.put(player, playerSummaryPanel);
        this.playerSummaryPanelsContainer.add(playerSummaryPanel);
        this.visiblePlayers.add(player);
    }

    // remove the selected player summary panel from all the player summary panels 
    private void removePlayerPanel(Player player)
    {
        SummaryPanel playerSummaryPanel = playerSummaryPanels.get(player);

        this.playerSummaryPanels.remove(playerSummaryPanel);
        this.playerSummaryPanelsContainer.remove(playerSummaryPanel);
        this.visiblePlayers.remove(player);
    }

    // get all the players visible on GUI 
    private Collection<Player> getVisiblePlayers()
    {
        return Collections.unmodifiableCollection(new ArrayList<Player>(this.visiblePlayers));
    }
    
    public Collection<SummaryPanel> getPlayerSummaryPanels()
    {
        return playerSummaryPanels.values();
    }

    public WheelPanel getWheelPanel()
    {
        return wheelPanel;
    }
    
    public StatusBarPanel getStatusBar()
    {
    	return statusBar;
    }
    
    public ToolBarPanel getToolBar()
    {
    	return toolBar;
    }
    
}