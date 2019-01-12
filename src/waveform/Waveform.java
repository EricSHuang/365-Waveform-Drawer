package waveform;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Waveform extends JPanel{
	
	private static int numSamples;
	private static int maxSampleVal;
	private static int maxAbsSampleVal;
	private ArrayList<Integer> points;
	
	public Waveform(ArrayList<Integer> amplitude) {
		this.points = amplitude;
	}
	
	//Drawing function
	@Override
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		
		//Scales to compress the waveform to a reasonable size
		double xScale = ((double)getWidth() / (numSamples-1));
		double yScale = ((double)getHeight() / (maxSampleVal*2));
		// System.out.println(numSamples);
		//System.out.println("xScale: "+ xScale);
        
		ArrayList<Point> scaledAmplitude = new ArrayList<>();
		for (int i=0; i < numSamples; i++) {
			int x = (int) Math.round((double)i * xScale);
			int y = (int) ((maxSampleVal - points.get(i)) * yScale);
			//System.out.printf("x: %d, y: %d\n", x, y);
			scaledAmplitude.add(new Point(x, y));
		}
		
		//Draw the waveform
		for(int i=0; i < scaledAmplitude.size(); i++) {
			int x = scaledAmplitude.get(i).x;
			int y1 = getHeight() / 2;	//Middle of the graph (i.e. amplitude = 0)
			int y2 = scaledAmplitude.get(i).y;
			g2d.drawLine(x, y1, x, y2);
		}
		
		//Write the numSamples and maxValues onto the graph
		g2d.setColor(Color.BLACK);
		String numSampleStr = "Number of Samples: ";
		g2d.drawString(numSampleStr + numSamples, 50, 25);
		String maxValStr = "Max Value: ";
		g2d.drawString(maxValStr + maxSampleVal, 50, 50);
		String maxAbsValStr = "Max Absolute Value: ";
		g2d.drawString(maxAbsValStr + maxAbsSampleVal, 50, 75);
	}
	        
	
	public static void drawWaveform(ArrayList<Integer> dataSet) {
		numSamples = dataSet.size();
		
		int tempMaxVal = 0;
		int tempMaxAbsVal = 0;
		for(int i=0; i < numSamples; i++) {
			if (dataSet.get(i) > tempMaxVal) {
				tempMaxVal = dataSet.get(i);
			}
			if (Math.abs(dataSet.get(i)) > Math.abs(tempMaxVal)) {
				tempMaxAbsVal = dataSet.get(i);
			}
		}
		maxSampleVal = tempMaxVal;
		maxAbsSampleVal = tempMaxAbsVal;
		
		Waveform waveform = new Waveform(dataSet);
		waveform.setPreferredSize(new Dimension(1080, 720));
		JFrame frame = new JFrame();
		frame.setTitle("Waveform");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(waveform);
		frame.pack();
		frame.setVisible(true);
		
	}
}