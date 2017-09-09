package memo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ������ ����ϴ� ��ü
 * @author pc
 *
 */
public class Control {
	
	ModelWithDB model;
	View view;
	
	public Control(ModelWithDB model, View view){
		this.model = model;
		this.view = view;
	}
	
	public void process(){
		Scanner scanner = new Scanner(System.in);

		// ��ɾ �Է¹޾Ƽ� �ļ� ó��
		// c - create : ������ �Է¸��� ��ȯ
		// r - read   : ������ �б���� ��ȯ
		// u - update : ������ �������� ��ȯ
		// d - delete : ������ �������� ��ȯ
		String command = "";
	    
		while(!command.equals("exit")){
			view.println("-------- ��ɾ��� �Է��ϼ��� ---------");
			view.println("c : ����, r : �б�, u : ����, d : ����, l : ���");
			view.println("exit : ����");
			view.println("-------------------------------");
			command = scanner.nextLine(); 
			// ��ɾ �б�ó��
			switch(command){
			case "c":
				Memo memo = view.create();
				model.create(memo);
				break;
			case "r":
				int readNo = view.readMemoNo();
				if(readNo < 0){ // ��� ����ó�� �ʿ�
					view.println("�۹�ȣ�� �߸��Ǿ����ϴ�.");
					break;
				}
				Memo readMemo = model.read(readNo);
				view.showMemo(readMemo);
				break;
			case "u":
				int updateNo = view.readMemoNo();
				Memo updateMemo = model.read(updateNo);
				view.update(updateMemo);
				break;
			case "d":
				int deleteNo = view.readMemoNo();
				model.delete(deleteNo);
				break;
			case "l":
				ArrayList<Memo> list = model.getList();
				view.showList(list);
				break;
			}
		}
		
		view.println("�ý����� ����Ǿ����ϴ�!");
	}
}