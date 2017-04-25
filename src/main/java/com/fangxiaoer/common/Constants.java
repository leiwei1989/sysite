package com.fangxiaoer.common;

/**
 * Created by tiansai on 17/3/14.
 */
public class Constants {
    //测试地址
    //public static final String HOST = "http://10.7.0.61:8081";
    public static final String HOST = "http://ltapi.fangxiaoer.com";
    /**
     * sy.fangxiaoer.com接口
     */
    //检查是否是会员
    public static final String MEMBER_VALIDATE = HOST + "/apiv1/base/checkIsMember";

    //库存列表&推送列表查询
    public static final String VIEW_HOUSEINFO_LIST = HOST + "/apiv1/house/viewHouseInfoList";
    //房源置顶
    public static final String ADD_STICKORDER = HOST + "/apiv1/house/addStickOrder";
    //房源置顶列表
    public static final String VIEW_STICKHOUSE_LIST = HOST + "/apiv1/house/viewStickOrderHousesUnionAll";
    //微信支付
    public static final String WX_PAY = HOST + "/apiv1/other/wxPay";
    //支付宝支付
    public static final String ALI_PAY = HOST + "/apiv1/other/aliPay";
    //日志查询
    public static final String VIEW_AGENTLOG = HOST + "/apiv1/other/viewAgentLogToPC";
    //取消置顶
    public static final String UPDATE_ISSTICK = HOST + "/apiv1/house/updateIsStick";
    //置顶房源预约刷新
    public static final String ADD_HOUSE_REFRESH = HOST + "/apiv1/house/addHouseRefresh";
    //立即置顶金额
    public static final String VIEW_STICK_TIME_MONEY_DISCOUNT = HOST + "/apiv1/house/viewStickTimeMoneyDiscount";
    //用户余额
    public static final String VIEW_BALANCE = HOST + "/apiv1/house/viewBalance";
    //计划置顶金额
    public static final String VIEW_REFRESH_STICK_TIME_MONEY_DISCOUNT = "/apiv1/house/viewRefreshStickTimeMoneyDiscount";
    //经纪人站首页展示房源总数
    public static final String VIEW_HOUSE_COUNT = HOST + "/apiv1/house/viewHouseCount";
    //签到
    public static final String ADD_PROCESS = HOST + "/apiv1/other/addProcess";
    //发布租房信息
    public static final String ADD_RENT_HOUSE = HOST + "/apiv1/house/addRentHouse";
    //二手房详情页
    public static final String VIEW_SALEHOUSE_DETAIL = HOST + "/apiv1/house/viewHouseDetail";
    //租房详情页
    public static final String VIEW_RENTHOUSE_DETAIL = HOST + "/apiv1/house/viewRentHouseDetail";
    //二手房列表页
    public static final String VIEW_SALEHOUSE_LIST = HOST + "/apiv1/house/viewHouses";
    //租房列表页
    public static final String VIEW_RENTHOUSE_LIST = HOST + "/apiv1/house/viewRentHouseList";
    //经纪人房源情况
    public static final String VIEW_AGENT_DETAIL = HOST + "/apiv1/house/viewAgentDetail";
    //商铺详情页
    public static final String VIEW_SHOP_DETAIL = HOST + "/apiv1/house/viewShopDetail";
    //商铺列表页
    public static final String VIEW_SHOP_LIST = HOST + "/apiv1/house/viewShops";
    //查询商铺列表页
    public static final String VIEW_NEWS = HOST + "/apiv1/news/viewNews";
    //查询商铺详情页
    public static final String VIEW_NEWS_DETAIL = HOST + "/apiv1/news/viewNewsDetail";
    //查询新房洋房普宅商铺详情页
    public static final String VIEW_RESIDENCE_DETAIL = HOST + "/apiv1/house/viewResidenceDetail";
    //查询学校详情
    public static final String VIEW_SCHOOL_DETAIL = HOST + "/apiv1/house/viewSchoolDetail";
    //查询项目评价
    public static final String VIEW_NEWHOUSE_ASKLIST = HOST + "/apiv1/other/viewAskDetail";
    //用户登录
    public static final String SIMPLE_LOGIN = HOST + "/apiv1/base/login";
    //短信验证码
    public static final String SMS_CODE = HOST + "/apiv1/other/sendCode";
    //用户注册
    public static final String QUICK_REGIST = HOST + "/apiv1/base/register";
    //查询写字间详细信息
    public static final String VIEW_OFFICE_DETAIL = HOST + "/apiv1/house/viewOfficeDetail";
    //查询别墅详细信息
    public static final String VIEW_VILLA_DETAIL = HOST + "/apiv1/house/viewVillaDetail";
    //查询项目相册
    public static final String VIEW_PROJECT_ALBUM = HOST + "/apiv1/house/viewProjectAlbum";
    //查询相册filter
    public static final String VIEW_ALBUM_COLUMN = HOST + "/apiv1/house/viewAlbumColumn";
    //查询小区详情
    public static final String VIEW_SUBDISTRICT_DETAIL = HOST + "/apiv1/house/viewDistrictDetail";
    //查看项目评论列表
    public static final String VIEW_COMMENT_LIST = HOST + "/apiv1/other/viewComments";
    //查看项目评论详情
    public static final String VIEW_COMMENT_DETAIL = HOST + "/apiv1/other/viewCommentInfo";
    //查看新房列表页
    public static final String VIEW_PROJECT_LIST = HOST + "/apiv1/house/viewProjectInfos";
    //获取个人信息
    public static final String VIEW_MEMBER_INFO = HOST + "/apiv1/base/checkMemberInfo";
    //修改个人信息
    public static final String UPDATE_MEMBER_INFO = HOST + "/apiv1/base/updateMember";
    //查看小二管家列表和详情
    public static final String VIEW_SMALLBULSTER_LIST = HOST + "/apiv1/dynatown/viewDynatowns";
    //小二管家特价房源列表
    public static final String VIEW_ROOMSCOURES_LIST = HOST + "/apiv1/dynatown/viewRoomScoures";
    //小二管家特价房源详细页
    public static final String VIEW_ROOMSCOURES_DETAIL = HOST + "/apiv1/dynatown/viewRoomScoureDetail";
    //小二管家相关动态列表页
    public static final String VIEW_WAITERDYNATOWNS_LIST = HOST + "/apiv1/dynatown/viewWaiterDynatowns";
    //小二管家评论页
    public static final String VIEW_DYNATOWN_REVIEWS = HOST + "/apiv1/dynatown/viewReviews";
    //查看精装房详细页
    public static final String VIEW_DECORATE_DETAIL = HOST + "/apiv1/house/viewDecorateDetail";
    //查看精装房简介页
    public static final String VIEW_DECORATE_BRIEF = HOST + "/apiv1/house/viewDecorateBrief";
    //查看精装房详细页相册
    public static final String VIEW_DECORATE_ALBUM = HOST + "/apiv1/house/viewDecorateAlbum";
    //查看精装房简介相册
    public static final String VIEW_DECORATE_BRIEFALBUM = HOST + "/apiv1/house/viewRenovationAlbum";
    //查看单个活动
    public static final String VIEW_ACTIVITY = HOST + "/apiv1/active/viewActivity2";

}
