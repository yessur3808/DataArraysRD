package Main;

import java.util.Arrays;

public class NPoint {

	private double[] components;

	public NPoint(double[] components) {
		this.components = components;
	}
	
	public int dimension(){
		return this.components.length;
	}
	
	public double getComponent(int index){
		return this.components[index];
	}

	@Override
	public String toString(){
		return Arrays.toString(this.components);
	}
}
