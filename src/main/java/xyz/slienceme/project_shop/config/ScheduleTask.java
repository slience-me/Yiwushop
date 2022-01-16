//package xyz.slienceme.project_shop.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//@Configuration
//@EnableScheduling
//public class ScheduleTask {
//
//    @Autowired
//    RecordMapper recordMapper;
//    @Autowired
//    UserMapper userMapper;
//    @Autowired
//    WeekEvaluateMapper weekEvaluateMapper;
//
//    private static Integer getTime(String timeFormat) {
//        String[] num = timeFormat.split(":");
//        int sum = 0;
//        int hour = Integer.parseInt(num[0]);
//        int min = Integer.parseInt(num[1]);
//        int sec = Integer.parseInt(num[2]);
//        sum = hour * 3600 + min * 60 + sec;
//        return sum * 1000;
//    }
//
//
////    @Scheduled(cron = "0/20 * * * * ?")
//    @Scheduled(cron = "0 30 22 * * ?")
//    public void sendEmail() throws Exception {
//        //记录的条件0 30 22 * * ?
//        List<HashMap<String, Object>> lists = recordMapper.selectAllOuttimeIsNull();
//        for (HashMap<String, Object> list : lists) {
//            Integer userId = (Integer) list.get("userId");
//            User user = userMapper.selectByUserId(userId);
//        //  if (user.getQqNumber() != null && !("".equals(user.getQqNumber()))) {
//            if (user.getOpenid() != null && !("".equals(user.getOpenid()))) {
//                /*String html = EmaildataUtil.getHtmlEmail("签退通知", user.getRealname(), "温馨提示：注意及时签退呦！");*/
//                SendWxTipUtil.sendTip(user.getOpenid(), "wx0c9789a8be812194", "9d2b7f0025ccba2831ffb433dd3e3ff6", "fsJ50vXMyXJaSn2s5av-sRbRp2_H05oTbl50VKZyFps", "/pages/clock/index/index", "{\"time1\": {\"value\": \"22:30\"},\"thing2\": {\"value\": \"温馨提示：注意及时签退呦！\"}");
//                //EmailUtils.SendMessageByMail(user.getQqNumber() + "@qq.com", "签退提醒", html);
//            }
//            System.out.println("给" + user.getRealname() + "发送通知了!");
//        }
//
//    }
//
//
//
////    @Scheduled(cron = "0/20 * * * * ?")
//    @Scheduled(cron = "0 0 23 * * ?")
//    public void deleteRecord() throws Exception {
//        List<HashMap<String, Object>> lists = recordMapper.selectAllOuttimeIsNull();
//        for (HashMap<String, Object> list : lists) {
//            Integer userId = (Integer) list.get("userId");
//            User user = userMapper.selectByUserId(userId);
//            if (user.getOpenid() != null && !("".equals(user.getOpenid()))) {
//                /*String html = EmaildataUtil.getHtmlEmail("未签退通知", user.getRealname(), "温馨提示：您没有及时签退呦！今天的打卡时长我拿走了呦！");*/
//                Date date = new Date();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
//                SendWxTipUtil.sendTip(user.getOpenid(), "wx0c9789a8be812194", "JWj8QaK1WbrYvca6I-vRLn6-TqNEuD8ILHXxQxQfStA", "JWj8QaK1WbrYvca6I-vRLn6-TqNEuD8ILHXxQxQfStA", "/pages/clock/index/index", "{\"time1\": {\"value\": \""+sdf.format(date)+"\"},\"thing2\": {\"value\": \"傻了吧，忘签退了吧，白干了吧，笑死我了！\"}");
//                // EmailUtils.SendMessageByMail(user.getQqNumber() + "@qq.com", "通知", html);
//            }
//            recordMapper.deleteByPrimaryKey((Integer) list.get("recordId"));
//        }
//    }
//    //定时给大一打分
//
////    @Scheduled(cron = "0/20 * * * * ?")
//    @Scheduled(cron = "0 50 23 ? * SUN")
//    public void mark() throws Exception {
//        //获取这一周的开始和终止时间
//        long startTime = TimeUtils.getTimesmorning().getTime();
//        long endTime = startTime + 7 * 24 * 60 * 60 * 1000;
//        System.out.println("startTime = " + startTime);
//        System.out.println("endTime = " + endTime);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date startDate = new Date();
//        Date endDate = new Date();
//        startDate.setTime(startTime);
//        endDate.setTime(endTime);
//        String startDateFormat = sdf.format(startDate);
//        String endDateFormat = sdf.format(endDate);
//        System.out.println("startDateFormat = " + startDateFormat);
//        System.out.println("endDateFormat = " + endDateFormat);
//
//
//        //获取本周所有的测评
//        List<HashMap<String, Object>> weekEvaluates = weekEvaluateMapper.selectList(null, startDateFormat, endDateFormat, null);
//        System.out.println("weekEvaluates = " + weekEvaluates);
//
//        //获取他们的record时间
//        for (HashMap<String, Object> weekEvaluate : weekEvaluates) {
//            List<HashMap<String, Object>> records = recordMapper.selectList((Integer) weekEvaluate.get("userId"), startDateFormat, endDateFormat, null);
//            long times = 0L;
//            for (HashMap<String, Object> record : records) {
//                String recordTime = (String) record.get("recordTime");
//                System.out.println("recordTime = " + recordTime);
//                Integer time1 = getTime(recordTime);
//                System.out.println("time1 = " + time1);
//                times += time1;
//            }
//            System.out.println("times = " + times);
//            //获取他们的时长分数
//            double timess = times;
//            System.out.println("timess = " + timess);
//            double standard = 1000 * 60 * 60 * 20;
//            System.out.println("standard = " + standard);
//            int studyTime = timess / standard > 1.0 ? 30 : (int) ((times / standard) * 30);
//            System.out.println("studyTime = " + studyTime);
//            //总分数
//            int kpi = (Integer) weekEvaluate.get("weLuogu") + (Integer) weekEvaluate.get("weCsdn") + (Integer) weekEvaluate.get("weProgress") + studyTime;
//            System.out.println("kpi = " + kpi);
//            //改到数据库中
//            WeekEvaluate weekEvaluate1 = new WeekEvaluate();
//            weekEvaluate1.setWeId((Integer) weekEvaluate.get("weId"));
//            weekEvaluate1.setWeStudytime(studyTime);
//            weekEvaluate1.setWeKpi(kpi);
//            weekEvaluateMapper.updateByPrimaryKeySelective(weekEvaluate1);
//        }
//        String weekHtml1 = EmaildataUtil.getHtmlEmail("周评通知", "计算机194姚赛", "本周大一的评分已结束");
//        String weekHtml2 = EmaildataUtil.getHtmlEmail("周评通知", "计算机193宋保贤", "本周大一的评分已结束");
////        SendWxTipUtil.sendTip("oDeIi5cpZOSHy6RdCaZ0c5f--RXk", "wx0c9789a8be812194", "9d2b7f0025ccba2831ffb433dd3e3ff6", "fsJ50vXMyXJaSn2s5av-sRbRp2_H05oTbl50VKZyFps", "/pages/clock/index/index", "{\"time1\": {\"value\": \"23:50\"},\"thing2\": {\"value\": \"大一已评分\"}");
////        SendWxTipUtil.sendTip("oDeIi5V000QfNDgV3rgfYk4vjFCw", "wx0c9789a8be812194", "9d2b7f0025ccba2831ffb433dd3e3ff6", "fsJ50vXMyXJaSn2s5av-sRbRp2_H05oTbl50VKZyFps", "/pages/clock/index/index", "{\"time1\": {\"value\": \"23:50\"},\"thing2\": {\"value\": \"大一已评分\"}");
//        EmailUtils.SendMessageByMail("2060695019@qq.com", "大一已评分", weekHtml1);
//        EmailUtils.SendMessageByMail("slience_me@foxmail.com", "大一已评分", weekHtml2);
//    }
//}
