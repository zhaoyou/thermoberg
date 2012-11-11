package org.fdap.entity.order;

/**
 * Car entity.
 * @author zhaoyou
 *
 */
public class PbmMiCar {
	private Long carId;
	private String carName;
	private Long oid;
	public Long getCarId() {
  	return carId;
  }
	public void setCarId(Long carId) {
  	this.carId = carId;
  }
	public String getCarName() {
  	return carName;
  }
	public void setCarName(String carName) {
  	this.carName = carName;
  }
	public Long getOid() {
  	return oid;
  }
	public void setOid(Long oid) {
  	this.oid = oid;
  }
}
