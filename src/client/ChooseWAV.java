package client;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ChooseWAV{
	public static File chooseFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV Files", "wav");
		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Choose a WAV File");
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			//System.out.println("APPROVED");
			return chooser.getSelectedFile();	
		}
		else {
			return null;
		}
	}
}