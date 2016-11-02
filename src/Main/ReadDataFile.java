package Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner; 


public class ReadDataFile {
	
	public static double rangeA = 4096.0;
	static double rangeG = 16.384;    
	double rate = 104;
	
	public static double[] Gx, Gy, Gz, Ax, Ay, Az;
	public static double[] gAx, gAz, gAs, gAy;
	
	
	public static boolean cont(double[] array){
		for(int i = 0; i < array.length - 1; i++){
			if(Math.abs(array[i]-array[i+1]) > 1){
				System.out.println("pb: " + i + " " + array[i] + " " + array[i+1]);
				return true;
			}
		}
		return false;
	}
	
	public static void Gyroacc(int[] array ){
		int size = (array.length - 420)/6;
		
		double a = 1.0/rangeG;
		double b = 1.0/rangeA;
		
		Gx = new double[size]; Gy = new double[size]; Gz = new double[size];
		Ax = new double[size]; Ay = new double[size]; Az = new double[size];
		gAx = new double[size]; gAy = new double[size]; gAz = new double[size];
		gAs = new double[size];

		for(int i=0; i < array.length - 426 ;i+=6){
			Gx[i/6]=array[i]*a;
			Gy[i/6]=array[i+1]*a;
			Gz[i/6]=array[i+2]*a;
			Ax[i/6]=array[i+3]*b;
			Ay[i/6]=array[i+4]*b;
			Az[i/6]=array[i+5]*b;
		}
//		for(int m = 0; m < Gx.length-6 ;m++){
//		gAx[m] = (Gx[m+1] - Gx[m]);
//	    gAy[m] = (Gy[m+1] - Gy[m]);
//	    gAz[m] = (Gz[m+1] - Gz[m]);
//		
//	    gAs[m]=Math.sqrt(gAx[m]*gAx[m]) + (gAy[m]*gAy[m]) +(gAz[m]*gAz[m]);
//		}
	}
	
	
	
	public static String HexToString(String hex){

		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();


		  for( int i=0; i<hex.length()-1; i+=2 ){

		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal = Integer.parseInt(output, 16);
		      //convert the decimal to character
		      sb.append((char)decimal);

		      temp.append(decimal);
		  }
		  System.out.println(temp.toString());

		  return sb.toString();
	  }
	
	
	
	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		
		String filename = s + ".txt" ;
		
		File file = new File(filename);
				
		FileInputStream fis = new FileInputStream(file);
		
		String x = "";
		
		
		
		while(fis.available() != 0){
			x += Character.toString((char)fis.read());
		}

		
		 int arraySize = x.length()/4;
		 int[] array = new int[arraySize];
		
	    
	    for(int i=0; i<x.length(); i+=4){
	    	String num = x.substring(i, i+4);
	         
	         array[i/4] = Integer.parseInt( num, 16);
	    }
	    
	    //System.out.println(Arrays.toString(array));
			
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		//fis.close();
		
		
		int count = 0;
	    for(int j =0; j < x.length(); j++){
	        if(x.charAt(j) == 'j'){
	            count++;
	            }
	    }
	    //System.out.println("The number of letter i is " + count);
		
		

		try {
			
//			System.out.println("Total file size to read (in bytes) : "
//					+ fis.available());
//			
			 


			int content;
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				//System.out.print((char) content);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
		Gyroacc(array);
		
		System.out.println(
				"Gx: " + Gx.length + "cont ? " + cont(Gx) + " " + Arrays.toString(Gx)
				+ '\n' +
				"Gy: " + Gy.length + " " + Arrays.toString(Gy) 
				+ '\n' +
				"Gz: " + Gz.length + " " + Arrays.toString(Gz)
				+'\n' + 
				"Ax: " + Ax.length + " " +Arrays.toString(Ax) 
				+ '\n' +
				"Ay: " + Ay.length + " " + Arrays.toString(Ay) 
				+ '\n' +
				"Az: "  +Az.length +"cont ? " + cont(Az) + " " + Arrays.toString(Az)
				
//				+ '\n' +
//				"gAx: "  + Arrays.toString(gAx)+ '\n' +
//				"gAy: "  + Arrays.toString(gAy)+ '\n' +
//				"gAz: "  + Arrays.toString(gAz)+ '\n' +
//				"gAs: "  + Arrays.toString(gAs)
				);
				
		}
	}



//+ '\n' +
//"gAx: "  + Arrays.toString(gAx)+ '\n' +
//"gAy: "  + Arrays.toString(gAy)+ '\n' +
//"gAz: "  + Arrays.toString(gAz)+ '\n' +
//"gAs: "  + Arrays.toString(gAs));
