package kr.co.ajoo.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ajoo.member.domain.MemberVO;
import kr.co.ajoo.member.service.MemberService;
import kr.co.ajoo.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberStore mStore;
	@Autowired
	private SqlSession session;

	@Override
	public int insertMember(MemberVO member) {
		int result = mStore.insertMember(session, member);
		return result;
	}

	@Override
	public MemberVO checkMemberLogin(MemberVO member) {
		MemberVO mOne = mStore.checkMemberLogin(session, member);
		return mOne;
	}

	@Override
	public MemberVO getOneById(String memberId) {
		MemberVO member = mStore.selectOneById(session, memberId);
		return member;
	}

	@Override
	public int updateMember(MemberVO member) {
		int result = mStore.updateMember(session, member);
		return result;
	}

	@Override
	public int deleteMember(String memberId) {
		int result = mStore.deleteMember(session, memberId);
		return result;
	}

}










































