package com.hrdkorea.psd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PsdServiceImpl implements PsdService {

	@Autowired
	private PsdDao dao;

	@Override
	public void formInsert(PsdVO vo) {
		dao.formInsert(vo);
	}

	@Override
	public List<PsdVO> formList(PsdVO vo) {
		return dao.formList(vo);
	}

	@Override
	public PsdVO content(PsdVO vo) {
		return dao.content(vo);
	}

	@Override
	public void formDelete(PsdVO vo) {
		dao.formDelete(vo);
	}

	@Override
	public void formUpdate1(PsdVO vo) {
		dao.formUpdate1(vo);
		
	}

	@Override
	public void formUpdate2(PsdVO vo) {
		dao.formUpdate2(vo);
		
	}

}
