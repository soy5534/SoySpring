package kr.co.ajoo.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ajoo.notice.domain.NoticeVO;
import kr.co.ajoo.notice.domain.PageInfo;
import kr.co.ajoo.notice.service.NoticeService;
import kr.co.ajoo.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeStore nStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public NoticeVO selectNoticeByNo(int noticeNo) {
		NoticeVO notice = nStore.selectNoticeByNo(session, noticeNo);
		return notice;
	}

	@Override
	public List<NoticeVO> selectNoticeList(PageInfo pInfo) {
		List<NoticeVO> nList = nStore.selectNoticeList(session, pInfo);
		return nList;
	}

	@Override
	public int getTotalCount() {
		int totalCount = nStore.selectTotalCount(session);
		return totalCount;
	}

	@Override
	public int insertNotice(NoticeVO notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}

	@Override
	public int updateNotice(NoticeVO notice) {
		int result = nStore.updateNotice(session, notice);
		return result;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		int result = nStore.deleteNotice(session, noticeNo);
		return result;
	}

	
	
	
	
	//0221 추가 검색
	@Override
	public List<NoticeVO> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<NoticeVO> searchList = nStore.selectNoticesByKeyword(session,pInfo, paramMap);
		return searchList;
	}
	
	//0221 추가 검색결과 페이징
	@Override
	public int getTotalCount(Map<String, String> paramMap) {
		int totalCount = nStore.selectTotalCount(session,paramMap);
		return totalCount;
	}

}







