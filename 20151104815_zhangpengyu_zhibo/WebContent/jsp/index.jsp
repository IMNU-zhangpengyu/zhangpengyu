<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>直播</title>
        
        <meta charset="utf-8">
        <link href="http://vjs.zencdn.net/5.5.3/video-js.css" rel="stylesheet">
        <!-- If you'd like to support IE8 -->
        <script src="http://vjs.zencdn.net/ie8/1.1.1/videojs-ie8.min.js"></script>
        <style type="text/css">
			body{
			background-image: url(${pageContext.request.contextPath}/img/index2.jpg);
	
			background-repeat: no-repeat;
			}
			
		</style>


    </head>
    <body>
       <div>
			<%@ include file="/jsp/header.jsp" %>
			<div>
		        <video 
		            id="my-video" 
		            class="video-js" 
		            controls 
		            preload="auto"
		            width="850" 
		            height="550"
		            poster="http://ppt.downhot.com/d/file/p/2014/08/12/9d92575b4962a981bd9af247ef142449.jpg"
		            data-setup="{}"
		            style="left:25%;top:100px;">
		
		            <!-- 直播地址 -->
		            <source src="rtmp://127.0.0.1/live/aaaa" type="rtmp/flv">
		
		        </video>
		
		        <!-- 引入video.js -->
		        <script src="http://vjs.zencdn.net/5.5.3/video.js"></script>
		
		        <!-- 自动播放 -->
		        <script type="text/javascript">
		            var myPlayer = videojs('my-video');
		            videojs("my-video").ready(function(){
		                var myPlayer = this;
		                myPlayer.play();
		            });
		        </script> 
	        </div>
        </div>
    </body>
</html>