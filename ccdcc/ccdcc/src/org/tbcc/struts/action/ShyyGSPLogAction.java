package org.tbcc.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tbcc.biz.ShyyGPSLogBiz;
import org.tbcc.entity.TbccShyyGPSLog;
import org.tbcc.util.BaseAction;

public class ShyyGSPLogAction extends BaseAction {
	
	private int limit = 500;
	private String startDate = "1970-1-1 00:00:00";
	private String endDate = "2050-1-1 00:00:00";
	private ShyyGPSLogBiz biz = null;
	
	public void setBiz(ShyyGPSLogBiz biz) {
  	this.biz = biz;
  }

	/**
	 * to Log List page.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String l = request.getParameter("limit");
		if (l != null && !"".equals("")) {
			limit = Integer.parseInt(l);
		}

		if (start != null && end != null) {
			System.out.println("start: " + start + " end: " + end + " limit: " + limit);
			List<TbccShyyGPSLog> list = biz.getByTime(start, end, limit);
			request.setAttribute("list", list);
		} else {
			request.setAttribute("first", true);
		}
		
		return mapping.findForward("tolist");
	}
	
	/**
	 * get Log data.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}
	
}
