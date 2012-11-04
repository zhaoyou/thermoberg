package org.fdap.dataupload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fdap.entity.Fdapstoretype;
import org.fdap.util.BaseAction;

/**
 * Test DataUpload.
 * @author zhaoyou
 *
 */
public class TestDataUpload extends BaseAction {
	
	Logger logger = Logger.getLogger(TestDataUpload.class);
	
	public ActionForward tolist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("entry to list method ....");
		return mapping.findForward("list") ;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,  
      HttpServletRequest request, HttpServletResponse response)  
      throws Exception {
			
		logger.info("xxxxxxxx");
		return null;
	}
}
