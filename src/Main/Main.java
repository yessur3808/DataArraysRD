package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

import util.EuclideanDistFunction;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		
//		Scanner scan = new Scanner(System.in);
//		String s = scan.next();
//		scan.close();
		
		String filename = "Data\\" + "J1" + ".txt" ;
		
		File file = new File(filename);
		System.out.println(file.getAbsolutePath());

		FileDecoder fD = new FileDecoder();
		double[] fileArray = null;
		
		try {
			fileArray = fD.toArray(file);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		if(fD == null) System.exit(1);
		
		double[] array = Arrays.copyOf(fileArray, fileArray.length - 105);
		
		double[] gX = fD.arrayDivide(fD.subSample(array, 6, 1), 16.384);
		double[] gY = fD.arrayDivide(fD.subSample(array, 6, 2), 16.384);
		double[] gZ = fD.arrayDivide(fD.subSample(array, 6, 3), 16.384);
		double[] aX = fD.arrayDivide(fD.subSample(array, 6, 4), 4096.0);
		double[] aY = fD.arrayDivide(fD.subSample(array, 6, 5), 4096.0);
		double[] aZ = fD.arrayDivide(fD.subSample(array, 6, 6), 4096.0);
				
		
		System.out.println(
				"Gx: " + gX.length + " " + Arrays.toString(gX)
				+ '\n' +
				"Gy: " + gY.length + " " + Arrays.toString(gY) 
				+ '\n' +
				"Gz: " + gZ.length + " " + Arrays.toString(gZ)
				+'\n' + 
				"Ax: " + aX.length + " " +Arrays.toString(aX) 
				+ '\n' +
				"Ay: " + aY.length + " " + Arrays.toString(aY) 
				+ '\n' +
				"Az: "  +aZ.length + " " + Arrays.toString(aZ)
				
				);
		
		TimeSeries ts = new TimeSeries();
		ts.build(gX, gY, gZ, aX, aY, aZ);
		
		FastDtw dtw = new FastDtw();
		WarpPath path = dtw.Compare(ts, ts, 1, new EuclideanDistFunction());
		
		System.out.println(path.getValue(path.getSize() - 1));
	}

}
