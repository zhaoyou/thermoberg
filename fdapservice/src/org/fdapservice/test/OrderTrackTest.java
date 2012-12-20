package org.fdapservice.test;

import java.util.ArrayList;
import java.util.List;

import org.fdapservice.entity.PbmMiCar;
import org.fdapservice.service.UploadService;
import org.fdapservice.service.impl.UploadServiceImpl;

public class OrderTrackTest {
	
	private static UploadService upload = new UploadServiceImpl();
	
	public static void main(String[] args) {
		testUploadCar();
	}
	
	public static void testUploadCar() {
		List<PbmMiCar> carList = new ArrayList<PbmMiCar>();
		PbmMiCar car = new PbmMiCar();
		car.setMicarId(100l);
		car.setMicarName("100");
		car.setIsdelete(new Short("100"));
		car.setOid(100l);
		
		PbmMiCar car2 = new PbmMiCar();
		car2.setMicarId(101l);
		car2.setMicarName("101");
		car2.setIsdelete(new Short("101"));
		car2.setOid(101l);
		
		carList.add(car);
		carList.add(car2);
		
		
		upload.uploadMiCar(carList);
	}
}
