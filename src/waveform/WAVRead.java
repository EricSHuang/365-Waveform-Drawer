package waveform;

import java.io.*;
import java.util.ArrayList;

public class WAVRead{
	public static ArrayList<Integer> wavRead(File file) throws IOException {
		FileInputStream wavInputs = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(wavInputs);
		ArrayList<Integer> dataSet = new ArrayList<Integer>();
		
		buffer.skip(34);	//skips to bits per sample information
		
		int bps = buffer.read();
		//System.out.println("bps = " + bps);
		int bytesPerSample = bps / 8;
		byte[] dataSample = new byte[bytesPerSample];
		
		buffer.skip(9);	//skips to the audio data (assuming 44 byte header)
		int bytesRead = buffer.read(dataSample);	//Equals bytesPerSample until it reach EOF
		while (bytesRead > 0) {
			int completeSample = dataSample[0];	//8 bits
			if (bytesPerSample == 2) {	//16 bits
				//Convert 2's complement little endian to unsigned int
				completeSample = (dataSample[0] & 0xFF) | (dataSample[1] & 0xFF) << 8;
				if (completeSample > 32678) {
					completeSample -= 65535;
				}
			}
			//System.out.println(completeSample);
			dataSet.add(completeSample);
			bytesRead = buffer.read(dataSample);
		}

		buffer.close();
		return dataSet;
	}
}

