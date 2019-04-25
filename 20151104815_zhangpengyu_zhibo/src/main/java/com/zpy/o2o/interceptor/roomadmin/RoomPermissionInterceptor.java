package com.zpy.o2o.interceptor.roomadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zpy.o2o.entity.Room;

public class RoomPermissionInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Room currentRoom = (Room) request.getSession().getAttribute(
				"currentRoom");
		@SuppressWarnings("unchecked")
		List<Room> roomList = (List<Room>) request.getSession().getAttribute(
				"roomList");
		if (currentRoom != null && roomList != null) {
			for (Room room : roomList) {
				if (room.getRoomId() == currentRoom.getRoomId()) {
					return true;
				}
			}
		}
		return false;
	}
}
