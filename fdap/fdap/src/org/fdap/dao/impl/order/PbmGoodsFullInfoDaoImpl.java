package org.fdap.dao.impl.order;

import org.fdap.dao.order.PbmGoodsFullInfoDao;
import org.fdap.entity.order.PbmMiGoodsFullInfo;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class PbmGoodsFullInfoDaoImpl extends HibernateTemplate implements
    PbmGoodsFullInfoDao {

	@Override
	public PbmMiGoodsFullInfo get(Long id) {
		return (PbmMiGoodsFullInfo)this.getSession().get(PbmMiGoodsFullInfo.class, id);
	}

}
