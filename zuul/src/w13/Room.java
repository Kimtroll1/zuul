package w13;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

public class Room {
	private String description; // 이 Room에 대한 설명.
	private Map<String, Room> exits = new HashMap<>();
	private ArrayList<Item> items = new ArrayList<>();

	/**
	 * "description" 설명에 해당하는 Room을 구성한다. 초기에는 exit을 갖지 않는다. "description"은 가령
	 * "과사무실", "실습실", "동아리방" 같은 것이다.
	 * 
	 * @param description 이 방에 관한 설명.
	 */
	public Room(String description) {
		this.description = description;
	}

	/**
	 * 이 Room의 출구들 중 하나를 정해 준다.
	 * @param direction 출구 방향.
	 * @param neighbor 지정된 방향의 출구에 연결된 Room.
	 */
	public void setExit(String direction, Room neighbor) {
		if (neighbor != null) {
			exits.put(direction, neighbor);
		}
	}
	
	/**
	 * 이 Room의 Item을 설정한다.
	 * @param item
	 */
	public void addItem(Item item) {
		items.add(item);
	}
	
	/**
	 * 지정된 방향으로 나가려고 할 때 연결된 Room을 알려준다
	 * @param direction 나가려고 하는 방향 (north, east, south, west)
	 * @return 나가려고 하는 방향으로 연결된 Room (없으면 null)
	 */
	public Room getExit(String direction) {
		return exits.get(direction);
	}
	
	/**
	 * 방의 출구들을 알려주는 문자열을 반환한다.
	 * 문자열 예 : "Exits: east south west "
	 * @return 출구가 있는 방향들을 알려주는 문자열.
	 */
	public String getExitString() {
		StringBuilder s = new StringBuilder("Exits: ");
		
		Set<String> keys = exits.keySet();
		
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			s.append(it.next() + " ");
		}
		
		return s.toString();
	}

	/**
	 * @return The description of the room.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Room의 상세한 정보를 모두 모은 문자열을 반환한다.
	 * @return
	 */
	public String getLongDescription() {
		StringBuilder s = new StringBuilder();
		s.append(description + ".\n");
		s.append(getExitString());
		if (items.size() != 0) {
			s.append("\n<Item>");
			Iterator<Item> it = items.iterator();
			while (it.hasNext()) {
				s.append("\n" + it.next().getLongDescription());
			}
		}
		
		return s.toString();
	}

	public Item removeItem(String name) {
		Iterator<Item> it = items.iterator();
		while (it.hasNext()) {
			Item item = it.next();
			if (item.getName().equals(name)) {
				it.remove();
				return item;
			}
		}
		
		return null;
	}
}
