package ussr.aGui.tabs.visualizer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Konstantinas (Modified Brian's code)
 *  FIXME NOT YET FINISHED.
 *
 */
public class CanvasMouseListener extends MouseAdapter {
	
	private DrawingCanvas drawingCanvas;
	
	public CanvasMouseListener(DrawingCanvas drawingCanvas){
		this.drawingCanvas = drawingCanvas;
	}
	
	public void mousePressed(MouseEvent e) {
		drawingCanvas.setMouseClickX(e.getX());
		drawingCanvas.setMouseClickY(e.getY());
		int mouseX = drawingCanvas.getMouseClickX();
		int mouseY = drawingCanvas.getMouseClickY();
		System.out.println("Mouse position (x, y) = " + "(" + mouseX + ", " + mouseY + ")");
		e.getComponent().repaint();
	}
}
