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

		
		List<Article> articles = new ArrayList<>();
		
		
		while (true) {
			System.out.println("명령어 ) ");
			String command = sc.nextLine().trim();

			if (command.equals("article list")) {
				if (articles.size() == 0) {
				System.out.println("게시글이 없습니다.");
				continue;
			} else {
				System.out.println("게시글이 있습니다.");
			}
			}
			
			else if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			else if (command.equals("article write")) {

				System.out.println("제목 : ");
				String title = sc.nextLine();
				System.out.println("내용 : ");
				String body = sc.nextLine();
				마지막_번호 = id + 1;
				System.out.printf("%d번글이 생성되었습니다.\n", 마지막_번호);
				id = 마지막_번호;

				Article article = new Article(id, title, body);
				articles.add(article);
			}

			else if (command.equals("System exit")) {
				break;
			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		sc.close();
		System.out.println("==프로그램 끝==");
	}
}
class Article {
	int id;
	String title;
	String body;
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
		
	}
}