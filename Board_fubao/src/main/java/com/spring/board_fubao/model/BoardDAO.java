package com.spring.board_fubao.model;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO implements InterBoardDAO{

	@Resource
	private SqlSessionTemplate sqlsession;

	//============================================//
	
	//회원가입 정보 insert
	@Override
	public int insertMember(MemberVO membervo) {
		int n = sqlsession.insert("board.insertMember", membervo);
		return n;
	}
	
	//ID 중복체크 Ajax
	@Override
	public int id_check(String id) {
		int n = sqlsession.selectOne("board.id_check", id);
		return n;	
	}

	//닉네임 중복체크 Ajax
	@Override
	public int nickname_check(String nickname) {
		int n = sqlsession.selectOne("board.nickname_check", nickname);
		return n;	
	}  
}