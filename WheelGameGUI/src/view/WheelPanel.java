package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.interfaces.GameEngine;
import model.interfaces.Slot;

public class WheelPanel extends JPanel
{
	private WheelGameFrame wheelGameFrame;
    private GameEngine gameEngine;
    private Slot slot;
    private Image wheelImage;
    private Image resizeWheelImage;
    private int resizeWidth;
    private int resizeHeight;
    private double scaleFactor;
    private double factor = 1;
    private double factorWidth;
    private double factorHeight;
    private int imageX;
    private int imageY;
    private final int ballDiameter = 25;
    private double radius;
    private final double resizeRadius = 0.93; 
    private double angle;
    private double ballX;
    private double ballY;
    private double centerX;
    private double centerY;
        	    
    public WheelPanel(WheelGameFrame wheelGameFrame, GameEngine gameEngine)
	{
    	this.setLayout(new BorderLayout());
    	this.gameEngine = gameEngine;
    	this.wheelGameFrame = wheelGameFrame;
		wheelImage = getWheelImage();
	}

	@Override
    public void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		
		// calculate scaling factor and dimensions from size of wheel image
		scaleFactor = Math.min(1, resizeImage(new Dimension(wheelImage.getWidth(null), wheelImage.getHeight(null)), getSize()));
		resizeWidth = (int) Math.round(wheelImage.getWidth(null) * scaleFactor);
		resizeHeight = (int) Math.round(wheelImage.getHeight(null) * scaleFactor);
		
		// scale the wheel image to appropriate size for JFrame
		resizeWheelImage = this.wheelImage.getScaledInstance(this.resizeWidth, this.resizeHeight, Image.SCALE_SMOOTH);
		
		// position to draw the wheel image
		imageX = ((getWidth() -1) - this.resizeWheelImage.getWidth(this)) / 2;
		imageY = ((getHeight() -1) - this.resizeWheelImage.getHeight(this)) / 2;
		
		// draw the wheel image at specified position
	    g.drawImage(this.resizeWheelImage, imageX, imageY, this);
	    
	    // draw the ball
	    drawBall(g);
	    
    }
	
	private void drawBall(Graphics g)
	{
		if (this.slot != null)
		{
			// set ball radius to new size after adjusting for resized wheel image
			radius = (((resizeWheelImage.getHeight(null) / 2) + (resizeWheelImage.getHeight(null) / 2)) / 2) * resizeRadius;

			// calculate the center position of resized wheel image
			centerX = imageX + this.resizeWheelImage.getWidth(this) / 2;
			centerY = imageY + this.resizeWheelImage.getHeight(this) / 2;
 
			// calculate theta for angle of the slot position
			angle = Math.toRadians(this.slot.getPosition() * (360.d / this.slot.WHEEL_SIZE) - 90.d);
			
			// calculate position for ball
			ballX = radius * Math.cos(angle) + centerX;
			ballY = radius * Math.sin(angle) + centerY;
			
			// draw ball at set position and set color to yellow
			g.setColor(Color.YELLOW);
		    g.drawOval((int) ballX - ballDiameter / 2, (int) ballY - ballDiameter / 2, ballDiameter, ballDiameter);	
		    g.fillOval((int) ballX - ballDiameter / 2, (int) ballY - ballDiameter / 2, ballDiameter, ballDiameter);	
		}
	}
	
	private Image getWheelImage()
	{
		Image image = null;
		try
		{
			image = ImageIO.read(new File("../WheelGame/img/Basic_roulette_wheel_1024x1024.png"));
		} 
		catch (IOException ioe) 
		{
			JOptionPane.showMessageDialog(wheelGameFrame, ioe.getMessage(), "Can't read input file", JOptionPane.ERROR_MESSAGE);
		}
		return image;
	}
	
    public Image getResizedWheelImage() 
    {
		return resizeWheelImage;
	}
    
    // method to resize image
    public double resizeImage(Dimension initialImage, Dimension finalImage) 
	{	
	    // calculate the scale factor
	    factorWidth = getResizeFactor(initialImage.width, finalImage.width);
	    factorHeight = getResizeFactor(initialImage.height, finalImage.height);
	    factor = Math.min(factorHeight, factorWidth);
	    return factor;
	}

	public double getResizeFactor(int initialSize, int targetSize) 
	{
	    double resizeFactor = 1;   
	    // calculate degree to resize
	    if (initialSize != targetSize) 
	    {
	    	resizeFactor = (double) targetSize / initialSize;
	    }
	    return resizeFactor;
	}
	
	public void updateBallPosition(Slot slot)
	{
		// move ball to next slot
		this.slot = slot;
	}
	
	
}