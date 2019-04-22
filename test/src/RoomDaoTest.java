import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zpy.ssm.dao.RoomDao;
import cn.zpy.ssm.po.Room;
import cn.zpy.ssm.po.Roomtype;
import cn.zpy.ssm.po.User;

public class RoomDaoTest extends BaseTest{
	@Autowired
	private RoomDao roomDao;

	@Test
	public void testInsertRoom() throws Exception {
		Room room = new Room();
		User owner = new User(); 
		Roomtype sc = new Roomtype();
		
		owner.setUserid(2);
		sc.setRoomtypeid(1);
		room.setRoomname("mytest1");
		room.setRoomdesc("mytest1");
		room.setRoomimg("test1");
		room.setCreatetime(new Date());
		room.setLastedittime(new Date());
		room.setEnablestatus(1);
		room.setAdvice("…Û∫À÷–");
	
		int effectedNum = roomDao.insertRoom(room);
	
	}
}
