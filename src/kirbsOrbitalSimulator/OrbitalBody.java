package kirbsOrbitalSimulator;
import java.math.BigDecimal;
import java.util.ArrayList;

public class OrbitalBody {
	/**
	 * Records all previous position and speed information in an ArrayList.
	 */
	private ArrayList<PositionAndSpeed> pasRecord = new ArrayList<PositionAndSpeed>();
	/**
	 * Mass of this object.
	 */
	private BigDecimal mass;
	/**
	 * Name of this object.
	 */
	private String name;
	
	/**
	 * Constructor assumes all input is valid.
	 * @param nName the name of the object
	 * @param nMass the weight in kg of the object
	 * @param nXcoordinate the x coordinate in meters
	 * @param nYcoordinate the y coordinate in meters
	 * @param nZcoordinate the z coordinate in meters
	 * @param nXspeed the x speed in meters/second
	 * @param nYspeed the y speed in meters/second
	 * @param nZspeed the z speed in meters/second
	 */
	public OrbitalBody(String nName, BigDecimal nMass, BigDecimal nXcoordinate, BigDecimal nYcoordinate, BigDecimal nZcoordinate, BigDecimal nXspeed, BigDecimal nYspeed, BigDecimal nZspeed) {
		name = nName;
		mass = nMass;  
		pasRecord.add(new PositionAndSpeed(nXcoordinate, nYcoordinate, nZcoordinate, nXspeed, nYspeed, nZspeed));
	}
	/**
	 * Returns the last known position of the object.
	 * @return
	 */
	public PositionAndSpeed getLastPas() {
		return pasRecord.get(pasRecord.size() - 1);
	}
	
	public BigDecimal getMass() {
		return mass;
	}
	/**
	 * Adds a new pas to the pasRecord
	 * @param nPas new pas
	 */
	public void addPas(PositionAndSpeed nPas) {
		pasRecord.add(nPas);
	}
	
	public String getName() {
		return name;
	}
	/**
	 * Returns the entire pasRecord.
	 * @return ArrayList<PositionAndSpeed>
	 */
	public ArrayList<PositionAndSpeed> getPasRecord() {
		return pasRecord;
	}
	/**
	 * Prints out the OrbitalBody name and the last known position.
	 */
	public void print() {
		System.out.println(name);
		System.out.println("xCoord: " + getLastPas().getXcoord() + "m");
		System.out.println("yCoord: " + getLastPas().getYcoord() + "m");
		System.out.println("zCoord: " + getLastPas().getZcoord() + "m");
	}
}
