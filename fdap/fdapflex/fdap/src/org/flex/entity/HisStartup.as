package org.flex.entity
{
	/**
	 * 	映射历史启停实体类
	 * */
	 [RemoteClass(alias='org.fdap.entity.FdapStartUp')]
	public class HisStartup
	{
			public var startUpId:Number ;
			public var intervalValue:int ;
			public var carrier :String ;
			public var startTimestr:String ;
			public var endTimestr:String ;
			
		public function HisStartup()
		{
					
		}

	}
}