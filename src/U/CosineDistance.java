package U;

import Main.NPoint;
import util.DistanceFunction;

public class CosineDistance implements DistanceFunction {

	@Override
	public double getDistance(NPoint u, NPoint v) {
		double product = 0.0;
		double x = 0.0, y = 0.0;		
			for(int i = 0;i < u.dimension() ;i++){
				product += u.getComponent(i) * v.getComponent(i);
				x += Math.pow(u.getComponent(i), 2);
				y += Math.pow(v.getComponent(i), 2);
			}	
		return product/(Math.sqrt(x)*Math.sqrt(y));	
	}

}
