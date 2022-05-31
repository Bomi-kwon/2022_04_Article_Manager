package com.KoreaIT.java.AM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class MemberController extends Controller{
	private Scanner sc;
	private List<Member> members;
	private String command;
	private String actionMethodName;
	public MemberController(Scanner sc, List<Member> members) {
		// App에서 썼던거 인자와 매개변수로 받아줘야됨.
		this.sc = sc;
		this.members = members;
	}
	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		
		switch (actionMethodName) {
			case "join" :
				doJoin();
				break;
		}
	}
	
	public void doJoin() {
		int id = members.size() + 1;
		String regDate = Util.getNowDateStr();
		String loginId = null;
		// liginId를 반복문 안에서 만든거라 밑에 인자가 몰라봐서
		// 반복문 밖에 처음으로 만들어야됨.
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			if (isJoinableLoginId(loginId) == false) {
				// 함수가 거짓이라면 처음 쓰는 아이디 쓸때까지 계속 붙잡아야됨.
				System.out.printf("%s는(은) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}
		String loginPW = null;
		String loginPWConfirm = null;
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPW = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인 : ");
			loginPWConfirm = sc.nextLine();
			// 비밀번호 재확인 해줘야됨
			if (loginPW.equals(loginPWConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
				// 만약 비밀번호랑 확인이랑 다르면 계속 붙잡아야됨!
			}
			break;
		}
		System.out.printf("이름 : ");
		String name = sc.nextLine();
		Member member = new Member(id, regDate, loginId, loginPW, name);
		members.add(member);
		System.out.printf("%d번 회원이 가입했습니다.\n", id);
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}
		// 일치하는 index 있으면 false라서 계속 다시 입력하라고 하고
		// 일치하는 index 없으면 -1이고 true라서 계속 진행됨!

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
		// Member arrayList를 뒤져서 일치하는 index 있으면 그걸 리턴하기
		// 일치하는 index없으면 -1을 리턴하고 그래야 true됨!
	}
}
