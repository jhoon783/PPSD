package com.hrdkorea.psd;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PsdDao {

	void formInsert(PsdVO vo);
	void formUpdate1(PsdVO vo);
	void formUpdate2(PsdVO vo);
	void formDelete(PsdVO vo);
	
	List<PsdVO> formList(PsdVO vo);
	PsdVO content(PsdVO vo);
	
}
