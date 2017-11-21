package com.es.es_dvtt_npc.Helper;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.es.es_dvtt_npc.Data.Object.HoDungChungEntity;
import com.es.es_dvtt_npc.Interface.ElectricitySupply.InfoDungChungAdapter;
import com.es.es_dvtt_npc.R;

import java.util.ArrayList;


/**
 * Created by hungh on 5/1/2017.
 */

public class AppAlertDialog {

    public static android.support.v7.app.AlertDialog ErrorApiAlertDialogOk(Context context, Error error,
                                                                           boolean hasOKButton, Dialog.OnClickListener okClick) {
        if (TextUtils.isEmpty(error.getMessage())){
            return AlertDialogOkAndCancel(context, context.getString(R.string.error),"", hasOKButton, okClick, false, null);
        }else {
            Spanned spanned = Html.fromHtml(error.getMessage());
            String massage = spanned.toString();
            return AlertDialogOkAndCancel(context, context.getString(R.string.error),
                    massage, hasOKButton, okClick, false, null);
        }
    }

    public static android.support.v7.app.AlertDialog AlertDialogCancel(Context context, String title, String message,
                                                                       boolean hasCancelButton, Dialog.OnClickListener cancelClick) {
        return AlertDialogOkAndCancel(context, title, message, false, null, hasCancelButton, cancelClick);
    }

    public static android.support.v7.app.AlertDialog AlertDialogOk(Context context, String title, String message,
                                                                   boolean hasOKButton, Dialog.OnClickListener okClick) {
        return AlertDialogOkAndCancel(context, title, message, hasOKButton, okClick, false, null);
    }

    public static android.support.v7.app.AlertDialog AlertDialogOkAndCancel(Context context, String title, String message,
                                                                            boolean hasOKButton, Dialog.OnClickListener okClick,
                                                                            boolean hasCancelButton, Dialog.OnClickListener cancelClick) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }
        if (hasOKButton) {
            builder.setNegativeButton(R.string.common_ok, okClick);
        }
        if (hasCancelButton) {
            builder.setPositiveButton(R.string.common_cancel, cancelClick);
        }
        return builder.create();
    }
    public static void showAlertDialogGreen(Context context, String title, int title_color, String content, int content_color, String button, int button_color){
        try{
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.invoice_dialog);
            dialog.getWindow().setLayout(android.app.ActionBar.LayoutParams.MATCH_PARENT,android.app.ActionBar.LayoutParams.WRAP_CONTENT);
            dialog.setCanceledOnTouchOutside(false);

            TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
            TextView tvBody = (TextView) dialog.findViewById(R.id.tvBody);
            LinearLayout lnButton = (LinearLayout) dialog.findViewById(R.id.lnButton);

            tvBody.setTextColor(content_color);
            tvTitle.setText(title);
            tvTitle.setTextColor(title_color);
            tvBody.setText(content);

            TextView tvClose = new TextView(context);
            tvClose.setText(button);
            tvClose.setTextColor(button_color);
            tvClose.setTypeface(null, Typeface.BOLD);
            tvClose.setPadding(10, 10, 20, 10);
            tvClose.setTextSize(20);
            tvClose.setGravity(Gravity.RIGHT);
            lnButton.setGravity(Gravity.RIGHT);
            lnButton.addView(tvClose, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            tvClose.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);

            dialog.show();
        } catch(Exception ex) {

        }
    }

    public static void showInfoHoDungChung(Context context, Dialog dlInfoHo, ArrayList<HoDungChungEntity> hoDungChungEntities) {
        if (dlInfoHo == null) {
            dlInfoHo = new Dialog(context, R.style.full_screen_dialog);
        }

        //setting custom layout to dialog
        dlInfoHo.setContentView(R.layout.dl_info_ho_dung_chung);
        dlInfoHo.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);

        Button btnDismiss = dlInfoHo.findViewById(R.id.dl_info_ho_dung_chung_btnDismiss);
        ListView lvInfo = dlInfoHo.findViewById(R.id.dl_info_ho_dung_chung_lvInfo);
        InfoDungChungAdapter infoDungChungAdapter = new InfoDungChungAdapter(context,hoDungChungEntities);
        lvInfo.setAdapter(infoDungChungAdapter);
        final Dialog finalDlInfoHo = dlInfoHo;
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalDlInfoHo.dismiss();
            }
        });
        dlInfoHo.show();
    }
}
