package org.flex.entity
{
	/**
	 * 		这个类是对应java中的 TbccRefInfo 类
	 * */
	 
	 [RemoteClass(alias='org.tbcc.entity.TbccRefInfo')]
	public class RefInfo
	{
		public var id :Number ;
		public var projectId :String ;
		public var netid :Number ;
		public var refid :Number ;
		public var realRefid :Number ;
		public var refName :String ;
		public var refType:Number ;
		public var floorType:Number ;
		public var floorNo :Number ;
		
		public function RefInfo()
		{
		}

	}
}