package org.ccdcc.biz;

import java.util.List;

import org.ccdcc.entity.RefHisData;

/**
 * RefHisData Upload.
 * @author zhaoyou
 *
 */
public interface RefHisBiz {
	int upload(String projectId, String devId, String devType, List<RefHisData> list);
}
