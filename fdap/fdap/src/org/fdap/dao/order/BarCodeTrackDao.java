package org.fdap.dao.order;

import java.util.List;

import org.fdap.entity.order.PbmMiBarCodeTrack;;

/**
 * 
 * @author zhaoyou
 *
 */
public interface BarCodeTrackDao {
  List<PbmMiBarCodeTrack> getBy(Long orderId);
}
