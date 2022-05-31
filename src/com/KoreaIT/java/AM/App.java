package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.MemberController;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class App {
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
		ArticleController articleController = new ArticleController();

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

			if (command.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
					// 게시글이 없다고만 하고 끝나지 않고
					// 다시 스킵하고 위로 올라가도록 continue 해줘야됨.
				}
				String searchKeyword = command.substring("article list".length()).trim();
				List<Article> forPrintArticles = articles;
				if (searchKeyword.length() > 0) {
					forPrintArticles = new ArrayList<>();
					// 검색어가 있으면 새 창고 하나 지어놓는다
					for (Article article : articles) {
						if (article.title.contains(searchKeyword)) {
							forPrintArticles.add(article);
							// 만약에 검색어와 일치하는게 제목에 있으면 창고에 넣는다.
						}
					}
					if (forPrintArticles.size() == 0) {
						System.out.println("검색 결과가 없습니다.");
						// 검색어가 없으면 새 창고를 안 만드니까 forPrintArticles 크기0.
					}
				}
				System.out.printf("번호  | 제목   | 조회 \n");
				for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
					Article article = forPrintArticles.get(i);
					// i가 변할 때마다 노트에 작성중(기억중)
					// article size가 3이면 index는 0,1,2 있으므로
					// size - 1 부터 시작해야됨!
					// 글은 보통 최신순으로 있기때문에 거꾸로 나타내야됨!
					System.out.printf("%5d | %5s  |  %4d\n", article.id, article.title, article.hit);
				}
			}

			else if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
				// 아무것도 안쓰고 엔터치면 명령어 쓰라고 해야됨.
			}

			else if (command.startsWith("article detail ")) {
				// article detail로 시작한다면~
				String[] commandBits = command.split(" ");
				// 입력한걸 띄어쓰기를 기준으로 자르겠다.
				int id = Integer.parseInt(commandBits[2]);
				// 인덱스 두번째, 즉 세번째 조각을 id라고 보겠다.
				Article foundArticle = getArticleById(id);
				// get 함수로 Article타입을 찾아서 foundArticle에 넣겠다.(id로 찾겠다)

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				foundArticle.increaseHit();
				// increaseHit 함수 버튼 누를 때마다 조회수 하나씩 증가함
				// id 찾고 article에 연결되서 foundArticle에 넣는다면~
				System.out.printf("%d번 게시물은 존재합니다.\n", id);
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회 : %s\n", foundArticle.hit);
			}

			else if (command.startsWith("article modify ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = getArticleById(id);
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				// 원래 있던 게시물에 덮어쓰기 해주기!!
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				foundArticle.title = title;
				foundArticle.body = body;
				System.out.printf("%d번 게시물을 수정했습니다.\n", id);
			}

			else if (command.equals("article write")) {
				String regDate = Util.getNowDateStr();
				// 설계도에게 바로 일 시키려면 쩜 누르면됨. static 하면됨
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				// 반복문 밖에서 id 0이었으므로 1 더해줘야됨
				int id = articles.size() + 1;
				System.out.printf("%d번글이 생성되었습니다.\n", id);
				// 만약에 그냥 마지막_번호++ 이라고 적으면
				// 그건 id와는 전혀 상관없는 일이 된다.
				// 그래서 id도 +1 해주던지 아니면 id에 마지막_번호 덮어쓰기
				Article article = new Article(id, regDate, title, body);
				// 객체 리모콘을 담을 변수 article 생성.
				// 인자로 번호, 날짜, 제목, 내용이 있음
				articles.add(article);
				// Article 클래스만 담을 arraylist랑 연결한 변수 articles에
				// article 변수가 나타내는 인자 가진 Article을 담겠다??
				// 여기는 해석이 잘.. 안된다.
			}

			else if (command.equals("member join")) {
				
				memberController.doJoin();
				
			}

			else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				int foundIndex = getArticleIndexById(id);
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				articles.remove(foundIndex);
				System.out.printf("%d번 게시물을 삭제하였습니다.\n", id);

			}

			else if (command.equals("System exit")) {
				break; // 반복문 평생 실행되지 않도록
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

	
	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Article getArticleById(int id) {
		int index = getArticleIndexById(id);
		if (index != -1) {
			return articles.get(index);
		}
		return null;

//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//			// for 반복문을 돌 때마다 arraylist을 순회(풀스캔)하며 i를 뽑아오겠다.
//			if (article.id == id) {
//				// 생성된 게시물의 번호와 commmandBits[2]와 똑같다면
//				return article;
//				// 찾았으면, foundArticle에 article을 덮어쓰겠다.
//			}
//		}

//		for (Article article : articles) {
//			if (article.id == id) {
//				return article;
//			}
//		}
//		return null;

	}

	private void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");
		articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 8));
		articles.add(new Article(1, Util.getNowDateStr(), "제목2", "내용2", 9));
		articles.add(new Article(1, Util.getNowDateStr(), "제목3", "내용3", 10));
		// new Article 할 때마다 인자 속 애들이 생성되서 articles에 추가됨.
	}

}
