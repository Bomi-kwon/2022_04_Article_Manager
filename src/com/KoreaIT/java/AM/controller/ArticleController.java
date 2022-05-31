package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

public class ArticleController extends Controller {
	private Scanner sc;
	private List<Article> articles;
	private String command;
	private String actionMethodName;

	public ArticleController(Scanner sc, List<Article> articles) {
		// App에서 썼던거 인자와 매개변수로 받아줘야됨.
		this.sc = sc;
		this.articles = articles;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "write":
			doWrite();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		}
	}

	public void doWrite() {
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

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return;
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

	public void showDetail() {
		// article detail로 시작한다면~
		String[] commandBits = command.split(" ");
		// 입력한걸 띄어쓰기를 기준으로 자르겠다.
		int id = Integer.parseInt(commandBits[2]);
		// 인덱스 두번째, 즉 세번째 조각을 id라고 보겠다.
		Article foundArticle = getArticleById(id);
		// get 함수로 Article타입을 찾아서 foundArticle에 넣겠다.(id로 찾겠다)

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
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

	public void doDelete() {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		int foundIndex = getArticleIndexById(id);
		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		articles.remove(foundIndex);
		System.out.printf("%d번 게시물을 삭제하였습니다.\n", id);
	}

	public void doModify() {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		Article foundArticle = getArticleById(id);
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
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
	}
}
