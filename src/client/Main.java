package client;

import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import waveform.WAVRead;
import waveform.Waveform;

public class Main{
	public static void main(String[] args) {
		File wavFile = ChooseWAV.chooseFile();
		try {
			ArrayList<Integer> dataSet = WAVRead.wavRead(wavFile);
			Waveform.drawWaveform(dataSet);
			
		} catch (NullPointerException | IOException e) {	//no file was selected
			//System.out.println("A WAV file was not selected");
			JFrame frame = new JFrame();
			String errorMessage = "An error in file selection occurred.\nYou either did not selelct a file or the selected file was not a WAV file.";
			JOptionPane.showMessageDialog(frame, errorMessage, "File Selection Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}