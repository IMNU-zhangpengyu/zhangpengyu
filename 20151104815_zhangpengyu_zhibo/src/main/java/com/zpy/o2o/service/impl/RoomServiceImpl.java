package com.zpy.o2o.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zpy.o2o.dao.RoomDao;
import com.zpy.o2o.dto.RoomExecution;
import com.zpy.o2o.entity.Room;
import com.zpy.o2o.enums.RoomStateEnum;
import com.zpy.o2o.exceptions.RoomOperationException;
import com.zpy.o2o.service.RoomService;
import com.zpy.o2o.util.ImageUtil;
import com.zpy.o2o.util.PageCalculator;
import com.zpy.o2o.util.PathUtil;

@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomDao roomDao;

	@Override
	public RoomExecution getRoomList(Room roomCondition, int pageIndex, int pageSize) {
		//分页
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Room> roomList = roomDao.queryRoomList(roomCondition, rowIndex, pageSize);
		int count = roomDao.queryRoomCount(roomCondition);
		RoomExecution se = new RoomExecution();
		if(roomList != null) {
			se.setRoomList(roomList);
			se.setCount(count);
		}else {
			se.setState(RoomStateEnum.INNER_ERROR.getState());
		}
		return se;
	}
	
	@Override
	@Transactional
	public RoomExecution addRoom(Room room, InputStream roomImgInputStream, String fileName) {
		// 空值判断
		if (room == null) {
			return new RoomExecution(RoomStateEnum.NULL_ROOM);
		}
		try {
			// 赋值
			room.setEnableStatus(0);
			room.setCreateTime(new Date());
			room.setLastEditTime(new Date());
			// 添加房间信息
			int effectedNum = roomDao.insertRoom(room);

			if (effectedNum <= 0) {
				throw new RoomOperationException("房间创建失败");
			} else {
				if (roomImgInputStream != null) {
					// 存储图片
					try {
						addRoomImg(room, roomImgInputStream, fileName);
					} catch (Exception e) {
						throw new RoomOperationException("addRoomImg error:" + e.getMessage());
					}
					// 更新room图片地址
					effectedNum = roomDao.updateRoom(room);
					if (effectedNum <= 0) {
						throw new RoomOperationException("更新room图片地址失败");
					}
				}
			}
		} catch (Exception e) {
			throw new RoomOperationException("addRoom error:" + e.getMessage());
			// return null;
		}

		return new RoomExecution(RoomStateEnum.CHECK, room);
	}

	private void addRoomImg(Room room, InputStream roomImgInputStream, String fileName) {
		// 获取room目录相对路径
		String dest = PathUtil.getRoomImagePath(room.getRoomId());
		String roomImgAddr = ImageUtil.generateThumbnail(roomImgInputStream, fileName, dest);
		room.setRoomImg(roomImgAddr);
	}

	@Override
	public Room getByRoomId(long roomId) {
		return roomDao.queryByRoomId(roomId);
	}

	@Override
	public RoomExecution modifyRoom(Room room, InputStream roomImgInputStream, String fileName)
			throws RoomOperationException {
		if (room == null || room.getRoomId() == null) {
			return new RoomExecution(RoomStateEnum.NULL_ROOM);
		} else {
			// 1判断是否需要处理图片
			try {
				if (roomImgInputStream != null && fileName != null && !"".equals(fileName)) {
					Room tempRoom = roomDao.queryByRoomId(room.getRoomId());
					if (tempRoom.getRoomImg() != null) {
						//先删除再添加
						ImageUtil.deleteFileOrPath(tempRoom.getRoomImg());
					}
					addRoomImg(room, roomImgInputStream, fileName);
				}
				// 2更新
				room.setLastEditTime(new Date());
				int effctadNum = roomDao.updateRoom(room);
				if (effctadNum <= 0) {
					return new RoomExecution(RoomStateEnum.INNER_ERROR);
				} else {
					room = roomDao.queryByRoomId(room.getRoomId());
					return new RoomExecution(RoomStateEnum.SUCCESS, room);
				}
			} catch (Exception e) {
				throw new RoomOperationException("modifyRoom error:" + e.getMessage());
			}
		}
	}

}
