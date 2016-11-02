package Main;

public class TimeSeries {

	private NPoint[] points;
	
	public TimeSeries(NPoint[] points){
		this.points = points;
	}
	
	public TimeSeries(){
		
	}

	public void build(double[] ... array) {
		this.points = new NPoint[array[0].length];
		for(int i = 0; i < array[0].length; i++){
			double [] point = new double[array.length];
			for(int j = 0; j < array.length; j++){
				point[j] = array[j][i];
			}
			NPoint timeSeriePoint = new NPoint(point);
			points[i] = timeSeriePoint;
		}
	}

	/**
	 * This method returns a copy of this TimeSeries reduced by half. Every 2nd value is ignored.
	 * @return reduced by half TimeSeries.
	 */
	public TimeSeries reduceByHalf(){
		
		NPoint[] copy = new NPoint[this.points.length / 2];
		for(int i = 0, j = 0; i < this.points.length - 1; i+=2, j++){
			copy[j] = this.points[i];
		}
		TimeSeries copyReducedByHalf = new TimeSeries(copy);
		return copyReducedByHalf;
	}
	
	public int size(){
		return this.points.length;
	}

	public NPoint get(int index) {
		return this.points[index];
	}

	
}
