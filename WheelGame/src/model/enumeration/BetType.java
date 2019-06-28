package model.enumeration;

import model.interfaces.Player;
import model.interfaces.Slot;

/**
 * Provided enum type for Further Programming representing a type of Bet<br>
 * See inline comments for where you need to provide additional code
 * 
 * @author Caspar Ryan
 * 
 */
public enum BetType
{
   RED
   {
      @Override
      public void applyWinLoss(Player player, Slot winSlot)
      {
    	  
         // TODO implementation
    	  int points = player.getBet();
    	  int currentPoints = player.getPoints();
    	  
    	  if(winSlot.getColor() == Color.RED && player.getBetType() == RED) 
    	  {
    		  player.setPoints(currentPoints + points);
    		 
    	  }
    	  else 
    	  {
    		  player.setPoints(currentPoints - points);
    		
    	  }
    	  
      }
   }, // TODO finish this class with other enum constants
   BLACK
   {
	   @Override
	   public void applyWinLoss(Player player, Slot winSlot)
	   {
		 
		 int points = player.getBet();
		 int currentPoints = player.getPoints();
	    	  
		 if(winSlot.getColor() == Color.BLACK && player.getBetType() == BLACK) 
		 {
			 player.setPoints(currentPoints + points);
		
		 }
		 else 
		 {
			 player.setPoints(currentPoints - points);

		 }
		   
	   }
   },
   ZEROS
   {
	   @Override
	   public void applyWinLoss(Player player, Slot winSlot) 
	   {
		
		int points = player.getBet();
		int currentPoints = player.getPoints();
		int odds = (Slot.WHEEL_SIZE/2)-1;
		
		if((winSlot.getColor() == Color.GREEN0 && player.getBetType() == ZEROS)||
		   (winSlot.getColor() == Color.GREEN00 && player.getBetType() == ZEROS)) 
		{
			 	int winnings = odds * points;
			 	player.setPoints(currentPoints + winnings);
			 	
		 }
		else 
		{
			 player.setPoints(currentPoints - points);
		}
	   }
	   
   };
   
	
   /**
    * This method is to be overridden for each bet type<br>
    * see assignment specification for betting odds and how win/loss is applied
    * 
    * @param player - the player to apply the win/loss points balance adjustment
    * @param winSlot - the winning slot the ball landed on
    */
   public abstract void applyWinLoss(Player player, Slot winSlot);
}