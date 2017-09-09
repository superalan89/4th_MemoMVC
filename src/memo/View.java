package memo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ȭ���� ������� ����ϴ� ��ü
 * @author pc
 *
 */
public class View {
	// ��ĳ�� ��Ʈ�ѷ��� ���������� ����
	Scanner scanner = new Scanner(System.in);
	// Ű���� �Է��� �޴� �Լ�
	public Memo create(){
		// �� �ϳ��� �����ϱ� ���� �޸� ���� Ȯ��
		Memo memo = new Memo();
		
		println("�̸��� �Է��ϼ��� :");
		memo.name = scanner.nextLine();
		println("������ �Է��ϼ��� :");
		memo.content = scanner.nextLine();
		// ��¥
		memo.datetime = System.currentTimeMillis();
		
		return memo;
	}
	
	public int readMemoNo(){
		println("�� ��ȣ�� �Է��ϼ���");
		// ------ ���ڰ� �Էµ��� �ʾ��� ���� ���� ó�� --------------- //
		String tempNo = scanner.nextLine();
		try{
			return Integer.parseInt(tempNo);
		}catch(Exception e){
			return -1;
		}
	}
	
	public void showMemo(Memo memo){
		println("No:"+memo.no);
		println("Author:"+memo.name);
		println("Content:"+memo.content);
		
		// ���ڷ� �Է¹��� ��¥���� ���� ��¥�� ����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String formattedDate = sdf.format(memo.datetime);
		println("Date:"+formattedDate);
	}
	
	public void update(Memo memo){
		println("�̸��� �Է��ϼ��� :");
		memo.name = scanner.nextLine();
		println("������ �Է��ϼ��� :");
		memo.content = scanner.nextLine();
		// ��¥
		memo.datetime = System.currentTimeMillis();
	}
	
	public void delete(){
		
	}
	
	public void showList(ArrayList<Memo> list) {
		// ArrayList ����Ҹ� �ݺ����� ���鼭 ���پ� ���
		for(Memo memo : list){
			print("No:"+memo.no);
			print(" | Author:"+memo.name);
			println(" | Content:"+memo.content);
		}
	}
	
	public void print(String string){
		System.out.print(string);
	}
	
	public void println(String string){
		System.out.println(string);
	}
}