package org.fdap.entity.order;

import java.util.List;

/**
 * OrderTrack view for build tree.
 * @author zhaoyou
 *
 */
public class OrderTrackView {
  private String receiver;
  private List<OrderView> list;
	public String getReceiver() {
  	return receiver;
  }
	public void setReceiver(String receiver) {
  	this.receiver = receiver;
  }
	public List<OrderView> getList() {
  	return list;
  }
	public void setList(List<OrderView> list) {
  	this.list = list;
  }
}

