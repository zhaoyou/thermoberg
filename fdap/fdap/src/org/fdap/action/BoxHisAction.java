package org.fdap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.biz.BoxHisBiz;
import org.fdap.biz.CarHisBiz;
import org.fdap.biz.OrgBiz;
import org.fdap.entity.FdapBoxHisData;
import org.fdap.entity.FdapCarHisData;
import org.fdap.entity.Fdaporg;
import org.fdap.entity.Fdapref;
import org.fdap.util.BaseAction;

public class BoxHisAction extends BaseAction {
	private static Logger logger = Logger.getLogger(CarHisAction.class);
	private final Integer PAGESIZE=15;

	private BoxHisBiz boxHisBiz;
	private OrgBiz orgbiz;
	
	
	
	public OrgBiz getOrgbiz() {
		return orgbiz;
	}

	public void setOrgbiz(OrgBiz orgbiz) {
		this.orgbiz = orgbiz;
	}

	public ActionForward doBoxhisbyStartup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		//车载冷库refId
		String carrefId=request.getParameter("checkedrefId");
		//启停记录sid
		Integer sid=Integer.parseInt(request.getParameter("sid"));
				
		//企业Id
		String oid=request.getParameter("oid");
		String tableName="fdapboxhisdata_"+oid;
		
		if(sid==null||oid==null||oid==""||sid<=0){
			logger.warn("传递了非法参数");
		}
		//总页数和当前页
		Integer pageCount=1,page=1;
		//该启停记录下车载历史数据的条数
		Integer counts=boxHisBiz.getBoxHisCount(tableName, sid);
		if(counts!=null&&counts>=1){
			pageCount=counts/PAGESIZE+(counts%PAGESIZE==0?0:1);
		}
		else{
			logger.warn("获取总页数失败");
		}
		//如果总页数只有一页，且至少有一条历史数据，计算历史数据中对应探头的最大、最小和平均值
		if(pageCount==1&&counts!=null&&counts>=1){
			//获得探头的最大、最小和平均(M、M、A分别代表)
			List<Object> listMMA=boxHisBiz.getBoxHisMMA(tableName, sid);
			if(listMMA!=null||listMMA.size()!=0){
				request.setAttribute("carlistMMA", listMMA);
			}
			else{
				logger.warn("获取统计值失败");
			}
		}
				
		//根据启停Id查询车载历史数据，从第0条开始查，查PAGESIZE条
		List<FdapBoxHisData> list=boxHisBiz.getBoxHisbyStartup(tableName, sid,0,PAGESIZE);
				
//		System.out.println("carrefId "+carrefId);
		//根据车载冷库Id查询冷库信息，获取车载名称
		Fdapref carref=boxHisBiz.getBoxRefById(carrefId);
		if(carref!=null)
			request.setAttribute("carrefname", carref.getName());
		else{
			logger.warn("获取小批零冷库信息失败");
		}
		
		request.setAttribute("pagesize", PAGESIZE);
		if(list!=null&&list.size()!=0)
		{
			request.setAttribute("page",page );
			request.setAttribute("carpagecount", pageCount);
			
			request.setAttribute("hiscarList",list);
			return mapping.findForward("boxhisList");
		}
		else{
			System.out.println("获取小批零历史记录失败");
			//当历史数据为空时，在页面上显示消息
			request.setAttribute("msg", "<font color='blue'>该启停记录下没有小批零历史数据</font>");
			return mapping.findForward("boxhisList");
		}
	}
	
	/**
	 * 根据启停记录查询移动车载的历史数据(分页调用)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doBoxHisPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Integer sid=Integer.parseInt(request.getParameter("sid"));
		
		String oid=request.getParameter("oid");
		//车载名称
		String carrefname=request.getParameter("carrefname");
		String tableName="fdapcarhisdata_"+oid;
		
		if(sid==null||oid==null||oid==""||sid<=0){
			logger.warn("启停记录ID以及企业ID没获取到");
		}
		
		//获取当前页和总页数
		Integer page=Integer.parseInt(request.getParameter("page"));
		Integer pageCount=Integer.parseInt(request.getParameter("carpagecount"));
		
		//当前页是最后一页的时候，计算历史数据中对应探头的最大、最小和平均值
		if(pageCount==page){
			//获得探头的最大、最小和平均(M、M、A分别代表)
			List<Object> listMMA=boxHisBiz.getBoxHisMMA(tableName, sid);
			if(listMMA!=null||listMMA.size()!=0){
				request.setAttribute("carlistMMA", listMMA);
			}
			else{
				logger.warn("获取统计值失败");
			}
		}
		
		Integer startRow=(page-1)*PAGESIZE;
		//根据启停Id查询车载历史数据，从第 startRow 条开始查，查 PAGESIZE 条
		List<FdapBoxHisData> list=boxHisBiz.getBoxHisbyStartup(tableName, sid,startRow,PAGESIZE);
				
		request.setAttribute("carrefname", carrefname);
		request.setAttribute("pagesize", PAGESIZE);
		
		if(list!=null&&list.size()!=0)
		{
			request.setAttribute("page",page );
			request.setAttribute("carpagecount", pageCount);
			
			request.setAttribute("hiscarList",list);
			return mapping.findForward("boxhisList");
		}
		else{
			System.out.println("获取车载历史记录失败");
			//当历史数据为空时，在页面上显示消息
			request.setAttribute("msg", "<font color='blue'>没有满足条件的车载历史数据</font>");
			return mapping.findForward("boxhisList");
		}
	}
	
	/**
	 * 跳转到车载启停记录查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toHisStartUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String oid=request.getParameter("oid");
		
		if(oid==null || oid.equals("")){
			logger.warn("传递了非法的oid参数");
		}
		//根据企业oid查询企业信息
		Fdaporg fdaporg=orgbiz.getByOid(Long.parseLong(oid));
		//根据企业oid查询车载冷库信息列表
		List<Fdapref> boxreflist=boxHisBiz.getBoxRefByOid(oid);
		//request.setAttribute("oid", oid);
		if(fdaporg!=null){
			request.setAttribute("orgname", fdaporg.getName());
		}
		if(boxreflist!=null&&boxreflist.size()!=0){
			request.setAttribute("carreflist", boxreflist);
			return mapping.findForward("boxStartUp");
		}
		else{
			logger.warn("该企业下没有车载工程");
			//没有获取到企业信息
			request.setAttribute("msg", "<font color='blue'>该企业下没有小批零</font>");
			return mapping.findForward("boxStartUp");
		}
	}

	public BoxHisBiz getBoxHisBiz() {
		return boxHisBiz;
	}

	public void setBoxHisBiz(BoxHisBiz boxHisBiz) {
		this.boxHisBiz = boxHisBiz;
	}
}
