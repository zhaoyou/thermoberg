package org.fdap.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.OrgBiz;
import org.fdap.biz.RefConfigBiz;
import org.fdap.biz.RefHisBiz;
import org.fdap.biz.order.PbmErpRefInOutBiz;
import org.fdap.biz.order.PbmGoodsBaseInfoBiz;
import org.fdap.biz.order.PbmMiReceiverBiz;
import org.fdap.biz.order.PbmOrderTrackBiz;
import org.fdap.biz.order.SubOrderMainTrackBiz;
import org.fdap.biz.order.TrackRefBiz;
import org.fdap.entity.Fdapaiinfo;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapproject;
import org.fdap.entity.Fdapref;
import org.fdap.entity.order.PbmCarInOutTrack;
import org.fdap.entity.order.PbmErpRefInOutTrack;
import org.fdap.entity.order.PbmMedicineSummary;
import org.fdap.entity.order.PbmMiGoodsBaseInfo;
import org.fdap.entity.order.PbmMiReceiver;
import org.fdap.entity.order.PbmRefInOutTrack;
import org.fdap.entity.order.PbmSubOrderMainTrack;
import org.fdap.util.BaseAction;
import org.fdap.util.GsonUtil;

public class OrderTrackAction extends BaseAction {
	
	private PbmMiReceiverBiz receiverBiz;
	private PbmGoodsBaseInfoBiz goodsBiz;
	private OrgBiz orgBiz;
	private PbmOrderTrackBiz orderTrackBiz;
	private SubOrderMainTrackBiz subOrderMainTrackBiz;
	private PbmErpRefInOutBiz erpRefInOutBiz; 
	private GsonUtil out;
	private TrackRefBiz trackRefBiz;
	private static Logger logger = Logger.getLogger(CarRealAction.class);
	
	private final Integer PAGESIZE=15;

	private RefHisBiz refHisBiz;
	private OrgBiz orgbiz;
	private RefConfigBiz refConfigBiz;
	
	
	
	public void setRefConfigBiz(RefConfigBiz refConfigBiz) {
		this.refConfigBiz = refConfigBiz;
	}

	public RefHisBiz getRefHisBiz() {
		return refHisBiz;
	}

	public void setRefHisBiz(RefHisBiz refHisBiz) {
		this.refHisBiz = refHisBiz;
	}
	
	public OrgBiz getOrgbiz() {
		return orgbiz;
	}

	public void setOrgbiz(OrgBiz orgbiz) {
		this.orgbiz = orgbiz;
	}
	
	
	public void setTrackRefBiz(TrackRefBiz trackRefBiz) {
		this.trackRefBiz = trackRefBiz;
	}

	public void setOut(GsonUtil out) {
		this.out = out;
	}

	public void setErpRefInOutBiz(PbmErpRefInOutBiz erpRefInOutBiz) {
		this.erpRefInOutBiz = erpRefInOutBiz;
	}

	public void setSubOrderMainTrackBiz(SubOrderMainTrackBiz subOrderMainTrackBiz) {
		this.subOrderMainTrackBiz = subOrderMainTrackBiz;
	}

	public void setOrgBiz(OrgBiz orgBiz) {
  	this.orgBiz = orgBiz;
  }

	public void setReceiverBiz(PbmMiReceiverBiz receiverBiz) {
  	this.receiverBiz = receiverBiz;
  }

	public void setGoodsBiz(PbmGoodsBaseInfoBiz goodsBiz) {
  	this.goodsBiz = goodsBiz;
  }

	public void setOrderTrackBiz(PbmOrderTrackBiz orderTrackBiz) {
  	this.orderTrackBiz = orderTrackBiz;
  }

	// to order page.
	public ActionForward toOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid");
		
		if (oid == null || "".equalsIgnoreCase(oid)) {
			throw new Exception("bad request");
		}
		
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String orderNo = request.getParameter("orderNo");
		String rid = request.getParameter("receiver");
		String prodArea = request.getParameter("prodArea");
		String lotno = request.getParameter("lotno");
		String goodsName = request.getParameter("goodsName");
		String goodsType = request.getParameter("goodsType");
		
		
		
		getResource(request, oid);
		
		//request.setAttribute("orderList", orderTrackBiz.getOrder(oid, startTime, endTime,
		//		orderNo, rid, prodArea,	lotno, goodsName, goodsType));
		
		
		getResource(request, oid);
		return mapping.findForward("order");
	}
	
	
    //to order page.
	public ActionForward queryOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String orderNo = request.getParameter("orderNo");
		String rid = request.getParameter("receiver");
		String prodArea = request.getParameter("prodArea");
		String lotno = request.getParameter("lotno");
		String goodsName = request.getParameter("goodsName");
		String goodsType = request.getParameter("goodsType");
		
		
		
		getResource(request, oid);
		
		request.setAttribute("orderList", orderTrackBiz.getOrder(oid, startTime, endTime,
				orderNo, rid, prodArea,	lotno, goodsName, goodsType));
		
		return mapping.findForward("order");
	}
	
	// query order detail info.
	public ActionForward queryOrderDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String orderNo = request.getParameter("orderNo");
		String rid = request.getParameter("receiver");
		String prodArea = request.getParameter("prodArea");
		String lotno = request.getParameter("lotno");
		String goodsName = request.getParameter("goodsName");
		String goodsType = request.getParameter("goodsType");
		
		String orderId = request.getParameter("orderId");
		
		
		
		getResource(request, oid);
		
		request.setAttribute("orderList", orderTrackBiz.getOrder(oid, startTime, endTime,
				orderNo, rid, prodArea,	lotno, goodsName, goodsType));
		
		List<PbmMedicineSummary> list = subOrderMainTrackBiz.getInfo(Long.parseLong(orderId), goodsName,
				goodsType, prodArea, lotno);
		
		request.setAttribute("detailList", list);
		
		return mapping.findForward("order");
	}
	
	private void getResource(HttpServletRequest request, String oid) {
		
		List<PbmMiReceiver> list = receiverBiz.getList(Long.parseLong(oid));	
		List<PbmMiGoodsBaseInfo> goodsList = goodsBiz.getList(Long.parseLong(oid));
		
		request.setAttribute("org", orgBiz.getByOid(Long.parseLong(oid)));
		request.setAttribute("receiverList", list);
		request.setAttribute("goodslist", goodsList);
		request.setAttribute("goodTypeList", getGoodsTypeList(goodsList));
		request.setAttribute("areaList", getProdAreaList(goodsList));
	}
	
	// get drug prodarea.
	private List<String> getProdAreaList(List<PbmMiGoodsBaseInfo> list) {
		List<String> arealist = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();
		for (PbmMiGoodsBaseInfo info: list) {
			if (!map.containsKey(info.getProdarea().trim())) {
				arealist.add(info.getProdarea().trim());
				map.put(info.getProdarea().trim(), info.getProdarea().trim());
			}
			
		}
		return arealist;
	}
	
	// get drug type.
	private List<String> getGoodsTypeList(List<PbmMiGoodsBaseInfo> list) {
		List<String> arealist = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();
		for (PbmMiGoodsBaseInfo info: list) {
			if (!map.containsKey(info.getGoodsType().trim())) {
				arealist.add(info.getGoodsType().trim());
				map.put(info.getGoodsType().trim(), info.getGoodsType().trim());
			}
			
		} 
		return arealist;
	}
	
	
	
	// to ordertbcc.
	public ActionForward toOrdertbcc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		String orderId = request.getParameter("orderId");
		String kid = request.getParameter("kid");
		String subOrderMid = request.getParameter("subOrderMid");
		String goodsName = request.getParameter("goodsName");
		String orderName = request.getParameter("orderName");
		
		System.out.println("oid: " + oid + " orderId: " + orderId + " kid: " + kid 
				+ " subOrderMid: " + subOrderMid + " goodsName: " + goodsName);
		List<PbmErpRefInOutTrack> list = 
			erpRefInOutBiz.getByKid(Long.parseLong(kid), Long.parseLong(oid));
		
		List<PbmRefInOutTrack> refList = trackRefBiz.getRefTrack(Long.parseLong(oid),
				Long.parseLong(subOrderMid), Long.parseLong(orderId));
		
		List<PbmCarInOutTrack> carList = trackRefBiz.getCarTrack(Long.parseLong(oid),
				Long.parseLong(subOrderMid), Long.parseLong(orderId));
		
		for (PbmErpRefInOutTrack t: list) {
			List xx = trackRefBiz.getRefEnv(t.getErpRefId());
			if (xx != null && xx.size() > 0) {
				Object[] info = (Object[])xx.get(0);
				t.setStoreEnv(new Double(info[3].toString()).intValue() + "℃" +
						" - " + new Double(info[2].toString()).intValue() + "℃");
				t.setStoreType(info[0].toString());
				t.setStoreName(info[1].toString());
				t.setStoreId(Long.parseLong(info[4].toString()));
			}
		}
		
		for(PbmRefInOutTrack r: refList) {
			List xx = trackRefBiz.getRefEnv(r.getErpRefId());
			if (xx != null && xx.size() > 0) {
				Object[] info = (Object[])xx.get(0);
				r.setStoreEnv(new Double(info[3].toString()).intValue() + "℃" +
						" - " + new Double(info[2].toString()).intValue() + "℃");
				r.setStoreType(info[0].toString());
				r.setStoreName(info[1].toString());
				r.setStoreId(Long.parseLong(info[4].toString()));
			}
		}
		
		for(PbmCarInOutTrack c: carList) {
			List xx = trackRefBiz.getCarEnv(c.getMiCarId());
			if (xx != null && xx.size() > 0) {
				Object[] info = (Object[])xx.get(0);
				c.setStoreEnv(new Double(info[3].toString()).intValue() + "℃" +
						" - " + new Double(info[2].toString()).intValue() + "℃");
				c.setStoreType(info[0].toString());
				c.setStoreName(info[1].toString());
				c.setStoreId(Long.parseLong(info[4].toString()));
			}
		}
		
		PbmSubOrderMainTrack subOrder = subOrderMainTrackBiz.get(Long.parseLong(subOrderMid));
		
		
		//out.print(list);
		request.setAttribute("goodsName", goodsName);
		request.setAttribute("orderName", orderName);
		request.setAttribute("tracklist", list);
		request.setAttribute("refList", refList);
		request.setAttribute("carList", carList);
		request.setAttribute("subOrder", subOrder);
		
		return mapping.findForward("ordertbcc");
	}
	
	
	
	
	// ************************** to rodertbcc ref. ***********************
	public ActionForward toOrdertbccRef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		
		String oid=request.getParameter("oid");
		//获取查询冷库历史数据的开始和结束时间
		String refstartTime=request.getParameter("start");
		String refendTime=request.getParameter("end");
		
		String refId=request.getParameter("refId");

		
		if(oid==null||oid.equals("")||refId==null||refId.equals("")||refstartTime==null||
				refstartTime.equals("")||refendTime==null||refendTime.equals("")){
			logger.warn("传递了非法参数");
		}
		
		String tableName="fdaprefhisdata_"+oid;
		
		Integer refpage=1,refPagecount=1;
		//查询满足条件的冷库历史数据的条数，并计算总页数
		Integer counts=refHisBiz.getRefHisCount(tableName, refstartTime, refendTime,refId);
		if(counts!=null&&counts>=1)
			refPagecount=counts/PAGESIZE+(counts%PAGESIZE==0?0:1);
		//根据冷库Id获取探头列表
		List<Fdapaiinfo> Ailist=refHisBiz.getAiByRefId(refId);
		logger.info("size: " + Ailist.size());
		//如果总页数只有一页，且满足条件的历史数据不止一条，则计算相应探头的最大、最小和平均值
		if(refPagecount==1&&counts!=null&&counts>=1){
			List<Object> refListMMA=refHisBiz.getRefHisMMA(tableName, refstartTime, refendTime, Ailist,refId);
			request.setAttribute("refListMMA", refListMMA);
		}
		
		//根据条件查询冷库历史数据，从 0 条开始查，查 PAGESIZE 条
		List<Object> list=refHisBiz.getRefHisData(tableName, refstartTime, refendTime,0,PAGESIZE,Ailist,refId);
		
		Fdaporg fdaporg=orgbiz.getByOid(Long.parseLong(oid));

		
		//request.setAttribute("oid", oid);
		request.setAttribute("refAiList", Ailist);
		request.setAttribute("orgname", fdaporg.getName());

		
		
		//记录下首次查询的冷库Id、工程Id、查询开始时间和结束时间
		request.setAttribute("ref", refConfigBiz.getById(Long.parseLong(refId)));
		request.setAttribute("checkedrefId", refId);
		request.setAttribute("checkedrefstartTime", refstartTime);
		request.setAttribute("checkedrefendTime", refendTime);
		
		request.setAttribute("refhisList", list);
		request.setAttribute("refpagecount", refPagecount);
		request.setAttribute("refpage", refpage);
			
		return mapping.findForward("ordertbccref");
	}
	
	public ActionForward doOrdertbccRef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String oid=request.getParameter("oid");
		//获取首次查询时的开始时间和结束时间，并根据这个时间来查询
		String checkedrefstartTime=request.getParameter("checkedrefstartTime");
		String checkedrefendTime=request.getParameter("checkedrefendTime");
		String refstartTime=checkedrefstartTime;
		String refendTime=checkedrefendTime;
		//获取首次查询时的工程Id和冷库Id，并根据这两个Id来查询
		String checkedrefId=request.getParameter("checkedrefId");
		String refId=checkedrefId;
		
		if(oid==null||oid.equals("")||refId==null||refId.equals("")||refstartTime==null||
				refstartTime.equals("")||refendTime==null||refendTime.equals("")){
			logger.warn("传递了非法的oid参数");
		}
		
		String tableName="fdaprefhisdata_"+oid;
		
		//获得当前页和总页数
		Integer refpage=Integer.parseInt(request.getParameter("refpage"));
		Integer refPagecount=Integer.parseInt(request.getParameter("refpagecount"));
		//根据冷库Id查询探头列表
		List<Fdapaiinfo> Ailist=refHisBiz.getAiByRefId(refId);
		//如果当前页是最后一页，则计算历史数据中相应探头的最大、最小和平均值
		if(refPagecount==refpage){
			List<Object> refListMMA=refHisBiz.getRefHisMMA(tableName, refstartTime, refendTime, Ailist,refId);
			request.setAttribute("refListMMA", refListMMA);
		}
		
		Integer startrow=(refpage-1)*PAGESIZE;
		//根据条件查询冷库历史数据，从 startrow 条开始查，查 PAGESIZE 条
		List<Object> list=refHisBiz.getRefHisData(tableName, refstartTime, refendTime,startrow,PAGESIZE,Ailist,refId);
		
		Fdaporg fdaporg=orgbiz.getByOid(Long.parseLong(oid));

		
		request.setAttribute("orgname", fdaporg.getName());
		//request.setAttribute("oid", oid);
		request.setAttribute("pagesize", PAGESIZE);
		
		/*request.setAttribute("refstartTime", refstartTime);
		request.setAttribute("refendTime", refendTime);
		request.setAttribute("refId", refId);
		request.setAttribute("projectid", projectid);*/
		
		//记录下首次查询的冷库Id、工程Id、查询开始时间和结束时间
		request.setAttribute("ref", refConfigBiz.getById(Long.parseLong(refId)));
		request.setAttribute("checkedrefId", checkedrefId);
		request.setAttribute("checkedrefstartTime", checkedrefstartTime);
		request.setAttribute("checkedrefendTime", checkedrefendTime);
		
		request.setAttribute("refAiList", Ailist);
		request.setAttribute("refhisList", list);
		request.setAttribute("refpagecount", refPagecount);
		request.setAttribute("refpage", refpage);
			
		
		return mapping.findForward("ordertbccref");
	}
	
	
}
