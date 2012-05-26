package org.ccdcc.dao;

import org.ccdcc.entity.RefHisData;
import java.util.List;

/**
 * RefHisDao interface for upload ref history data.
 * @author zhaoyou
 *
 */
public interface RefHisDao {
	int insertData(String type, String tableName, List<RefHisData> list);
}
