package com.thermoberg.util;

import com.thermoberg.phone.Call;
import com.thermoberg.phone.ITelephone;
import com.thermoberg.phone.PhoneCaller;

/**
 * 测试拨打电话
 * @author zhaoyou
 *
 */
public class TestCall {
	public static void main(String[] args) {
//		new PhoneCaller().getBasicHttpBindingITelephone().call("Thermoberg", "#856", 2) ;
//		System.out.println("ok");
	//	new PhoneCaller().getBasicHttpBindingITelephone().sendMessage("","", "");
	//	new PhoneCaller().getBasicHttpBindingITelephone().stopCall("Thermoberg", "#856");
		
		new PhoneCaller().getBasicHttpBindingITelephone().sendMessage("Thermoberg", "13524279586", "赵有") ;
		
	}	
}
