package com.KoreaIT.java.AM.dto;

public class Member extends Dto{
	public String loginId;
	public String loginPW;
	public String name;
		// 아까 article 변수에 인자 담는다고 했어서
		// 그 인자들 타입 선언해주기
		public Member(int id, String regDate, String loginId, String loginPW, String name) {
			// 생성자 앞에는 되도록 public 붙여주기
			// 인자와 매개변수 연결시키기
			this.id = id;
			this.loginId = loginId;
			this.loginPW = loginPW;
			this.regDate = regDate;
			this.name = name;
			// this 뒤에 있는건 이 클래스에서 선언한 변수들.
			// = 오른쪽에 있는건 매개변수에서 받는것.
			// 문자가 똑같으니까 좀 헷갈린다.. }
			// 얘네는 테스트 데이터니까 내가 인자에 적어놓은 hit 따라야됨.
		}
}
