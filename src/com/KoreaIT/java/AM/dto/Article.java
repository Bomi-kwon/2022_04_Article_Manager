package com.KoreaIT.java.AM.dto;

public class Article {
	// 왔다갔다 해야되는 데이터들
	//data transfer object 또는 value object라고 부름
		// 아까 new 써서 Article 객체 만들어서 class도 꼭 만들어야함.
	public int id;
	public String title;
	public String body;
	public String regDate;
	public int hit;
		// 아까 article 변수에 인자 담는다고 했어서
		// 그 인자들 타입 선언해주기

		public Article(int id, String regDate, String title, String body, int hit) {
			// 생성자 앞에는 되도록 public 붙여주기
			// 인자와 매개변수 연결시키기
			this.id = id;
			this.title = title;
			this.body = body;
			this.regDate = regDate;
			this.hit = hit;
			// this 뒤에 있는건 이 클래스에서 선언한 변수들.
			// = 오른쪽에 있는건 매개변수에서 받는것.
			// 문자가 똑같으니까 좀 헷갈린다.. }
			// 얘네는 테스트 데이터니까 내가 인자에 적어놓은 hit 따라야됨.
		}

		public Article(int id, String regDate, String title, String body) {
			this(id, regDate, title, body, 0);
			// 다른 생성자한테 일을 떠넘기는것!!
			// 새로 만들어지는 글은 조회수가 0이어야함.
		}

		public void increaseHit() {
			hit++;
		}
	}

