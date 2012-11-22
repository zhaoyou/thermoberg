package org.fdap.dao.order;

import java.util.List;

import org.fdap.entity.PbmBarcodeTrack;

/**
 * 
 * @author zhaoyou
 *
 */
public interface BarCodeTrackDao {
  List<PbmBarcodeTrack> getBy(Long orderId);
}
