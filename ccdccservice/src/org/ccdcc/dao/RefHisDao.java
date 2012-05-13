package org.ccdcc.dao;

import org.ccdcc.entity.RefHisData;
import java.util.List;

/**
 * RefHisDao interface for upload ref history data.
 * @author zhaoyou
 *
 */
public interface RefHisDao {
	void insertData(String type, String tableName, List<RefHisData> list);
}
