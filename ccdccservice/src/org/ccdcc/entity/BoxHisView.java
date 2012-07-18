package org.ccdcc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 小批零移动车载历史数据实体视图
 * @author Administrator
 *
 */
public class BoxHisView implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
			private Integer id ;
			private Double ai1 ;
			private Integer parentId ;
			private Integer alarmStatus ;
			private Date updateTime ;
			private Integer flag ;
			
			private Date endTime ;		//为了满足webservice调用，该字段表示该历史数据所在的历史启停记录
			
			public Date getEndTime() {
				return endTime;
			}


			public void setEndTime(Date endTime) {
				this.endTime = endTime;
			}


			public BoxHisView(){}

			
			public BoxHisView(Integer id, Double ai1, Double ai2, Double ai3,
					Double ai4, Integer parentId, Double latitude,
					Integer latitude_dir, Double longitude,
					Integer longitude_dir, Integer alarmStatus,
					Date updateTime, Integer flag) {
				super();
				this.id = id;
				this.ai1 = ai1;
				this.parentId = parentId;
				this.alarmStatus = alarmStatus;
				this.updateTime = updateTime;
				this.flag = flag;
			}


			public Integer getId() {
				return id;
			}

			public void setId(Integer id) {
				this.id = id;
			}

			public Double getAi1() {
				return ai1;
			}

			public void setAi1(Double ai1) {
				this.ai1 = ai1;
			}


			public Integer getParentId() {
				return parentId;
			}

			public void setParentId(Integer parentId) {
				this.parentId = parentId;
			}

			public Integer getAlarmStatus() {
				return alarmStatus;
			}

			public void setAlarmStatus(Integer alarmStatus) {
				this.alarmStatus = alarmStatus;
			}

			public Date getUpdateTime() {
				return updateTime;
			}

			public void setUpdateTime(Date updateTime) {
				this.updateTime = updateTime;
			}

			public Integer getFlag() {
				return flag;
			}

			public void setFlag(Integer flag) {
				this.flag = flag;
			}
			
			
}
