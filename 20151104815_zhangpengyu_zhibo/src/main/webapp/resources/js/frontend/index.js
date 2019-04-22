$(function() {
    var url = '/20151104815_zhangpengyu_zhibo/frontend/listmainpageinfo';

    $.getJSON(url, function (data) {
        if (data.success) {
            var headLineList = data.headLineList;
            var swiperHtml = '';
            //轮播图
            headLineList.map(function (item, index) {
                swiperHtml += ''
                            + '<div class="swiper-slide img-wrap">'
                            + '<a href="' + item.lineLink + '" external>'
                            +      '<img class="banner-img" src="'+ item.lineImg +'" alt="'+ item.lineName +'">'
                            + '</a></div>';
            });
            $('.swiper-wrapper').html(swiperHtml);
            $(".swiper-container").swiper({
            	//轮播图定时2秒
                autoplay: 2000,
                //操作时是否停止
                autoplayDisableOnInteraction: false
            });
            var roomCategoryList = data.roomCategoryList;
            var categoryHtml = '';
            roomCategoryList.map(function (item, index) {
                categoryHtml += ''
                     +  '<div class="col-50 room-classify" data-category='+ item.roomCategoryId +'>'
                     +      '<div class="word">'
                     +          '<p class="room-title">'+ item.roomCategoryName +'</p>'
                     +          '<p class="room-desc">'+ item.roomCategoryDesc +'</p>'
                     +      '</div>'
                     +      '<div class="room-classify-img-warp">'
                     +          '<img class="room-img" src="'+ item.roomCategoryImg +'">'
                     +      '</div>'
                     +  '</div>';
            });
            //赋给HTML控件
            $('.row').html(categoryHtml);
        }
    });
    //侧栏
    $('#me').click(function () {
        $.openPanel('#panel-right-demo');
    });

    $('.row').on('click', '.room-classify', function (e) {
        var roomCategoryId = e.currentTarget.dataset.category;
        var newUrl = '/20151104815_zhangpengyu_zhibo/frontend/roomlist?parentId=' + roomCategoryId;
        window.location.href = newUrl;
    });

});
