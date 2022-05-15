package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MovieDao.UserDao;
import vo.UserVO;

public class MovieReview {

	static int select; // ���õǴ� ���� ���� ������ �ʿ� ���� ���⼭ ������ �� �ֽ��ϴ�.
	Scanner scanner = new Scanner(System.in);
	String loginInfo;

	private int initDisplay() {
		System.out.println("��ȭ���� ����Ʈ�� ���Ű��� ȯ���մϴ�");
		System.out.println("1. �α����ϱ�");
		System.out.println("2. �����ϱ�");
		select = scanner.nextInt();

		return select;
	}

	public MovieReview() {
		initDisplay();
		do {
			if (select == 1) {
				login();
			} else if (select == 2) {
				signup();
			}
		} while (select != 3);
		System.out.println("�߸��� �Է��Դϴ�");
		new MovieReview();

	}

	private void signup() { //
		/*
		 * TODO: ������ �����Է� �̹� ��ϵ� ���̵�� �α����ϰų� ���ư��� �����ϱ� ����ϸ� ó������ ���ư���
		 */
		List<UserVO> list = UserDao.getInstance().userList();
		List<String> memberList = new ArrayList<String>();
		for (UserVO vo : list) {
			memberList.add(vo.getId());
		}
		scanner.nextLine();
		System.out.println("���� �������Դϴ�");
		System.out.println("���̵� �Է��ϼ���");
		String id = scanner.nextLine();

		if (memberList.contains(id)) {
			System.out.println("�̹� ��ϵ� ���̵��Դϴ�.");
			System.out.println("1. �α����ϱ�");
			System.out.println("2. ���ư���");
			select = scanner.nextInt();
			if (select == 1) {
				login();
			} else if (select == 2) {
				new MovieReview();
			}
		}

		System.out.println("��й�ȣ�� �Է��ϼ���");
		String pwd = scanner.nextLine();

		System.out.printf("���̵�: %s\n��й�ȣ:%s\n�Է��Ͻ� ������ �����Ͻðڽ��ϱ�?\n", id, pwd);
		System.out.println("1. �����ϱ�");
		System.out.println("2. ���");
		select = scanner.nextInt();

		if (select == 1) {
			UserVO vo = new UserVO(id, pwd);
			UserDao.getInstance().insertSignUp(vo);
			System.out.println("���ԿϷ�");
			loginInfo = id;
			mainpage();
		} else if (select == 2) {
			new MovieReview();

		}
		
	}

	public void login() {
		/*
		 * TODO: ������ �α����ϱ� �����Է� ���̵��Է��ϸ� 1. �ε����� �ش��ϴ� ����������� 2. ���̵𰡾����� ���̵� Ʋ�Ƚ��ϴ� ����Է�
		 * 1. �ش��ϴ� ����� ������ ������������ �̵��ϱ� 2. ����� Ʋ���� ����� Ʋ�Ƚ��ϴ�
		 */
		scanner.nextLine();

		System.out.println("���̵� �Է��ϼ���");
		String id = scanner.nextLine();
		UserVO vo = new UserVO(id);

		List<UserVO> voList = UserDao.getInstance().checkUserInfo(vo); // �����������ϱ�
		try {
			if (voList.get(0).getId().equals(id)) {
				System.out.println("��й�ȣ�� �Է��ϼ���");
				String pwd = scanner.nextLine();
				if (voList.get(0).getPwd().equals(pwd)) {
					System.out.println("�α��� ����");
					this.loginInfo = voList.get(0).getId();
					mainpage();
				} else {
					System.out.println("��ġ�ϴ� ������ �����ϴ�.");
					login();
				}
			}
		} catch (Exception e) {
			System.out.println("��ġ�ϴ� ���̵� �����ϴ�. Ȩ�������� ���ư��ϴ�...");
			new MovieReview();
		}

	}

	private void mainpage() {

		System.out.printf("%s�� �ȳ��ϼ���\n", loginInfo);
		System.out.println("�����������Դϴ�");
		System.out.println("1. ��ȭ�����ϱ�");
		System.out.println("2. ���� �� �� ����");
		select = scanner.nextInt();

		if (select == 1) {
			selectMovies();
		} else if (select == 2) {
			showMyReviews();
		} else {
			System.out.println("�߸��� �Է��Դϴ�.");
			mainpage();
		}

	}

	private void selectMovies() { // TODO: �����
		System.out.println("selectMovies");
		System.out.println("��ȭ ����Ʈ");
		System.out.println("1. �۾���");
		System.out.println("2. ���� ����");
		System.out.println("3. ���ư���");
		select = scanner.nextInt();
		if (select == 1) {
			insertReview();
		} else if (select == 2) {
			selectReview();
		} else if (select == 3) {
			return;
		}
	}

	private void selectReview() { // TODO: ��ü���� �����ϱ��Դϴ�. �����

	}

	private void insertReview() { // TODO: �����
		System.out.println("�Է¿Ϸ�... ���亸��");
		selectReview();

	}

	private void showMyReviews() { // TODO: ���� �� ���� �����ϱ��Դϴ�. �����
		System.out.println("showMyReviews");
		System.out.println("1. �����ϱ�");
		System.out.println("2. �����ϱ�");
		System.out.println("3. ���ư���");
		select = scanner.nextInt();

		if (select == 1) {
			updateMyReview();
		} else if (select == 2) {
			deleteMyReview();
		} else if (select == 3) {
			return;
		}

	}

	private void deleteMyReview() { // TODO: �����
		System.out.println("������ ���� ��ȣ ����");
		mainpage();
	}

	private void updateMyReview() { // TODO: �����
		System.out.println("������ ���� ��ȣ ����");

	}

	public static void main(String[] args) {
		try {
			new MovieReview();
		} catch (Exception e) {
			System.out.println("�߸��� �Է��Դϴ�.");
			new MovieReview();
		}
	}

}
