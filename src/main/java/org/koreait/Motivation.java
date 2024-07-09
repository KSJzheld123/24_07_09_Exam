package org.koreait;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Motivation {

    Scanner sc = new Scanner(System.in);
    List<MotivationList> motivationLists = new ArrayList<>();
    int lastId = 0;
    int cutingId = -1;
    String cmd = "";

    void run() {
        System.out.printf("명령어 ) ");

        cmd = sc.nextLine().trim();

        if (cmd.contains("?id=")) {
            String[] cmdBits = cmd.split("=");
            try {
                cutingId = Integer.parseInt(cmdBits[1]);
            } catch (NumberFormatException e) {
                System.out.println("Id 검색은 숫자만 가능합니다");
                run();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("명령어만 입력하고 숫자는 입력되지 않았습니다");
                run();
            }
        }

        boolean loop = true;

        while (loop) {

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요");
                run();
            }

            if (cmd.equals("exit")) {
                System.out.println("시스템 종료");
                loop = false;
            }

            if (cmd.equals("등록")) {
                creat();
            } else if (cmd.equals("목록")) {
                listAll();
            } else if (cmd.startsWith("상세보기")) {
                list();
            } else if (cmd.startsWith("수정")) {
                update();
            } else if (cmd.startsWith("삭제")) {
                delete();
            } else {
                System.out.println("명령어를 다시 입력해주세요");
                run();
            }
        }
    }

    void delete() {
        if(cmd.contains("?id=")) {
            for (int i = 0; i < motivationLists.size(); i++) {
                if (cutingId == motivationLists.get(i).id) {
                    System.out.println(motivationLists.get(i).id + "번 명언이 삭제되었습니다");
                    motivationLists.remove(i);
                    run();
                }
            }
            System.out.println(cutingId + "번 명언은 존재하지 않습니다");
            run();
        } else if (cutingId == -1) {
            System.out.println("삭제할 명언 id를 입력해주세요");
            run();
        }
    }

    void update() {
        if(cmd.contains("?id=")) {
            for (int i = 0; i < motivationLists.size(); i++) {
                if (cutingId == motivationLists.get(i).id) {
                    System.out.println("명언(기존) : " + motivationLists.get(i).body);
                    System.out.println("작가(기존) : " + motivationLists.get(i).source);
                    System.out.printf("명언 : ");
                    String body = sc.nextLine();
                    System.out.printf("작가 : ");
                    String source = sc.nextLine();
                    motivationLists.get(i).body = body;
                    motivationLists.get(i).source = source;
                    System.out.println(motivationLists.get(i).id + "번 명언이 수정되었습니다");
                    run();
                }
            }
            System.out.println(cutingId + "번 명언은 존재하지 않습니다");
            run();
        } else if (cutingId == -1) {
            System.out.println("수정할 명언 id를 입력해주세요");
            run();
        }
    }

    void list() {
        if(cmd.contains("?id=")) {
            for (int i = 0; i < motivationLists.size(); i++) {
                if (motivationLists.get(i).id == cutingId) {
                    System.out.println("번호 : " + motivationLists.get(i).id);
                    System.out.println("날짜 : " + motivationLists.get(i).date);
                    System.out.println("작가 : " + motivationLists.get(i).source);
                    System.out.println("내용 : " + motivationLists.get(i).body);
                    run();
                }
            }
            System.out.println(cutingId + "번 명언은 존재하지 않습니다");
            run();
        } else if (cutingId == -1) {
            System.out.println("상세보기할 명언 id를 입력해주세요");
            run();
        }

    }

    void listAll() {
        if (motivationLists.size() == 0) {
            System.out.println("명언이 없습니다");
            run();
        }

        System.out.println("번호   ||     작가     ||     명언");
        for (int i = motivationLists.size() - 1; i >= 0; i--) {
            System.out.printf("%d   ||     %s     ||     %s\n", motivationLists.get(i).id, motivationLists.get(i).source, motivationLists.get(i).body);
        }
        run();
    }

    void creat() {
        Util now = new Util();
        int id = lastId + 1;
        String date = String.valueOf(now.dateFormat.format(now.today));
        System.out.printf("명언 : ");
        String body = sc.nextLine();
        System.out.printf("작가 : ");
        String source = sc.nextLine();
        MotivationList motivationList = new MotivationList(id, body, source, date);
        motivationLists.add(motivationList);
        System.out.println(id + "번 명언이 등록되었습니다");
        lastId++;
        run();
    }
}
