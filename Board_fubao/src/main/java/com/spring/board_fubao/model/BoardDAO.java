package com.spring.board_fubao.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	//로그인처리
	@Override
	public MemberVO get_login_member(Map<String, String> paraMap) {
		MemberVO login_user = sqlsession.selectOne("board.get_login_member", paraMap);		
		return login_user;
	}

	//게시글쓰기
	@Override
	public int write_end(BoardVO boardvo) {
		int n = sqlsession.insert("board.write_end", boardvo);
		return n;
	}

	//카테고리 리스트
	@Override
	public List<CategoryVO> get_category(int category_idx) {
		List<CategoryVO> cate_list = sqlsession.selectList("board.get_category", category_idx);
		return cate_list;
	} 
	
	/*페이징처리 안한 게시글목록
	@Override
	public List<BoardVO> get_boardList(HashMap<String, Object> paraMap) {
		List<BoardVO> board_list = sqlsession.selectList("board.get_boardList", paraMap);
		return board_list;
	}*/

	// 글 한 개 보기
	@Override
	public BoardVO getView(Map<String, String> paraMap) {
		BoardVO boardvo = sqlsession.selectOne("board.getView", paraMap);
		return boardvo;
	}
	
	// 글 조회수 1증가
	@Override
	public void setAddReadCnt(int b_idx) {
		sqlsession.update("board.setAddReadCnt", b_idx);

	}

	//모든 카테고리 조회
	@Override
	public List<CategoryVO> get_all_category(int role) {
		List<CategoryVO> cate_list_all = sqlsession.selectList("board.get_all_category", role);
		return cate_list_all;
	}

	//글 수정하기
	@Override
	public int edit(BoardVO boardvo) {
		int n = sqlsession.update("board.edit" , boardvo);
		return n;
	}

	//글 삭제하기
	@Override
	public int del(String b_idx) {
		int n = sqlsession.delete("board.del", b_idx);
		return n;
	}

	//댓글쓰기
	@Override
	public int add_comment(CommentVO commentvo) {
		int n = sqlsession.insert("board.add_comment", commentvo);
		return n;
	}

	
	//댓글쓰기 첨부파일
	@Override
	public int add_commentWithFile(CommentVO commentvo) {
		int n = sqlsession.insert("board.add_commentWithFile", commentvo);
		return n;
	}

	
	//댓글쓰면 tbl_board의 c_cnt 1증가
	@Override
	public int update_c_cnt(int b_idx_fk) {
		int n = sqlsession.update("board.update_c_cnt", b_idx_fk);
		return n;
	}

	//게시글에 달린 댓글 리스트 조회
	@Override
	public List<CommentVO> get_commentList(String b_idx_fk) {
		List<CommentVO> c_list = sqlsession.selectList("board.get_commentList", b_idx_fk);
		return c_list;
	}

	//게시글에 달린 댓글 리스트 조회 (페이징처리)
	@Override
	public List<CommentVO> get_commentListPaging(Map<String, String> paraMap) {
		List<CommentVO> c_list = sqlsession.selectList("board.get_commentListPaging", paraMap);
		return c_list;
	}

	//원글 글번호에 해당하는 댓글의 totalPage 알아오기
	@Override
	public int getCommentTotalPage(Map<String, String> paraMap) {
		int totalPage = sqlsession.selectOne("board.getCommentTotalPage", paraMap);
		return totalPage;
	}

	//댓글작성후 최근 댓글띄우기 위해 c_cnt 가져오기
	@Override
	public int getC_cnt(String b_idx_fk) {
		int c_cnt = sqlsession.selectOne("board.getC_cnt",b_idx_fk);
		return c_cnt;
	}

	//카테고리 별 게시글 총 개수 구해오기 (board_list 페이징)
	@Override
	public int getTotalCnt(int category_idx) {
		int totalCnt = sqlsession.selectOne("board.getTotalCnt", category_idx);
		return totalCnt;
	}

	//페이징 처리한 글목록
	@Override
	public List<BoardVO> boardListPagination(HashMap<String, Object> paraMap) {
		List<BoardVO> board_list = sqlsession.selectList("board.boardListPagination", paraMap);
		return board_list;
	}
	//페이징 처리한 글 목록(인기글)
	@Override
	public List<BoardVO> boardListHitsPagination(HashMap<String, Object> paraMap) {
		List<BoardVO> board_list = sqlsession.selectList("board.boardListHitsPagination", paraMap);
		return board_list;
	}

	//게시글 좋아요 기능
	@Override
	public int boardLike(Map<String, String> paraMap) {
		int n = sqlsession.insert("board.boardLike", paraMap);
		return n;
	}

	//좋아요 되어있는지 체크
	@Override
	public int checkLikeList(Map<String, String> paraMap) {
		int n = sqlsession.selectOne("board.checkLikeList", paraMap);
		return n;
	}

	//좋아요 취소 기능
	@Override
	public int boardLikeDelete(Map<String, String> paraMap) {
		int n = sqlsession.delete("board.boardLikeDelete", paraMap);
		return n;
	}

	//좋아요 개수(b_like 컬럼) 1증가
	@Override
	public int updateB_like(Map<String, String> paraMap) {
		int n = sqlsession.update("board.updateB_like", paraMap);
		return n;
	}

	//좋아요 개수 1감소
	@Override
	public int updateB_like_minus(Map<String, String> paraMap) {
		int n = sqlsession.update("board.updateB_like_minus", paraMap);
		return n;
	}

	//좋아요 개수 띄우기
	@Override
	public int boardLikeCnt(String b_idx_fk) {
		int b_like = sqlsession.selectOne("board.boardLikeCnt", b_idx_fk);
 		return b_like;
	}

	//댓글 삭제
	@Override
	public int comment_del(Map<String, String> paraMap) {
		int n = sqlsession.delete("board.comment_del", paraMap);
		return n;
	}

	//댓글 삭제시 댓글 개수(c_cnt) 수정
	@Override
	public int updateC_cnt(Map<String, String> paraMap) {
		int n = sqlsession.update("board.updateC_cnt", paraMap);
		return n;
	}

	//메인페이지 인기글 조회
	@Override
	public List<BoardVO> boardListHitsMain() {
		List<BoardVO> boardList = sqlsession.selectList("board.boardListHitsMain");
		return boardList;
	}



}
