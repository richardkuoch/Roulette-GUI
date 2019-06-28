package model;

import model.interfaces.Player;
import model.enumeration.BetType;

public class SimplePlayer implements Player 
{
	private String playerId;
	private String playerName;
	private int initialPoints;
	private int bet;
	private BetType betType;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints)
	{
		this.playerId = playerId;
		this.playerName = playerName;
		this.initialPoints = initialPoints;
	}
	
	@Override
	public String getPlayerName()
	{
		return playerName;
	}
	
	@Override
	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}
	
	@Override
	public int getPoints() 
	{
		return initialPoints;
	}
	
	@Override
	public void setPoints(int points)
	{
		this.initialPoints = points;
	}
	
	@Override
	public String getPlayerId() 
	{
		return playerId;
	}
	
	@Override
	public boolean setBet(int bet) 
	{
		if(bet > 0 && initialPoints >= bet)
		{
			this.bet = bet;
			return true;
		}
		return false;
	}
	
	@Override
	public int getBet()
	{
		return bet;
	}
	
	@Override
	public void setBetType(BetType betType) 
	{
		this.betType = betType;
	}
	
	@Override
	public BetType getBetType() 
	{
		return betType;
	}
	
	@Override
	public void resetBet() 
	{
		bet = 0;
	}
	
	@Override
	public String toString() 
	{
		return String.format("Player: id=%s, name=%s, bet=%d, betType=%s, points=%d\n",
							this.playerId, this.playerName,this.bet, this.betType, this.initialPoints);
	}
	
	

}
