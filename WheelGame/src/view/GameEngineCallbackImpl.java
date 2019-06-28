package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

   public GameEngineCallbackImpl()
   {
      // FINE shows wheel spinning output, INFO only shows result
      logger.setLevel(Level.FINE);
   }

   @Override
   public void nextSlot(Slot slot, GameEngine engine)
   {
      // intermediate results logged at Level.FINE
	  // TODO: complete this method to log intermediate results
      logger.log(Level.FINE, "Next slot: " + slot.toString());
   }

   @Override
   public void result(Slot result, GameEngine engine)
   {
      // final results logged at Level.INFO
      logger.log(Level.INFO, "RESULT=" + result.toString()+"\n");
      // TODO: complete this method to log results
      logger.log(Level.INFO, "FINAL PLAYER POINT BALANCES");
      engine.calculateResult(result);
      String playerResults = "";
      for(Player player: engine.getAllPlayers()) 
      {
    	  playerResults += "Player: id=" + player.getPlayerId()
    			  		+ ", name=" + player.getPlayerName()
    			  		+ ", bet=" + player.getBet()
    			  		+ ", betType=" + player.getBetType()
    			  		+ ", points=" + player.getPoints() + "\n";
    	  
    	  //reset player bet amounts after calculate result 
    	  player.resetBet();
      }
      // player results logged at Level.INFO
      logger.log(Level.INFO,"\n" + playerResults);    
      
   }
   

}
