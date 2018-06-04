package com.qdch.portal.littleproject.dao;



import org.apache.ibatis.annotations.Param;

import com.qdch.portal.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface DaoRuModelDao {
	  public  void  daoru(@Param("id")String id,@Param("parentid")String parentid,@Param("parentids")String parentids,@Param("name")String name);//已停用
}
