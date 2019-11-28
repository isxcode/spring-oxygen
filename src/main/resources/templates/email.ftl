<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+SC:100,300,400,500,700,900">
    <style type="text/css">
        body{
            font-family: "Noto Sans SC";
            font-size: 12px;
            background: white;
        }
        .param {
            font-weight: bold;
            color: #027aff;
        }
        #font{
            color: darkgray;
            border-top: lightgray solid 1px;
            border-bottom: lightgray solid 1px;
        }
        #font-text{
            margin-top: 18px;
            font-size: 12px;
            line-height: 25px;
        }
        .font-link{
            margin-top: 15px;
            font-size: 10px;
            line-height: 10px;
        }
        ul li {
            margin-top: 4px;
        }
    </style>
</head>
<body>
<div style="width: 440px; margin: auto;box-shadow: 1px 1px 5px #d3d3d3;">

    <img src="http://portal.definesys.com:30600/pluto/Attachments/downloadImage/be87901ca541ff0d.jpg">
    <div style="width: 360px; margin-left: 40px;margin-bottom:25px;margin-top: 25px;">

        <p>${userName}，您好！这是您使用Pluto的第<span class="param">${dayNum}</span>天。</p>
        <p style="margin-top: -5px">以下是${startDate}-${endDate}的用户使用周报。</p>
        <ul style="margin-top: 20px;margin-left:-23px;">
            <li>累计签到<span class="param">7</span>次，最早签到时间为<span class="param">00:00:00</span></li>
            <li>累计完工打卡<span class="param">6</span>次，最晚打卡时间为<span class="param">00:00:00</span></li>
            <li>共参与<span class="param">3</span>个项目，分别为王伟是傻子、王伟是智障</li>
            <li>累计完成<span class="param">0</span>个任务，同比上周下降<span class="param">0%</span></li>
            <li>累计完成<span class="param">0</span>个工时，同比上周增长<span class="param">0%</span></li>
            <li>累计增加<span class="param">0</span>积分，同比上周增长<span class="param">0%</span></li>
        </ul>
        <div id="font">

            <p id="font-text">我们珍惜每一个来自于用户的声音，如果任何疑问或者建议请随时联系我们，我们非常乐意倾听您的想法，并协助解决问题。</p>
            <div style="margin-top: 20px;margin-bottom: 25px">
                <p class="font-link">邮箱: &nbsp;support.pluto@definesys.com</p>
                <p class="font-link">WEB:&nbsp;<span style="color: #027aff">http://hr.definesys.com/pluto/index.html#/home/inventory</span></p>
                <p class="font-link">微信小程序:&nbsp;Definesys Pluto</p>
            </div>
        </div>
        <div style="text-align:center;color: lightgray;font-size: 10px;margin-top: 20px;height: 40px">本邮件由系统自动发出，请勿回复。</div>
    </div>
</div>
</body>
</html>