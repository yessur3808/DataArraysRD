package Main;

import java.lang.reflect.Array;
import java.util.Arrays;




public class KNN  {

	private FileDecoder fd;
	private Distance[] distance;
	private int[] Index;
	FastDtw dtw_dist;
	private int[] trainset;
	private int[] testData;
		
	
	
public void train(FileDecoder fd, int[] trainset){
	
	
	
	
}

	public Distance[] getDistances() {
		return distance;
	}

	public void setDistances(Distance[] distance) {
		this.distance = distance;
	}
	
	
	public void calculateKnn(int k, String s) {
		Arrays.sort(this.distance);
		int DistanceIndex = this.distance.length - 1;
		this.Index = new int[k];
		for (int i = 0; i < k; i++) {
			if ("euclidean".equals(s))
				this.Index[i] = this.distance[i].getIndex();
			else
				this.Index[i] = this.distance[DistanceIndex - i].getIndex();
		}
	}
	
public double Accuracy_Rate(double[] testData, FastDtw[] dtw_dist){
	
	
	
	
	
	return 0;
	}
	
	
	
}
