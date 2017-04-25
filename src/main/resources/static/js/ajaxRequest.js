
var ajaxRequest = {
    mobilZC: function () {
        var mobile = $(".fxe_mobile").val();
        var x = false;
        $.ajax({
            type: "POST",
            data: { telNumber: mobile },
            url: "/mobileValidate",
            async: false,
            success: function (data) {
                if (data.status == 1) {
                    $(".error_info").text("");
                    x = false;
                } else if (data.status == -1) {
                    $(".error_info").text("该手机号段不在范围内!");
                    x = false;
                }
                else {
                    $(".error_info").text("");
                    x = true;
                }
            }
        });
        return x;
    },
    //查图片验证码是否正确
    TPyzmFS: function () {
        var code = $(".fxe_CodeImg").val();
        var x = false;
        if (code == "") {
            $(".fxe_jiaoyan_error").html(hint[6]);
            //$('.fxe_Code').attr("src", "/Action/CheckCode.aspx?id=" + new Date().getTime());
        } else {
            $.ajax({
                type: "POST",
                data: { code: code},
                url: "/imgvrifyControllerDefaultKaptcha",
                async: false,
                success: function (data) {
                    if (data.info == 1) {
                        alert(data.info);
                        $(".fxe_jiaoyan_error").html("");
                        x = true;
                    } else {
                        alert(data.info);
                        $(".fxe_jiaoyan_error").html(hint[7]);
                        $('.fxe_Code').attr("src", "/defaultKaptcha?id=" + new Date().getTime());
                        x = false;
                    }
                }
            });
        }
        return x;
    },
    //查看手机验证码状态
    yzmZT: function () {
        var x = false;
        ajaxRequest.SJyzmFS();
        //alert(fxeTime.r)
        if (fxeTime.r == 1) {
            $(".fxe_jiaoyan_error").html("");
            $(".fxe_jiaoyan_bg").hide();
            $(".fxe_jiaoyan_div").hide();
            fxeTime.timeWait();
            x = true;
        } else if (fxeTime.r == -4) {
            $(".fxe_jiaoyan_error").html("十分抱歉！您今日的请求次数已达上限，请明日再进行重试。");
            x = false;
        } else if (fxeTime.r == 0 || fxeTime.r == -2 || fxeTime.r == -3) {
            $(".fxe_jiaoyan_error").html("系统繁忙，请稍后重试!");
            //fxeTime.r = 1;
            x = false;
        } else {
            $('.fxe_Code').attr("src", "/Action/CheckCode.aspx?id=" + new Date().getTime());// 重新获取图片验证码
            //fxeTime.r = 1;
            x = false;
        }
        return x;
    },
    //向用户发送手机验证码
    SJyzmFS: function () {
        //发送短信验证码
        var mobile = $(".fxe_mobile").val();
        var x = false;
        $.ajax({
            type: "POST",
            data: { mobile: mobile },
            url: "/sendSmsCode",
            async: false,
            success: function (data) {
                if (data.status == "1") {
                    fxeTime.r = 1;
                    //   return icode;
                }else {
                    fxeTime.r = data.status;
                    // return icode;
                }
            }
        })
    },
}