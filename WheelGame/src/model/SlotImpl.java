package model;

import model.enumeration.Color;
import model.interfaces.Slot;

public class SlotImpl implements Slot 
{
	private int position;
	private int number;
	private Color color;
	
	public SlotImpl(int position, Color color, int number)
	{
		this.position = position;
		this.color = color;
		this.number = number;
	}
	
	@Override
	public int getPosition() 
	{
		return position;
	}
	
	@Override
	public int getNumber()
	{
		return number;
	}
	
	@Override
	public Color getColor() 
	{
		return color;
	}
	
	@Override
	public String toString()
	{
		return String.format("Position: %d, Color: %s, Number: %d ",
							  this.position, this.color, this.number);
	}
	
	@Override
	public boolean equals(Slot slot) 
	{
		if(slot == null) 
		{
			return false;
		}
		
		return (this.color == slot.getColor() && this.number == slot.getNumber());
	}
		
	@Override
	public boolean equals(Object slot)
	{
		if (this == slot)
		{
			return true;
		}
		
		if (slot == null)
		{
			return false;
		}
		
		if (!(slot instanceof Slot))
		{
			return false;
		}
		
		Slot obj = (Slot) slot;
		return equals(obj);
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + number;
		return result;
	}
	

}
