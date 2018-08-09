package kirbsOrbitalSimulator;

import java.math.BigDecimal;

public class Test2dOrbitOfEarthAndSun {
	
	
	public static void main(String args[]) {
		Galaxy testGalaxy = new Galaxy();
		BigDecimal zero = new BigDecimal("0");
	
		/**
		 * Sun setup
		 */
		BigDecimal sunMass = new BigDecimal("1989000000000000000000000000000");
		OrbitalBody sun = new OrbitalBody("Sun", sunMass, zero, zero, zero, zero, zero, zero);
	
		/**
		 * Earth setup
		 */
		BigDecimal eMass = new BigDecimal("5972000000000000000000000");
		BigDecimal eDistanceFromSun = new BigDecimal("149600000000"); //Initial xCoord
		BigDecimal eOrbitVelocity = new BigDecimal("30000"); //Initial xSpeed
		OrbitalBody earth = new OrbitalBody("Earth", eMass, eDistanceFromSun, zero, zero, eOrbitVelocity, zero, zero);
	
		/**
		 * Galaxy setup
		 */
		testGalaxy.addObs(sun);
		sun.print();
		testGalaxy.addObs(earth);
		earth.print();
		
		/**
		 * Run sim
		 */
		testGalaxy.projectMovements(12);
		
		
	}
}
