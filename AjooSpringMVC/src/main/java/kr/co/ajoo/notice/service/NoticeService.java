package kr.co.ajoo.notice.service;

import java.util.List;
import java.util.Map;

import kr.co.ajoo.notice.domain.NoticeVO;
import kr.co.ajoo.notice.domain.PageInfo;

public interface NoticeService {

	/**
	 * 공지사항 등록 Service
	 * @param notice
	 * @return result
	 */
	int insertNotice(NoticeVO notice);

	/**
	 * 공지사항 목록 Service
	 * @return nList
	 */
	List<NoticeVO> selectNoticeList(PageInfo pInfo);

	/**
	 * 전체 게시물 갯수 Service
	 * @return int
	 */
	int getTotalCount();

	/**
	 * 공지사항 상세 Service
	 * @param noticeNo
	 * @return notice
	 */
	NoticeVO selectNoticeByNo(int noticeNo);

	/**
	 * 공지사항 수정 Service
	 * @param notice
	 * @return int
	 */
	int updateNotice(NoticeVO notice);

	/**
	 * 공지사항 삭제 Service
	 * @param noticeNo
	 * @return
	 */
	int deleteNotice(int noticeNo);

	
	
	
	
	
	
	
	
	/**
	 * 공지사항 검색 Service//0221 추가
	 * @param paramMap
	 * @return List
	 */
	List<NoticeVO> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 검색 게시물 전체 갯수 0221 추가
	 * @param paramMap
	 * @return
	 * 메소드 명이 똑같아도 되는 이유는 다형성에서의 오버로딩
	 * 타입의 갯수나 파라미터가 다르면 같은 메소드명을 쓸 수 있음
	 * 면접에서 오버로딩 vs 오버라이딩 잘 물어봄
	 */
	int getTotalCount(Map<String, String> paramMap);

	
}
