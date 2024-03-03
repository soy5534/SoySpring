package kr.co.ajoo.notice.store.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.ajoo.notice.domain.NoticeVO;
import kr.co.ajoo.notice.domain.PageInfo;
import kr.co.ajoo.notice.store.NoticeStore;

@Repository
public class NoticeStoreImpl implements NoticeStore{

	@Override
	public List<NoticeVO> selectNoticeList(SqlSession session, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<NoticeVO> nList 
		= session.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return nList;
	}

	@Override
	public NoticeVO selectNoticeByNo(SqlSession session, int noticeNo) {
		NoticeVO notice = session.selectOne("NoticeMapper.selectNoticeByNo", noticeNo);
		return notice;
	}



	@Override
	public int selectTotalCount(SqlSession session) {
		int totalCount = session.selectOne("NoticeMapper.selectTotalCount");
		return totalCount;
	}

	@Override
	public int insertNotice(SqlSession session, NoticeVO notice) {
		int result = session.insert("NoticeMapper.insertNotice", notice);
		return result;
	}

	@Override
	public int updateNotice(SqlSession session, NoticeVO notice) {
		int result = session.update("NoticeMapper.updateNotice", notice);
		return result;
	}

	@Override
	public int deleteNotice(SqlSession session, int noticeNo) {
		int result = session.update("NoticeMapper.deleteNotice", noticeNo);
		return result;
	}
	
	
	
	
	
	
	
	
	//0221 추가
	@Override
	public List<NoticeVO> selectNoticesByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<NoticeVO> searchList 
		= session.selectList("NoticeMapper.selectNoticesByKeyword", paramMap, rowBounds);
		return searchList;
	}
	//0221 추가 검색 결과 페이징
	@Override
	public int selectTotalCount(SqlSession session, Map<String, String> paramMap) {
		int totalCount = session.selectOne("NoticeMapper.searchTotalCount",paramMap);
		return totalCount;
	}

}
















