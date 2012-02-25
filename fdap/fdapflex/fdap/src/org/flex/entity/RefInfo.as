package org.flex.entity
{
	/**
	 * 		这个类是对应java中的 TbccRefInfo 类
	 * */
	 
	 [RemoteClass(alias='org.fdap.util.FdaprefData')]
	public class RefInfo
	{
		public var refId :Number ;
		public var name :String ;
		public var floorType:Number ;
		public var floorNo :Number ;
		
		public function RefInfo()
		{
		}

	}
}