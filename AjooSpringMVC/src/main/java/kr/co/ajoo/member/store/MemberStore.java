package kr.co.ajoo.member.store;

import org.apache.ibatis.session.SqlSession;

import kr.co.ajoo.member.domain.MemberVO;

public interface MemberStore {

	/**
	 * 회원 정보 등록 Store
	 * @param session
	 * @param member
	 * @return result
	 */
	int insertMember(SqlSession session, MemberVO member);

	/**
	 * 회원 로그인 Store
	 * @param session
	 * @param member
	 * @return mOne
	 */
	MemberVO checkMemberLogin(SqlSession session, MemberVO member);

	/**
	 * 회원 아이디 검색 Store
	 * @param session
	 * @param memberId
	 * @return mOne
	 */
	MemberVO selectOneById(SqlSession session, String memberId);

	/**
	 * 회원 정보 수정 Store
	 * @param session
	 * @param member
	 * @return result
	 */
	int updateMember(SqlSession session, MemberVO member);

	/**
	 * 회원 정보 삭제 Store
	 * @param session
	 * @param memberId
	 * @return result
	 */
	int deleteMember(SqlSession session, String memberId);

}













