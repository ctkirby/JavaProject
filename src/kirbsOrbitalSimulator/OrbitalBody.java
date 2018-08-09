package kirbsOrbitalSimulator;
import java.math.BigDecimal;
import java.util.ArrayList;

public class OrbitalBody {
	private ArrayList<PositionAndSpeed> pasRecord = new ArrayList<PositionAndSpeed>();
	private BigDecimal mass;
	private String name;
	
	public OrbitalBody(String nName, BigDecimal nMass, BigDecimal nXcoordinate, BigDecimal nYcoordinate, BigDecimal nZcoordinate, BigDecimal nXspeed, BigDecimal nYspeed, BigDecimal nZspeed) {
		name = nName;
		mass = nMass;  
		pasRecord.add(new PositionAndSpeed(nXcoordinate, nYcoordinate, nZcoordinate, nXspeed, nYspeed, nZspeed));
	}
	
	public PositionAndSpeed getLastPas() {
		return pasRecord.get(pasRecord.size() - 1);
	}
	
	public BigDecimal getMass() {
		return mass;
	}
	
	public void addPas(PositionAndSpeed nPas) {
		pasRecord.add(nPas);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<PositionAndSpeed> getPasRecord() {
		return pasRecord;
	}
	
	public void print() {
		System.out.println(name);
		System.out.println("xCoord: " + getLastPas().getXcoord() + "m");
		System.out.println("yCoord: " + getLastPas().getYcoord() + "m");
		System.out.println("zCoord: " + getLastPas().getZcoord() + "m");
	}
}
