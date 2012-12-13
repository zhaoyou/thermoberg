package org.fdap.entity.order;

public class OrderView {
	private Long oid;
	private String orderNo;
	public Long getOid() {
  	return oid;
  }
	public void setOid(Long oid) {
  	this.oid = oid;
  }
	public String getOrderNo() {
  	return orderNo;
  }
	public void setOrderNo(String orderNo) {
  	this.orderNo = orderNo;
  }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((orderNo == null) ? 0 : orderNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final OrderView other = (OrderView) obj;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (orderNo == null) {
			if (other.orderNo != null)
				return false;
		} else if (!orderNo.equals(other.orderNo))
			return false;
		return true;
	}
	
   
	
}
