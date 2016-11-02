package util;

import Main.NPoint;

public class EuclideanDistFunction implements DistanceFunction {

	@Override
	public double getDistance(NPoint u, NPoint v) {
		double sum = 0.0;
		for (int i = 0; i < u.dimension() - 1; i++) {
			double diff = u.getComponent(i) - v.getComponent(i);
			sum += Math.pow(diff, 2);
		}
		return Math.sqrt(sum);
	}
}
