package castle;

import java.util.HashMap;
import java.util.Scanner;

import javax.crypto.NullCipher;

import map.Feature;
import map.Room;

public class Game {
	private Room currentRoom;
	private HashMap<String, Handler> handlers = new HashMap<>();
	
	public Game(){
//		buildMap();
//		AddFeatures();
		AddHandlers();
//		currentRoom = outside;
	}
	
	
	private void AddHandlers() {
		handlers.put("退出", new Handler() {
			
			@Override
			public void doCmd() throws EndOfGameException {
				throw new EndOfGameException();
			}
			
		});
		
		handlers.put("帮助", new Handler(){

			@Override
			public void doCmd() throws EndOfGameException {
				System.out.println("你可以：");
				System.out.println("输入“帮助”、“退出”执行相应功能");
				System.out.println("输入物品或人物的名字，查看详细信息");
				System.out.println("输入出口，前进到对应房间");
				System.out.println();
			}
			
		});
		
	}

	public void run(){
		welcome();
		Scanner in = new Scanner(System.in);
		while(true){
			String str = in.nextLine().trim();
			Handler handler = handlers.get(str);
			if(handler != null){
				try{
					handler.doCmd();
				} catch(EndOfGameException e){
					break;
				}
			}
			else{
				Feature feature = currentRoom.get(str);
				feature.showDetail();
			}			
		}
		in.close();
	}
	
	public void welcome(){
		System.out.println("欢迎来到城堡！");
		System.out.println("这仍然是一个很无聊的游戏。");
		System.out.println("不过不妨走走看看，或许有意想不到的惊喜？\n");
		System.out.println("或者惊吓呢？");
		System.out.println("------------------------------------\n");
		System.out.println("你与小伙伴组团游一个荒僻的古堡，不小心走散了。");
		prompt();
		System.out.println("如果需要帮助，请输入“帮助”。");
	}

	public void prompt(){
		System.out.println("现在你在" + currentRoom);
		System.out.println("这里有：" + currentRoom.getItems());
		System.out.println("出口有：" + currentRoom.getExits());
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.run();
	}
}
