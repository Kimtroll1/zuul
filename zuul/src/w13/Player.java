package w13;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
public class Player {
	private Room currentRoom;
	private int maxWeight;
	private Stack<Room> pastRooms;
	private ArrayList<Item> items = new ArrayList<>();

	
	public Player(Room startRoom, int maxWeight) {
		currentRoom = startRoom;
		pastRooms = new Stack<Room>();
		this.maxWeight = maxWeight;
	}
	
	public int getMaxWeight() {
		return maxWeight;
	}
	
	/**
	 * 주어진 방향으로 옮겨간다.
	 * 그 방향으로 출구가 없으면 현재 위치에 머문다.
	 * @param direction 방향
	 * @return 성공 시 0, 실패 시 -1.
	 */
	public int moveTo(String direction) {
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null) {
			return -1;
		} else {
			pastRooms.push(currentRoom);
			currentRoom = nextRoom; // 방을 변경
			return 0;
		}
	}
	
	/**
	 * 선수가 현재 있는 방을 반환한다.
	 * @return 방.
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * 이전 방으로 돌아간다.
	 */
	public void back() {
		if (!pastRooms.isEmpty()) {
			currentRoom = pastRooms.pop();
		}
	}
	
	public List<Item> getItems() {
		return Collections.unmodifiableList(items);
	}
	
	public Item takeItem(String name) {
		Item item = currentRoom.removeItem(name);
		
		if (item != null) {
			if (pickable(item)) {
				items.add(item);
			} else {
				currentRoom.addItem(item);
				item = null;
			}
		}
		
		return item;
	}
	
	public Item dropItem(String name) {
		Iterator<Item> it = items.iterator();
		
		while(it.hasNext()) {
			Item item = it.next();
			if(item.getName().equals(name)) {
				it.remove();
				currentRoom.addItem(item);
				return item;
			}
		}
		
		return null;
	}
	
	private int totalWeight() {
		Iterator<Item> it = items.iterator();
		int sum = 0;
		
		while(it.hasNext()) {
			sum += it.next().getWeight();
		}
		
		return sum;
	}
	
	private boolean pickable(Item item) {
		if (item.getWeight() + totalWeight() > maxWeight) {
			return false;
		} else {
			return true;
		}
	}
}
