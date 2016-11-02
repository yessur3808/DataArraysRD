package Main;



public class Distance {

public double distance;
public int index;



	public Distance(int index, double distance){
		this.index = index;
		this.distance = distance;
	}
	
	public int getIndex(){
		return index;
	}
	
	
	public double getDistance(){
		return distance;
	}
	
	public int compare(Distance o){
		return Double.compare(this.distance, o.getDistance());
	}
	
	
}
