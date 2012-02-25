package org.fdapservice.util;

import org.fdapservice.phone.PhoneCaller;

import org.fdapservice.phone.ITelephone;

public class TelePhone {
	public static ITelephone itelphone = null;
	static{
		try {
			itelphone = new PhoneCaller().getBasicHttpBindingITelephone();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("创建ITelephone失败"+e.getStackTrace());
		}
	}
}
