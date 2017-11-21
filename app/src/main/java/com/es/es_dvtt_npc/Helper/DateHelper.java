package com.es.es_dvtt_npc.Helper;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.DatePicker;
import android.widget.EditText;

import com.es.es_dvtt_npc.R;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by PhuongVV on 5/18/16.
 */
public class DateHelper {


    public static String getDateStringBasic(String dateStr) {
        Date date = dateFromString(dateStr, "yyyy-MM-dd");

        return stringFromDate(date, "MMMM dd, yyyy");
    }


    public static Date dateFromString(String aDate, String aFormat) {

        if (aDate == null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }

    public static String stringFromDate(Date aDate, String format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(format);

        return date.format(aDate);
    }

    public static String stringFromDateBasic(Date date) {
        return stringFromDate(date, "dd/MM/yyyy");
    }


    //    public static String DateDiff(long time, Activity act){
//        Resources res = act.getResources();
//
//        String rs;
//        Calendar cal = GregorianCalendar.getInstance();
//        cal.setTime(new Date());
//        cal.add(Calendar.DAY_OF_YEAR, 0);
//        long kq=cal.getTime().getTime() - time;
//        long diffInSecond = TimeUnit.MILLISECONDS.toSeconds(kq);
//        long diffInMinute=diffInSecond/60;
//        long diffinHour=diffInMinute/60;
//        long diffinDay=diffinHour/24;
//        long diffinWeek=diffinDay/7;
//        long diffinMonth=diffinWeek/4;
//        long diffinYear=diffinMonth/12;
////        Log.e("diff second", diffInSecond + "");
////        Log.e("diff minute",diffInMinute+"");
////        Log.e("diff hour",diffinHour+"");
////        Log.e("diff day",diffinDay+"");
////        Log.e("diff year",diffinYear+"");
//        if(diffInMinute==0){
//            if(diffInSecond>1) {
//                rs=diffInSecond + res.getString(R.string.second_ago);
//                return rs;
//            }else{
//                rs=res.getString(R.string.just_now);
//                return rs;
//            }
//        }else if(diffinHour==0){
//            if(diffInMinute>1) {
//                rs=diffInMinute + res.getString(R.string.min_ago);
//                return rs;
//            }else{
//                rs= 1 + res.getString(R.string.a_minute_ago);
//                return rs;
//            }
//        }else if(diffinDay==0){
//            if(diffinHour>1) {
//                rs=diffinHour + res.getString(R.string.hour_ago);
//                return rs;
//            }else{
//                rs=res.getString(R.string.a_hour_ago);
//                return rs;
//            }
//        }else if(diffinWeek==0){
//            if(diffinDay>1) {
//                rs=diffinDay + res.getString(R.string.day_ago);
//                return rs;
//            }else{
//                rs=res.getString(R.string.a_day_ago);
//                return rs;
//            }
//        }else if(diffinMonth==0){
//            if(diffinWeek>1) {
//                rs=diffinWeek + res.getString(R.string.week_ago);
//                return rs;
//            }else{
//                rs=res.getString(R.string.a_week_ago);
//                return rs;
//            }
//        }else if(diffinYear==0){
//            if(diffinMonth>1) {
//                rs=diffinMonth + res.getString(R.string.month_ago);
//                return rs;
//            }else{
//                rs=res.getString(R.string.a_month_ago);
//                return rs;
//            }
//        }else{
//            if(diffinYear>1) {
//                rs=diffinYear + res.getString(R.string.year_ago);
//                return rs;
//            }else{
//                rs=res.getString(R.string.a_year_ago);
//                return rs;
//            }
//        }
//    }

    public static void dateDialogPicker(final Context context, final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int mYear, mMonth, mDay;
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Calendar cal = Calendar.getInstance();
                cal.roll(Calendar.DATE, -1);
                if (calendar.getTime().before(cal.getTime())) {
                    //  myDate must be yesterday or earlier
                    editText.setText(DateHelper.stringFromDateBasic(calendar.getTime()));
                } else {
                    //  myDate must be today or later
                    AppAlertDialog.showAlertDialogGreen(context, "Lỗi", Color.RED,"Bạn không thể nhập ngày lớn hơn ngày hiện tại" , Color.WHITE, context.getString(R.string.common_ok), Color.RED);
                    editText.setText("");
                }
            }
        };

        DatePickerDialog dpDialog = new DatePickerDialog(context, 0, listener, mYear, mMonth, mDay); // theme 3 easy to use
        dpDialog.show();
    }
}
