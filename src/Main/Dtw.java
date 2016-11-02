package Main;

import util.DistanceFunction;

public class Dtw {

	private WarpPath warpPath;
	private double distance;
	private SearchWindow window;
	
	public Dtw(){
		this.warpPath = new WarpPath();
		this.distance = 0;
	}
	
	public WarpPath dtw(TimeSeries ts1, TimeSeries ts2, DistanceFunction function){
		double[][] matrix = new double[ts1.size()][ts2.size()];
		int minPathLength = (int) Math.sqrt(Math.pow(ts1.size(), 2) + Math.pow(ts2.size(), 2) );
		WarpPath path = new WarpPath(minPathLength);
		
		//Initialisation
		matrix[0][0] = 0;

		for(int i = 1; i < ts1.size(); i++){
			matrix[i][0] = Double.POSITIVE_INFINITY;
		} //end for loop
		
		for(int j = 1; j < ts2.size(); j++){
			matrix[0][j] = Double.POSITIVE_INFINITY;
		}
		
		for(int i = 1; i < ts1.size(); i++){
			for(int j = 1; j < ts2.size(); j++){
				double cost = function.getDistance(ts1.get(i), ts2.get(j));
				cost += min(matrix[i-1][j], matrix[i][j-1], matrix[i-1][j-1]);
				matrix[i][j] = cost;
			}
		}
		
		// Backtracking of the path
		int i = ts1.size() - 1;
		int j = ts2.size() - 1;
		
		path.addPoint(i,j,matrix[i][j]);
		
		while(i > 0 || j > 0){
		    if (i == 0)
		        j = j - 1;
		    else if (j == 0)
		        i = i - 1;
		    else{
		        if(matrix[i-1][j] == min(matrix[i-1][j-1], matrix[i-1][j], matrix[i][j-1]))
		            i = i - 1;
		        else if(matrix[i][j-1] == min(matrix[i-1][j-1], matrix[i-1][j], matrix[i][j-1]))
		            j = j-1;
		        else{
		            i = i - 1;
		            j= j - 1;
		        }
		    }
		    path.addPoint(i, j, matrix[i][j]);
		}//end while loop
		path.reverse();
		return path;
	}
	
	public WarpPath dtw(TimeSeries ts1, TimeSeries ts2, DistanceFunction function, SearchWindow window){
		int minPathLength = (int) Math.sqrt(Math.pow(ts1.size(), 2) + Math.pow(ts2.size(), 2) );
		WarpPath path = new WarpPath(minPathLength);

		double[][] matrix = new double[ts1.size()][ts2.size()];
		double cost = 0;
		System.out.println(window.toString());
		//printing matrix
		int x = 0; int y=0;
		int lastMax = window.colomnMax[0];
		int lastValue = 0;
		boolean swtch = false;
		while( x < ts1.size()){
			if(lastMax != window.colomnMax[x] && !swtch){
				swtch = true;
				lastValue = x;
			}
			
			if( window.contains(x, y)){
				cost =  !(x==0&&y==0)?function.getDistance(ts1.get(x), ts2.get(y)):0;
				if(window.contains(x-1, y) || window.contains(x-1, y-1) || window.contains(x, y-1)){
					cost += min(matrix[x-1][y-1], matrix[x][y-1], matrix[x-1][y]);
			}
				x++;

			}else if(window.contains(x-1, y)){
				y++;
			}else{
				y++;
				swtch = false;
				x = lastValue;
			}
		}
		
		int	i = ts1.size() - 1;
		int j = ts2.size() - 1;		
		
		System.out.println("out !");
		
		while(i>0 || j>0){
		    if (i==0)
		        j = j - 1;
		    else if (j==0)
		        i = i - 1;
		    else{
		        if( matrix[i-1][j] == min(matrix[i-1][j-1], matrix[i-1][j], matrix[i][j-1]))
		            i = i - 1;
		        else if(matrix[i][j-1] == min(matrix[i-1][j-1], matrix[i-1][j], matrix[i][j-1]))
		            j = j-1;
		        else{
		            i = i - 1;
		            j= j- 1;
		        }
		    }
		    path.addPoint(i, j, matrix[i] [j]);
		}//end while loop
		path.reverse();
		return path;	
	}
	
	public void clear(){
		this.warpPath = new WarpPath();
		this.distance = 0;
	}
	
	public double getDistance(){
		return this.distance;
	}
	
	public WarpPath getWarpPath(){
		return this.warpPath;
	}
	
	private double min(double... array){
		double min = Double.POSITIVE_INFINITY;
		for(int i = 1; i < array.length; i++){
			if(array[i] < min) min = array[i];
		}
		return min;
	}
	
}
