package Main;

import util.DistanceFunction;



public class FastDtw {
	static int cpt = 0;
	private WarpPath warpPath;
	private double distance;
	Dtw dtw;
	

	
	WarpPath path;
	
	public FastDtw(){
		this.warpPath = new WarpPath();
		this.distance = 0;
		dtw = new Dtw();
	}
	
	public WarpPath Compare(TimeSeries x, TimeSeries y, int radius, DistanceFunction function){
		
		int MinTS = radius + 2;
				
		if(Math.abs(x.size()) <= MinTS || Math.abs(y.size())<= MinTS){
			System.out.println("x " + x);
			System.out.println("y " + y);
			System.out.println("fn " + function);

			WarpPath path = dtw.dtw(x, y, function);
			System.out.println("path " + path);
			return path;
		}else{
			
			TimeSeries ShrunkX = x.reduceByHalf();
			TimeSeries ShrunkY = y.reduceByHalf() ;
			
			WarpPath LowRes = Compare(ShrunkX,ShrunkY,radius, function);
			ExpandedWindow window = new ExpandedWindow(LowRes, x, y, radius);
			System.out.println("recursion ended " + cpt++);

			return dtw.dtw(x, y, function, window);
		}
		
		
	}


	
}
