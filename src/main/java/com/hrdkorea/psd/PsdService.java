package com.hrdkorea.psd;

import java.util.List;


public interface PsdService {

	void formInsert(PsdVO vo);
	void formUpdate1(PsdVO vo);
	void formUpdate2(PsdVO vo);
	void formDelete(PsdVO vo);
	
	List<PsdVO> formList(PsdVO vo);
	PsdVO content(PsdVO vo);
}
