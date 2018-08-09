package kirbsOrbitalSimulator;
import java.math.BigDecimal;
/**
 * Position and speed data or pas. 
 * @author Charles T. Kirby
 *
 */
public class PositionAndSpeed {
	private BigDecimal xCoordinate, yCoordinate, zCoordinate, xSpeed, ySpeed, zSpeed;
	/**
	 * Constructor assumes all data is valid.
	 * @param nXcoordinate set X position
	 * @param nYcoordinate set Y position
	 * @param nZcoordinate set Z position
	 * @param nXspeed set X speed
	 * @param nYspeed set Y speed
	 * @param nZspeed set Z speed
	 */
	public PositionAndSpeed(BigDecimal nXcoordinate, BigDecimal nYcoordinate, BigDecimal nZcoordinate, BigDecimal nXspeed, BigDecimal nYspeed, BigDecimal nZspeed) {
		xCoordinate = nXcoordinate;
		yCoordinate = nYcoordinate;
		zCoordinate = nZcoordinate;
		xSpeed = nXspeed;
		ySpeed = nYspeed;
		zSpeed = nZspeed;
	}
	
	public BigDecimal getXcoord() {
		return xCoordinate;
	}
	public BigDecimal getYcoord() {
		return yCoordinate;
	}
	public BigDecimal getZcoord() {
		return zCoordinate;
	}
	public BigDecimal getXspeed() {
		return xSpeed;
	}
	public BigDecimal getYspeed() {
		return ySpeed;
	}
	public BigDecimal getZspeed() {
		return zSpeed;
	}
}
