package kirbsOrbitalSimulator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Galaxy {
	
	/**
	 * List of orbital body objects.
	 */
	private ArrayList<OrbitalBody> obs;
	
	/**
	 * Will contain the list of last known positions and speeds of each orbital body.
	 */
	private PositionAndSpeed[] tempPasList;
	
	/**
	 * Will contain a list of masses of each orbital body.
	 */
	private BigDecimal[] tempMassList;
	
	/**
	 * BigDecimal of Zero
	 */
	final private BigDecimal zero = new BigDecimal("0");
	
	/**
	 * Physic's know gravitation constant
	 */
	final BigDecimal gravitationalConstant = new BigDecimal("0.0000000000667");
	
	BigDecimal two = new BigDecimal("2");
	
	public Galaxy() {
		obs = new ArrayList<OrbitalBody>();
	}
	
	/**
	 * Moves the objects forward over the desired months and stores the pas data for each month.
	 * @param months
	 */
	public void projectMovements(int months) {
		
		/**
		 * Number of seconds per month.
		 */
		final int secondsPerMonth = 2592000;
		
		/**
		 * Number of orbital bodies.
		 */
		int numberOfOrbitalBodies = obs.size();
		
		/**
		 * 
		 */
		tempPasList = new PositionAndSpeed[numberOfOrbitalBodies];
		
		/**
		 * 
		 */
		tempMassList = new BigDecimal[numberOfOrbitalBodies];
		
		/**
		 * Populate Lists
		 */
		for(int orbitalBody = 0; orbitalBody < numberOfOrbitalBodies; orbitalBody++) {
			tempPasList[orbitalBody] = obs.get(orbitalBody).getLastPas();
			tempMassList[orbitalBody] = obs.get(orbitalBody).getMass();
		}
		/**
		 * Calculates the movements over the course of the desired number of months.
		 */
		BigDecimal nXspeed, nYspeed, nZspeed, nXcoord, nYcoord, nZcoord;
		for (int nMonths = 0; nMonths < months; nMonths++) {
			for (int secondsPassed = 0; secondsPassed < secondsPerMonth; secondsPassed++) {
				for(int orbitalBody = 0; orbitalBody < numberOfOrbitalBodies; orbitalBody++) {
					for(int orbitalBody2 = 0; orbitalBody2 < numberOfOrbitalBodies; orbitalBody2++) {
						if (orbitalBody != orbitalBody2) {
							/**
							 * Change speeds by: previuosSpeed + newSpeed
							 * Change positions by: coord = coord + (previousSpeed + newSpeed) / 2
							 */
							nXspeed = tempPasList[orbitalBody].getXspeed().add(calc1DimensionalChangeInSpeed(tempPasList[orbitalBody].getXcoord(), tempPasList[orbitalBody2].getXcoord(), tempMassList[orbitalBody2]));
							nYspeed = tempPasList[orbitalBody].getYspeed().add(calc1DimensionalChangeInSpeed(tempPasList[orbitalBody].getYcoord(), tempPasList[orbitalBody2].getYcoord(), tempMassList[orbitalBody2]));
							nZspeed = tempPasList[orbitalBody].getZspeed().add(calc1DimensionalChangeInSpeed(tempPasList[orbitalBody].getZcoord(), tempPasList[orbitalBody2].getZcoord(), tempMassList[orbitalBody2]));
							
							nXcoord = tempPasList[orbitalBody].getXcoord().add(nXspeed.divide(two).setScale(2, RoundingMode.CEILING));
							nYcoord = tempPasList[orbitalBody].getYcoord().add(nYspeed.divide(two).setScale(2, RoundingMode.CEILING));
							nZcoord = tempPasList[orbitalBody].getZcoord().add(nZspeed.divide(two).setScale(2, RoundingMode.CEILING));
							
							tempPasList[orbitalBody] = new PositionAndSpeed(nXcoord, nYcoord, nZcoord, nXspeed, nYspeed, nZspeed);
						}
					}
				}
			}
			/**
			 * Update positions with final temp variable values.
			 */
			for(int orbitalBody = 0; orbitalBody < numberOfOrbitalBodies; orbitalBody++) {
				obs.get(orbitalBody).addPas(tempPasList[orbitalBody]);
				obs.get(orbitalBody).print();
			}
		}
	}
	/**
	 * New speed = SQRT ( GravitationalConstant * Other Mass / Distance )
	 * @param coord1
	 * @param coord2
	 * @param otherMass
	 * @return
	 */
	private BigDecimal calc1DimensionalChangeInSpeed(BigDecimal coord1, BigDecimal coord2, BigDecimal otherMass) {
		BigDecimal negOne = new BigDecimal("-1");
		if (coord1.add(coord2).compareTo(zero) == 0) {
			return new BigDecimal("0");
		}
		BigDecimal distance = coord1.add(coord2).divide(two).setScale(2, RoundingMode.CEILING);
		/**
		 * If negative distance.
		 */
		if (distance.compareTo(zero) == -1) {
			return negOne.multiply(sqrtBigDecimal(gravitationalConstant.multiply(otherMass).divide(distance.multiply(negOne)).setScale(2, RoundingMode.CEILING)));
		}
		/**
		 * If positive distance.
		 */
		return sqrtBigDecimal(gravitationalConstant.multiply(otherMass).divide(distance, 2, RoundingMode.CEILING));
	}
	/**
	 * Finds the square root of a BigDecimal. This function only handles positive numbers... TODO or does it?
	 * @param toBeSquared The number we need the sqrt of.
	 * @return The numbers sqrt.
	 */
	private BigDecimal sqrtBigDecimal(BigDecimal toBeSquared) {
		BigDecimal infiniteAvoid = BigDecimal.valueOf(2L);
		BigDecimal y = toBeSquared.divide(infiniteAvoid);		
		BigDecimal value = toBeSquared.divide(y);
		while (y.compareTo(value) > 0) {
			y = value.add(y).divide(infiniteAvoid).setScale(2,RoundingMode.CEILING);
			value = toBeSquared.divide(y, 2, RoundingMode.HALF_UP);
		}
		return y;
	}
	/**
	 * Adds a new orbital body to the ArrayList of orbital bodies.
	 * @param nObs new OrbitalBody
	 */
	public void addObs(OrbitalBody nObs) {
		obs.add(nObs);
	}
}
