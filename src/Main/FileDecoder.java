package Main;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDecoder {

	
	public FileDecoder(){
	
	}
		
	
	
	public double[] toArray(File f) throws IOException, InterruptedException{
		
		int size = (int) (f.length() / 4);
		double[] result = new double[size];
		int cpt = 0;
		
		FileInputStream inputStream = new FileInputStream(f);
		BufferedInputStream buffer = new BufferedInputStream(inputStream);
				
		for(int i = 0; i < f.length(); i+=4, cpt++){
			byte[] dB = new byte[4];
			buffer.read(dB, 0, 4);
			String str = new String(dB);
			int val = Integer.parseInt(str, 16);
			
			if(val > (65535/2)){
				val = val | 0xffff0000;
			}
			result[cpt] = (double) val;
		}
		buffer.close();
		return result;
	}
	
	
	public double[] subSample(double[] array, int nbOfSpl, int splNb){
		
		int size = array.length / nbOfSpl;
		double[] result = new double[size];
		int cpt = 0;
		for(int i = splNb - 1; i < array.length; i+=nbOfSpl, cpt++){
			result[cpt] = array[i];
		}
		return result;
	}
	
	public double[] arrayDivide(double[] array, double value){
		for(int i = 0; i < array.length; i++){
			array[i] = array[i] / value;
		}
		return array;
	}
}
