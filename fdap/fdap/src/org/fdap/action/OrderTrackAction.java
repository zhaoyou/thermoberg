package org.fdap.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.OrgBiz;
import org.fdap.biz.order.PbmErpRefInOutBiz;
import org.fdap.biz.order.PbmGoodsBaseInfoBiz;
import org.fdap.biz.order.PbmMiReceiverBiz;
import org.fdap.biz.order.PbmOrderTrackBiz;
import org.fdap.biz.order.SubOrderMainTrackBiz;
import org.fdap.biz.order.TrackRefBiz;
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
	
	
	// to rodertbcc ref.
	public ActionForward toOrdertbccRef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid = request.getParameter("oid");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String refId = request.getParameter("refId");
		System.out.println("oid: " + oid + " start: " + start +
				" end: " + end + " refId: " + refId);
		return mapping.findForward("ordertbccref");
	}
	
	
}
