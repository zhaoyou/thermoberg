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
import org.fdap.biz.order.PbmGoodsBaseInfoBiz;
import org.fdap.biz.order.PbmMiReceiverBiz;
import org.fdap.entity.order.PbmMiGoodsBaseInfo;
import org.fdap.entity.order.PbmMiReceiver;
import org.fdap.util.BaseAction;

public class OrderTrackAction extends BaseAction {
	
	private PbmMiReceiverBiz receiverBiz;
	private PbmGoodsBaseInfoBiz goodsBiz;
	private OrgBiz orgBiz;
	
	public void setOrgBiz(OrgBiz orgBiz) {
  	this.orgBiz = orgBiz;
  }

	public void setReceiverBiz(PbmMiReceiverBiz receiverBiz) {
  	this.receiverBiz = receiverBiz;
  }

	public void setGoodsBiz(PbmGoodsBaseInfoBiz goodsBiz) {
  	this.goodsBiz = goodsBiz;
  }



	// to order page.
	public ActionForward toOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String oid = request.getParameter("oid");
		
		if (oid == null || "".equalsIgnoreCase(oid)) {
			throw new Exception("bad request");
		}
		
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
		String receiver = request.getParameter("receiver");
		String prodArea = request.getParameter("prodArea");
		String lotno = request.getParameter("lotno");
		String goodsName = request.getParameter("goodsName");
		String goodsType = request.getParameter("goodsType");
		
		getResource(request, oid);
		
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
		return mapping.findForward("ordertbcc");
	}
	
	// to rodertbcc ref.
	public ActionForward toOrdertbccRef(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("ordertbccref");
	}
	
	
}
