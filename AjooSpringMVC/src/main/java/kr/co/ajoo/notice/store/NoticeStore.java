package kr.co.ajoo.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.co.ajoo.notice.domain.NoticeVO;
import kr.co.ajoo.notice.domain.PageInfo;

public interface NoticeStore {

	/**
	 * 공지사항 등록 Store
	 * @param session
	 * @param notice
	 * @return result
	 */
	int insertNotice(SqlSession session, NoticeVO notice);

	/**
	 * 공지사항 목록 Store
	 * @param session
	 * @return
	 */
	List<NoticeVO> selectNoticeList(SqlSession session, PageInfo pInfo);

	/**
	 * 공지사항 전체 게시물 갯수 Store
	 * @param session
	 * @return int
	 */
	int selectTotalCount(SqlSession session);

	/**
	 * 공지사항 상세 Store
	 * @param session
	 * @param noticeNo
	 * @return notice
	 */
	NoticeVO selectNoticeByNo(SqlSession session, int noticeNo);

	/**
	 * 공지사항 정보 수정 Store
	 * @param session
	 * @param notice
	 * @return int
	 */
	int updateNotice(SqlSession session, NoticeVO notice);

	/**
	 * 공지사항 삭제 Store
	 * @param session
	 * @param noticeNo
	 * @return
	 */
	int deleteNotice(SqlSession session, int noticeNo);


	
	
	
	
	
	
	/**
	 * 공지사항 Search Store 0221 추가
	 * @param session
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	List<NoticeVO> selectNoticesByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 검색 전체 게시물  Store 0221 추가
	 * @param session
	 * @return int
	 */
	int selectTotalCount(SqlSession session, Map<String, String> paramMap);

}









