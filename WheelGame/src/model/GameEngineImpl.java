
package model;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.interfaces.GameEngine;
import view.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.Slot;
import model.enumeration.BetType;
import model.enumeration.Color;

public class GameEngineImpl implements GameEngine 
{	
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<GameEngineCallback> gameEngineCallbacks = new ArrayList<GameEngineCallback>();
	ArrayList<Slot> slots = new ArrayList<Slot>();
	// hard-coded the wheel numbers into an array 
	final int[] wheelNumbers = {00,27,10,25,29,12,8,19,31,18,6,21,33,16,4,23,35,14,
			   					2,0,28,9,26,30,11,7,20,32,17,5,22,34,15,3,24,36,13,1};
	
	//initialize creation of wheel in the constructor
	public GameEngineImpl() 
	{
		createWheel();
	}
	
	@Override
	public void spin(int initialDelay, int finalDelay, int delayIncrement)
	{	
		Slot randWheelSlot = null;
		Random rand = new Random();
		
		// to match output trace
		//int randIndex = 1;
		
		// to match specification requirement of random index
		int randIndex = rand.nextInt((slots.size()) + 1);
		
		do 
		{
			if(randIndex >= slots.size())
			{
				/* wheel has spun around once already so...
				 * inform loop to spin the wheel again from position 0
				*/ 
				randIndex = 0;
			}
			randWheelSlot = slots.get(randIndex);
			
			try 
			{
				/* pause the execution of current thread
				 * for specified time(initialDelay) in milliseconds
				 */
				Thread.sleep(initialDelay);
				
			} catch (InterruptedException ie) 
			{
				// print stack trace
				ie.printStackTrace();
			}
			// increment delay each time through the loop
			initialDelay += delayIncrement;
			// increment index each time through the loop
			randIndex++;
			
			for (GameEngineCallback engine : gameEngineCallbacks) 
			{
				engine.nextSlot(randWheelSlot, this);
			}
			
		} while (initialDelay < finalDelay);
		
		/* set the next position index to 0 so wheel knows to take this  
		 * when it reaches last wheel slot
		 */
		if(randIndex >= slots.size())
		{
			randIndex = 0;
		}
		// get the next (winning) slot after wheel stopped spinning
		Slot winningSlot = slots.get(randIndex);
		
		for (GameEngineCallback engine : gameEngineCallbacks)
		{
			engine.result(winningSlot, this);
			
		}
		
	}
	
	@Override
	public void calculateResult(Slot winningSlot) 
	{
		Color color = winningSlot.getColor();
		
		// compare win slot color to possible ENUM color values for all players
		for(Player player: players)
		{
			if(color == Color.RED) 
			{
				BetType.RED.applyWinLoss(player, winningSlot);
			}
			else if(color == Color.BLACK) 
			{
				BetType.BLACK.applyWinLoss(player, winningSlot);
			}
			else if(color == Color.GREEN0 || color == Color.GREEN00)
			{
				BetType.ZEROS.applyWinLoss(player, winningSlot);
			}	
		}
		
		
	}
	
	@Override
	public void addPlayer(Player player)
	{
		players.add(player);
	}
	
	@Override
	public Player getPlayer(String id)
	{
		for(Player player: players) 
		{
			if(player.getPlayerId().equals(id)) 
			{
				return player;
			}
		}
		return null;
	}
	
	@Override
	public boolean removePlayer(Player player)
	{
		for(Player aPlayer: players)
		{
			if(player == aPlayer)
			{
				players.remove(aPlayer);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		gameEngineCallbacks.add(gameEngineCallback);
	}
	
	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		for(GameEngineCallback engine: gameEngineCallbacks) 
		{
			if(gameEngineCallback == engine) 
			{
				gameEngineCallbacks.remove(engine);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Collection<Player> getAllPlayers()
	{
		List<Player> immutablePlayerLists = Collections.unmodifiableList(players);
		return immutablePlayerLists;
	}
	
	@Override
	public boolean placeBet(Player player, int bet, BetType betType) 
	{
		// bet is greater than 0 and player has sufficient points to place bet
		if(bet > 0 && player.getPoints() >= bet)
		{
			player.setBet(bet);
			player.setBetType(betType);
			return true;
		}
		return false;
	}
	
	//create an unmodifiable collection of wheel slots
	@Override
	public Collection<Slot> getWheelSlots()
	{
		List<Slot> immutableSlotsLists = Collections.unmodifiableList(slots);
		return immutableSlotsLists;	
	}
	
	private void createWheel() 
	{	
		for (int i=0; i < wheelNumbers.length; i++) {	
			if((i % 2) == 0) 
			{
				// isEven = true then set to BLACK
				Slot blackSlots = new SlotImpl(i,Color.BLACK,wheelNumbers[i]);
				// add the slot on the wheel
				slots.add(blackSlots);
			}
			else 
			{
				// isOdd = true then set to RED
				Slot redSlots = new SlotImpl(i,Color.RED,wheelNumbers[i]);
				// add the slot on the wheel
				slots.add(redSlots);
			}

		}
		/* .set method to manually set color to green for position 0 Green00
		 * and green for position 19 green
		 */
		Slot green00Slot = new SlotImpl(0,Color.GREEN00,wheelNumbers[0]);
		Slot green0Slot = new SlotImpl(19,Color.GREEN0,wheelNumbers[19]);
		slots.set(0,green00Slot);
		slots.set(19, green0Slot);	
	}
		
}
