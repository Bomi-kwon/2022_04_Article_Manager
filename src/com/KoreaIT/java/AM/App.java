package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.Controller;
import com.KoreaIT.java.AM.controller.MemberController;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class App {
	// App은 은행에서 번호표 뽑아주는 안내원 역할임!
	private List<Article> articles;
	private List<Member> members;

	// static 은 static이랑만 연결할 수 있음.
	// private라고 쓰면 main 외부에서는 접근 못함. 자식도 몰라봄.
	// protected - 상속 관계에서는 가능함.
	// public - 공공재임. 다른 클래스에서도 쓸 수 있음.
	// 얘네를 접근지정자라고 부름.
	public App() {
		// static 생성자는 이렇게 만들어야됨.
		// 생성자 만들어서 한줄로 말고 따로 쓰기
		// 그럼 저 생성자는 main class 실행할 때 무조건 실행됨
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}
	// article에 대한 정보를 저장할 노트가 필요하다
	// arraylist는 방칸수 제한없이 방을 만들수 있다!
	// Article 객체만 들어가는 arraylist를 만들어서
	// 변수 articles와 연결하겠다.
	// 지역 변수로 만들면 모두가 접근할 수 없으니까 전역변수로
	// method 바깥으로 빼서 모두가 접근할수 있게 만들어주기!!

	public void start() {
		System.out.println("==프로그램 시작==");

		makeTestData();

		Scanner sc = new Scanner(System.in);
		// App이 하는 일이 너무 많으니까 부하직원 두고 일 나눠서 하자!!
		// 은행 창구 직원같은 cotroller 따로 만들어주기!!
		// App은 더 높은 service임
		
		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);

		// id는 꼭 반복문 밖에다가 정의해야한다!!
		// 안에서 정의하면 계속 0으로 초기화되기 때문!!

		while (true) {
			// true이므로 반복문은 계속 무한대로 돌아간다.
			// 그래서 나중에 반복문 깨고 나올 break를 꼭 해줘야함.
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();
			// 내가 입력한 문장을 command라는 변수에 저장하겠음.
			// 한줄에 쓰는 문장 하나를 통째로 취급!
			// trim은 뒤에 띄어쓰기 날려주는것!!

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
				// 아무것도 안쓰고 엔터치면 명령어 쓰라고 해야됨.
			}
			
			String[] commandBits = command.split(" ");
			
			if (commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
 			String controllerName = commandBits[0];
 			String actionMethodName = commandBits[1];
			
			Controller controller = null;
			// 컨트롤러 새로 만들어서 들어오는 입력어가
			// article로 시작하면 articleController로,
			// member로 시작하면 memberController로 바꿔주고
			// 각자에게 일 시키기
			// 그 다음 입력어가 뭐인지에 따라
			// 각각 다른거 행하도록 시키기
			
			if(controllerName.equals("article")) {
				controller = articleController;
			}
			else if (controllerName.equals("member")) {
				controller = memberController;
			}
			
			controller.doAction(command,actionMethodName);
			
			
			if (command.equals("System exit")) {
				break; // 반복문 평생 실행되지 않도록
				// System exit 명령어 치면 반복문 깨고 나오도록 꼭 써주기.
			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
				// 이상한 거 치면 존재하지 않는 명령어라고 막아야됨.
				continue;
			}
		}
		sc.close();
		// 더이상 입력하지 않도록 입력 종료하는것.

		System.out.println("==프로그램 끝==");
	}

	private void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");
		articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 8));
		articles.add(new Article(1, Util.getNowDateStr(), "제목2", "내용2", 9));
		articles.add(new Article(1, Util.getNowDateStr(), "제목3", "내용3", 10));
		// new Article 할 때마다 인자 속 애들이 생성되서 articles에 추가됨.
	}

}
