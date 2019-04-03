/**
 * Created by tanjinyu on 2016/10/12.
 * this is javascript library rely on Jquery.
 * tab切换+首张图片加载的Jquery库(精简版).
 * ps:其他功能待完善.
 */
~function( win ) {
    function Qtab ( opts ) {
        //默认参数
        this.config = $.extend( {}, {
            classActive : '',
            tabBtn : null,   //选项卡按钮
            tabContent : null,  //选项卡切换内容
            tabBtnActive : 'current',   //选项卡按钮状态class
            tabHandlerType : 'click',   //选项卡触发类型
            isMove : false          //默认切换是无动画直接显示隐藏，如果要切换内容的时候自己写其他效果，要设置true
        }, opts);
        var _this = this;
        var config = this.config;
        this._aImg = config.tabContent.find('img');
        this._btn = config.tabBtn;
        this._content = config.tabContent;
        this._class = config.tabBtnActive;
        this._type = config.tabHandlerType;

        config.tabBtn [ this._type ] (function () {
            var index = config.classActive==''?$(this).index():$(this).index(config.classActive);
            var oImg = _this._aImg.eq(index);
            var _src;

            if( oImg.attr('_src') ) {
                _src = oImg.attr('_src');
                oImg[0].src = _src;
                oImg.removeAttr('_src');
            }

            $(this).addClass( _this._class).siblings().removeClass( _this._class );

            if( config.isMove == false ) {
                _this._content.hide().eq(index).show();
            }

        });
    };

    win.Qtab = Qtab;

}(window)