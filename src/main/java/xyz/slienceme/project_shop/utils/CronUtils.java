package xyz.slienceme.project_shop.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
public class CronUtils {

    //"ss mm HH dd MM ? yyyy"   每年的指定日执行
    private static final SimpleDateFormat sdf0 = new SimpleDateFormat("ss mm HH dd MM ?");
    //每月的指定日执行
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("ss mm HH dd * ?");
    //每日的指定时间执行
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("ss mm HH * * ?");
    //每小时执行
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("ss mm * * * ?");
    //每分钟执行
    private static final SimpleDateFormat sdf4 = new SimpleDateFormat("ss * * * * ?");
    //每秒
    private static final SimpleDateFormat sdf5 = new SimpleDateFormat("0/ss * * * * ?");

    /***
     * convert Date to cron, eg "0 07 10 15 1 ?"
     * @param date  : 时间点
     * @return type=0 年  1 月  2日  3小时  4分钟  5秒
     */
    public static String getCron(Date date, int type) {
        String formatTimeStr = null;
        if (Objects.nonNull(date)) {
            switch (type) {
                case 0:
                    formatTimeStr = sdf0.format(date);
                    break;
                case 1:
                    formatTimeStr = sdf1.format(date);
                    break;
                case 2:
                    formatTimeStr = sdf2.format(date);
                    break;
                case 3:
                    formatTimeStr = sdf3.format(date);
                    break;
                case 4:
                    formatTimeStr = sdf4.format(date);
                    break;
                case 5:
                    formatTimeStr = sdf5.format(date);
                    break;
                default:
                    formatTimeStr = null;
                    break;
            }
        }
        return formatTimeStr;
    }


    /**
     * 方法摘要：构建Cron表达式
     *
     * @param taskScheduleModel
     * @return String
     */
    public static String createCronExpression(xyz.slienceme.project_shop.utils.TaskScheduleModel taskScheduleModel) {
        StringBuffer cronExp = new StringBuffer("");

        if (null == taskScheduleModel.getJobType()) {
            System.out.println("执行周期未配置");//执行周期未配置
        }

        if (null != taskScheduleModel.getSecond()
                && null != taskScheduleModel.getMinute()
                && null != taskScheduleModel.getHour()) {
            //秒
            cronExp.append(taskScheduleModel.getSecond()).append(" ");
            //分
            cronExp.append(taskScheduleModel.getMinute()).append(" ");
            //小时
            cronExp.append(taskScheduleModel.getHour()).append(" ");

            //每天
            if (taskScheduleModel.getJobType().intValue() == 1) {
                cronExp.append("* ");//日
                cronExp.append("* ");//月
                cronExp.append("?");//周
            }

            //按每周
            else if (taskScheduleModel.getJobType().intValue() == 3) {
                //一个月中第几天
                cronExp.append("? ");
                //月份
                cronExp.append("* ");
                //周
                Integer[] weeks = taskScheduleModel.getDayOfWeeks();
                for (int i = 0; i < weeks.length; i++) {
                    if (i == 0) {
                        cronExp.append(weeks[i]);
                    } else {
                        cronExp.append(",").append(weeks[i]);
                    }
                }

            }

            //按每月
            else if (taskScheduleModel.getJobType().intValue() == 2) {
                //一个月中的哪几天
                Integer[] days = taskScheduleModel.getDayOfMonths();
                for (int i = 0; i < days.length; i++) {
                    if (i == 0) {
                        cronExp.append(days[i]);
                    } else {
                        cronExp.append(",").append(days[i]);
                    }
                }
                //月份
                cronExp.append(" * ");
                //周
                cronExp.append("?");
            }
        } else {
            System.out.println("时或分或秒参数未配置");//时或分或秒参数未配置
        }
        return cronExp.toString();
    }

    /**
     * 方法摘要：生成计划的详细描述
     *
     * @param taskScheduleModel
     * @return String
     */
    public static String createDescription(xyz.slienceme.project_shop.utils.TaskScheduleModel taskScheduleModel) {
        StringBuffer description = new StringBuffer("");
        //计划执行开始时间
//      Date startTime = taskScheduleModel.getScheduleStartTime();

        if (null != taskScheduleModel.getSecond()
                && null != taskScheduleModel.getMinute()
                && null != taskScheduleModel.getHour()) {
            //按每天
            if (taskScheduleModel.getJobType().intValue() == 1) {
                description.append("每天");
                description.append(taskScheduleModel.getHour()).append("时");
                description.append(taskScheduleModel.getMinute()).append("分");
                description.append(taskScheduleModel.getSecond()).append("秒");
                description.append("执行");
            }

            //按每周
            else if (taskScheduleModel.getJobType().intValue() == 3) {
                if (taskScheduleModel.getDayOfWeeks() != null && taskScheduleModel.getDayOfWeeks().length > 0) {
                    String days = "";
                    for (int i : taskScheduleModel.getDayOfWeeks()) {
                        days += "周" + i;
                    }
                    description.append("每周的").append(days).append(" ");
                }
                if (null != taskScheduleModel.getSecond()
                        && null != taskScheduleModel.getMinute()
                        && null != taskScheduleModel.getHour()) {
                    description.append(",");
                    description.append(taskScheduleModel.getHour()).append("时");
                    description.append(taskScheduleModel.getMinute()).append("分");
                    description.append(taskScheduleModel.getSecond()).append("秒");
                }
                description.append("执行");
            }

            //按每月
            else if (taskScheduleModel.getJobType().intValue() == 2) {
                //选择月份
                if (taskScheduleModel.getDayOfMonths() != null && taskScheduleModel.getDayOfMonths().length > 0) {
                    String days = "";
                    for (int i : taskScheduleModel.getDayOfMonths()) {
                        days += i + "号";
                    }
                    description.append("每月的").append(days).append(" ");
                }
                description.append(taskScheduleModel.getHour()).append("时");
                description.append(taskScheduleModel.getMinute()).append("分");
                description.append(taskScheduleModel.getSecond()).append("秒");
                description.append("执行");
            }
        }
        return description.toString();
    }

    public static void main(String[] args) throws Exception {
//        String time = "2021-03-11 09:40:05";
//        Date date = DateUtil.getDataByString(time);
//        System.err.println(getCron(date,0));
//        System.err.println(getCron(date,1));
//        System.err.println(getCron(date,2));
//        System.err.println(getCron(date,3));
//        System.err.println(getCron(date,4));
//        System.err.println(getCron(date,5));


        //执行时间：每天的12时12分12秒 start
        xyz.slienceme.project_shop.utils.TaskScheduleModel taskScheduleModel = new xyz.slienceme.project_shop.utils.TaskScheduleModel();

        taskScheduleModel.setJobType(1);//按每天
        Integer hour = 12; //时
        Integer minute = 12; //分
        Integer second = 12; //秒
        taskScheduleModel.setHour(hour);
        taskScheduleModel.setMinute(minute);
        taskScheduleModel.setSecond(second);
        String cropExp = createCronExpression(taskScheduleModel);
        System.out.println(cropExp + ":" + createDescription(taskScheduleModel));
        //执行时间：每天的12时12分12秒 end

        taskScheduleModel.setJobType(3);//每周的哪几天执行
        Integer[] dayOfWeeks = new Integer[3];
        dayOfWeeks[0] = 1;
        dayOfWeeks[1] = 2;
        dayOfWeeks[2] = 3;
        taskScheduleModel.setDayOfWeeks(dayOfWeeks);
        cropExp = createCronExpression(taskScheduleModel);
        System.out.println(cropExp + ":" + createDescription(taskScheduleModel));

        taskScheduleModel.setJobType(2);//每月的哪几天执行
        Integer[] dayOfMonths = new Integer[3];
        dayOfMonths[0] = 1;
        dayOfMonths[1] = 21;
        dayOfMonths[2] = 13;
        taskScheduleModel.setDayOfMonths(dayOfMonths);
        cropExp = createCronExpression(taskScheduleModel);
        System.out.println(cropExp + ":" + createDescription(taskScheduleModel));

    }

}
