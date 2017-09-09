package memo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

//�����͸� �����ϴ� ����Ҹ� �����ϴ� ��ü
public class Model {
	private final String DB_DIR  = "c:/workspaces/java/database";
	private final String DB_FILE = "memo.txt";
	//                     mac ��       "/workspaces/java/database"
	private File database = null;
	
	// ��ü �޸� �����ϴ� �����
	ArrayList<Memo> list = new ArrayList<>();
	// ������ �۹�ȣ
	int lastIndex = 0;
	
	public Model(){
		// new �ϴ� ���� �� ������ ����ȴ�.
		File dir = new File(DB_DIR);
		// ���丮�� ��������
		if(!dir.exists()){
			dir.mkdirs(); // ��λ� ���丮�� ������ �ڵ�����
		}
		// window     = \
		// mac        = /
		// unix,linux = /
		File file = new File(DB_DIR + File.separator + DB_FILE);
		// ������ ��������
		if(!file.exists()){
			try{
				file.createNewFile();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		database = file;
	}
	
	private final String COLUMN_SEP = "::";
	
	// ����
	public void create(Memo memo){
		//�۹�ȣ
		memo.no = ++lastIndex;

		// 1. ���� ��Ʈ���� ����
		try(FileOutputStream fos = new FileOutputStream(database, true)) {
			// ������ ������ �����ڷ� �и��Ͽ� ������ ���ڿ��� �ٲ۴�.
			String row = memo.no + COLUMN_SEP 
					+ memo.name + COLUMN_SEP 
					+ memo.content + COLUMN_SEP 
					+ memo.datetime + "\n";
			// 2. ��Ʈ���� �߰�ó��... (�ؽ�Ʈ�� ���ڵ��� ����...)
			OutputStreamWriter osw = new OutputStreamWriter(fos); // ���۽�Ʈ��
			// 3. ����ó��... (�������)
			BufferedWriter bw = new BufferedWriter(osw);
			bw.append(row);
			bw.flush();   //�����ִ� �����͸� ó��
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// �� �ϳ��� ������ �޸𸮸� ����ҷ� �̵�
		// list.add(memo);
	}
	// �б�
	public Memo read(int no){
		for(Memo memo : list){
			if(memo.no == no){
				return memo;
			}
		}
		return null;
	}
	// ����
	public void update(Memo memo){
		
	}
	// ����
	public void delete(int no){
		for(Memo memo : list){
			if(memo.no == no){
				list.remove(memo);
			}
		}
	}
	// ���
	public ArrayList<Memo> getList(){
		
		// �����Ͱ� �ߺ��ؼ� ������ �ʵ��� ����Ҹ� �����ִ� �۾��� �ʿ��� ��찡 �ִ�.
		list.clear();
		
		// 1. �д� ��Ʈ���� ����
		try(FileInputStream fis = new FileInputStream(database)){ // try-with ������ �ڵ����� fis.close�� �߻�
			// 2. ���� ���� ���ڵ��� �ٲ��ִ� ���� Ŭ������ ���
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			// 3. ����ó��
			BufferedReader br = new BufferedReader(isr);
			
			String row;
			// ���ο� ���� ���پ� �о row�� �����ϰ�
			// �� �̻� ���� �����Ͱ� ������ null�� ���ϵǹǷ� ������ ����ȴ�.
			while( (row = br.readLine()) != null ){
				String tempRow[] = row.split(COLUMN_SEP);
				// 1::fds::fdsaf::1504839497021
				// tempRow[0] = 1
				// tempRow[1] = fds 
				// tempRow[2] = fdsaf
				// tempRow[3] = 1504839497021
				Memo memo = new Memo();
				memo.no = Integer.parseInt(tempRow[0]);
				memo.name = tempRow[1];
				memo.content = tempRow[2];
				memo.datetime = Long.parseLong(tempRow[3]);
				
				list.add(memo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
}