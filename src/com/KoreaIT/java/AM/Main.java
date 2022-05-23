package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		int 마지막_번호;
		int id = 0;
		// id는 꼭 반복문 밖에다가 정의해야한다!!
		// 안에서 정의하면 계속 0으로 초기화되기 때문!!

		
		List<Article> articles = new ArrayList<>();
		// article에 대한 정보를 저장할 노트가 필요하다
		// arraylist는 방칸수 제한없이 방을 만들수 있다!
		// Article 객체만 들어가는 arraylist를 만들어서
		// 변수 articles와 연결하겠다.
		
		
		while (true) {
			// true이므로 반복문은 계속 무한대로 돌아간다.
			// 그래서 나중에 반복문 깨고 나올 break를 꼭 해줘야함.
			System.out.println("명령어 ) ");
			String command = sc.nextLine().trim();
			// 내가 입력한 문장을 command라는 변수에 저장하겠음.
			// 한줄에 쓰는 문장 하나를 통째로 취급!
			// trim은 뒤에 띄어쓰기 날려주는것!!

			if (command.equals("article list")) {
				if (articles.size() == 0) {
				System.out.println("게시글이 없습니다.");
				continue;
				// 게시글이 없다고만 하고 끝나지 않고
				// 다시 스킵하고 위로 올라가도록 continue 해줘야됨.
			} else {
				System.out.println("게시글이 있습니다.");
			}
			}
			
			else if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
				// 아무것도 안쓰고 엔터치면 명령어 쓰라고 해야됨.
			}
			
			else if (command.equals("article write")) {

				System.out.println("제목 : ");
				String title = sc.nextLine();
				System.out.println("내용 : ");
				String body = sc.nextLine();
				// 반복문 밖에서 id 0이었으므로 1 더해줘야됨
				마지막_번호 = id + 1;
				System.out.printf("%d번글이 생성되었습니다.\n", 마지막_번호);
				id = 마지막_번호;
				// 만약에 그냥 마지막_번호++ 이라고 적으면
				// 그건 id와는 전혀 상관없는 일이 된다.
				// 그래서 id도 +1 해주던지 아니면 id에 마지막_번호 덮어쓰기

				Article article = new Article(id, title, body);
				// 객체 리모콘을 담을 변수 article 생성.
				// 인자로 번호, 제목, 내용이 있음
				articles.add(article);
				// Article 클래스만 담을 arraylist랑 연결한 변수 articles에
				// article 변수가 나타내는 인자 가진 Article을 담겠다??
				// 여기는 해석이 잘.. 안된다.
			}
			
			
			else if (command.equals("System exit")) {
				break; //반복문 평생 실행되지 않도록
				// System exit 명령어 치면 반복문 깨고 나오도록 꼭 써주기.
			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
				// 이상한 거 치면 존재하지 않는 명령어라고 막아야됨.
			}
		}
		sc.close();
		// 더이상 입력하지 않도록 입력 종료하는것.
		System.out.println("==프로그램 끝==");
	}
}
class Article {
	// 아까 new 써서 Article 객체 만들어서 class도 꼭 만들어야함.
	int id;
	String title;
	String body;
	// 아까 article 변수에 인자 담는다고 했어서
	// 그 인자들 타입 선언해주기
	
	public Article(int id, String title, String body) {
		// 생성자 앞에는 되도록 public 붙여주기
		// 인자와 매개변수 연결시키기
		this.id = id;
		this.title = title;
		this.body = body;
		// this 뒤에 있는건 이 클래스에서 선언한 변수들.
		// = 오른쪽에 있는건 매개변수에서 받는것.
		// 문자가 똑같으니까 좀 헷갈린다..
	}
}