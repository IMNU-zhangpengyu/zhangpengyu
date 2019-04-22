package cn.zpy.ssm.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.zpy.ssm.dao.RoomDao;
import cn.zpy.ssm.dto.RoomExecution;
import cn.zpy.ssm.enums.RoomStateEnum;
import cn.zpy.ssm.po.Room;
import cn.zpy.ssm.service.RoomService;



@Service
public class RoomServiceImpl implements RoomService {
		@Autowired
		private RoomDao roomDao;
	/*	@Autowired
		private RoomAuthMapDao roomAuthMapDao;
		@Autowired
		private RoomCategoryDao roomCategoryDao;

		@Override
		public RoomExecution getRoomList(Room roomCondition, int pageIndex,
				int pageSize) {
			int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
			List<Room> roomList = roomDao.queryRoomList(roomCondition, rowIndex,
					pageSize);
			int count = roomDao.queryRoomCount(roomCondition);
			RoomExecution se = new RoomExecution();
			if (roomList != null) {
				se.setRoomList(roomList);
				se.setCount(count);
			} else {
				se.setState(RoomStateEnum.INNER_ERROR.getState());
			}
			return se;
		}

		@Override
		public RoomExecution getByEmployeeId(long employeeId)
				throws RuntimeException {
			List<Room> roomList = roomDao.queryByEmployeeId(employeeId);
			RoomExecution se = new RoomExecution();
			se.setRoomList(roomList);
			return se;
		}

		@Override
		public Room getByRoomId(long roomId) {
			return roomDao.queryByRoomId(roomId);
		}
*/
		@Override
		@Transactional
		/**
		 * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
		 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
		 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
		 */
		public RoomExecution addRoom(Room room,File roomimg) {
			if (room == null) {
				return new RoomExecution(RoomStateEnum.NULL_ROOM_INFO);
			}
			try {
				room.setEnablestatus(0);
				room.setCreatetime(new Date());
				room.setLastedittime(new Date());
				
				int effectedNum = roomDao.insertRoom(room);
				if (effectedNum <= 0) {
					throw new RuntimeException("店铺创建失败");
				} else {
			
						if (roomimg != null) {
							try {
						
							}catch (Exception e) {
								throw new RuntimeException("addRoomImg error: "
										+ e.getMessage());
							}
							effectedNum = roomDao.updateRoom(room);
							if (effectedNum <= 0) {
								throw new RuntimeException("创建图片地址失败");
							}
						}
				}
					} catch (Exception e) {
						throw new RuntimeException("addRoomImg error: "
								+ e.getMessage());
					}
					
							return new RoomExecution(RoomStateEnum.CHECK, room);
						
		}

	/*	@Override
		@Transactional
		public RoomExecution modifyRoom(Room room, CommonsMultipartFile roomImg)
				throws RuntimeException {
			if (room == null || room.getRoomId() == null) {
				return new RoomExecution(RoomStateEnum.NULL_SHOPID);
			} else {
				try {
					if (roomImg != null) {
						Room tempRoom = roomDao.queryByRoomId(room.getRoomId());
						if (tempRoom.getRoomImg() != null) {
							FileUtil.deleteFile(tempRoom.getRoomImg());
						}
						addRoomImg(room, roomImg);
					}
					room.setLastEditTime(new Date());
					int effectedNum = roomDao.updateRoom(room);
					if (effectedNum <= 0) {
						return new RoomExecution(RoomStateEnum.INNER_ERROR);
					} else {// 创建成功
						room = roomDao.queryByRoomId(room.getRoomId());
						return new RoomExecution(RoomStateEnum.SUCCESS, room);
					}
				} catch (Exception e) {
					throw new RuntimeException("modifyRoom error: "
							+ e.getMessage());
				}
			}
		}*/

		

	}


