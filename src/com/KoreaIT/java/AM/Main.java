package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		int 마지막_번호 = 0;
		
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
			System.out.printf("명령어 ) ");
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
			} System.out.printf("번호 | 제목\n");
				for (int i = articles.size()-1; i >=0; i--) {
				Article article = articles.get(i);
				// i가 변할 때마다 노트에 작성중(기억중)
				// article size가 3이면 index는 0,1,2 있으므로
				// size - 1 부터 시작해야됨!
				// 글은 보통 최신순으로 있기때문에 거꾸로 나타내야됨!
				System.out.printf("%d | %s\n", article.id, article.title);
			}
			}
			
			else if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
				// 아무것도 안쓰고 엔터치면 명령어 쓰라고 해야됨.
			}
			
			
			else if (command.startsWith("article detail ")) {
				//article detail로 시작한다면~
				String[] commandBits = command.split(" ");
				// 입력한걸 띄어쓰기를 기준으로 자르겠다.
				int id = Integer.parseInt(commandBits[2]);
				// 인덱스 두번째, 즉 세번째 조각을 id라고 보겠다.
				Article foundArticle = null;
				for( int i = 0; i < articles.size();i++) {
					Article article = articles.get(i);
					//for 반복문을 돌 때마다 arraylist을 순회(풀스캔)하며 i를 뽑아오겠다.
					if(article.id == id) {
						// 생성된 게시물의 번호와 commmandBits[2]와 똑같다면
						foundArticle = article;
						//찾았으면, foundArticle에 article을 덮어쓰겠다.
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
					continue;
				} 
				// id 찾고 article에 연결되서 foundArticle에 넣는다면~
					System.out.printf("%d번 게시물은 존재합니다.\n", id);
					System.out.printf("번호 : %d\n",foundArticle.id);
					System.out.printf("날짜 : %s\n",foundArticle.regDate);
					System.out.printf("제목 : %s\n",foundArticle.title);
					System.out.printf("내용 : %s\n",foundArticle.body);
			}
			
			
			else if (command.startsWith("article modify ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = null;
				for( int i = 0; i < articles.size();i++) {
					Article article = articles.get(i);
					if(article.id == id) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				//원래 있던 게시물에 덮어쓰기 해주기!!
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				foundArticle.title = title;
				foundArticle.body = body;
				System.out.printf("%d번 게시물을 수정했습니다.\n",id);
			}
			
			
			else if (command.equals("article write")) {
				String regDate = Util.getNowDateStr();
				// 설계도에게 바로 일 시키려면 쩜 누르면됨. static 하면됨
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				// 반복문 밖에서 id 0이었으므로 1 더해줘야됨
				int id = 마지막_번호 + 1;
				System.out.printf("%d번글이 생성되었습니다.\n", id);
				마지막_번호 = id;
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
			
			
			else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = null;
				int foundIndex = -1;
				//새로운 변수 도입해서 아무값도 아닌 -1 넣어주기
				for( int i = 0; i < articles.size();i++) {
					Article article = articles.get(i);
					//for 반복문을 돌 때마다 arraylist을 순회(풀스캔)하며 i를 뽑아오겠다.
					if(article.id == id) {
						foundIndex = i;
						//일치하는거 찾으면 foundIndex에 그 글의 i 도입하기
						//예를 들어 1번 2번 글 쓰고 삭제한 수 3번글 다시 쓰면
						//3번 글의 index는 0번이 되므로 id는 3번이고 index는 0인 글 지우면됨.
						break;
					}
				}
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
					continue;
				} 
				articles.remove(foundIndex);
				System.out.printf("%d번 게시물을 삭제하였습니다.\n",id);
				
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
	String regDate;
	// 아까 article 변수에 인자 담는다고 했어서
	// 그 인자들 타입 선언해주기
	
	
	public Article(int id, String regDate, String title, String body) {
		// 생성자 앞에는 되도록 public 붙여주기
				// 인자와 매개변수 연결시키기
				this.id = id;
				this.title = title;
				this.body = body;
				this.regDate = regDate;
				// this 뒤에 있는건 이 클래스에서 선언한 변수들.
				// = 오른쪽에 있는건 매개변수에서 받는것.
				// 문자가 똑같으니까 좀 헷갈린다..	}
	}
}