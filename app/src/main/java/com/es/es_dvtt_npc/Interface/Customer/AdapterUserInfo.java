package com.es.es_dvtt_npc.Interface.Customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.es.es_dvtt_npc.Data.Object.CustomerInfoEntity;
import com.es.es_dvtt_npc.Data.Object.TTThanhToanEntity;
import com.es.es_dvtt_npc.Helper.PinnedSectionListView;
import com.es.es_dvtt_npc.R;

import java.util.ArrayList;
import java.util.List;
public class AdapterUserInfo extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {

    private Context mContext;
    private CustomerInfoEntity mAccount;
    public static final int TYPE_GROUP_TITLE = 1;
    public static final int TYPE_ITEM = 2;

    private List<Row> mList;
    private LayoutInflater mLayoutInflater;


    public AdapterUserInfo(Context context, CustomerInfoEntity accountInfo) {
        mContext = context;
        mAccount = accountInfo;
        mLayoutInflater = LayoutInflater.from(context);
        mList = new ArrayList<>();
        onCreateItems();
    }

    private void onCreateItems() {
        //section thông tin tài khoản.
//        boolean receiveEmail = configManager.getBoolean(ConfigManager.CONFIG_EMAIL_NOTIFICATION, true);
        mList.add(new Row("THÔNG TIN TÀI KHOẢN", TYPE_GROUP_TITLE));
//        mList.add(new Row("Cập nhật thông tin cá nhân", true, false)); change specs 13/07/2017 remove this button.
        mList.add(new Row("Cập nhật mật khẩu tài khoản", true, false));
//        mList.add(new Row("Nhận thông báo email", false, true, receiveEmail));
        mList.add(new Row("Nhận thông báo", false, true, false));
        mList.add(new Row("Đăng xuất tài khoản", true, false));

        //section 2
        mList.add(new Row("THÔNG TIN HỢP ĐỒNG", TYPE_GROUP_TITLE));
        mList.add(new Row(mAccount.getDonViBanDien().tenDienLuc));
        mList.add(new Row(mAccount.getDonViBanDien().diaChiDienLuc));
        mList.add(new Row(mAccount.getTtKhachHang().maKhachHang));
        mList.add(new Row(mAccount.getTtKhachHang().soCongTo));
        mList.add(new Row(mAccount.getTtKhachHang().soHopDong));
        mList.add(new Row(mAccount.getTtKhachHang().diaChi));


        //section 3 thông tin cắt điên
        mList.add(new Row("THÔNG TIN CẮT ĐIỆN", TYPE_GROUP_TITLE));
        mList.add(new Row(mAccount.getTtCatDien().thoiGianCat));
        mList.add(new Row(mAccount.getTtCatDien().liDo));
//
//        //section 4 thong tin thanh toan.
        mList.add(new Row("THÔNG TIN THANH TOÁN", TYPE_GROUP_TITLE));
        for (TTThanhToanEntity payment : mAccount.getThanhToanList()) {
            mList.add(new Row(payment.thang));
            mList.add(new Row(payment.ky));
            mList.add(new Row(payment.lichGhiChiSo));
            mList.add(new Row(payment.dienTieuThu));
            mList.add(new Row(payment.soTien));
            mList.add(new Row(payment.tinhTrang));
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Row getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_user_info, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Row item = mList.get(position);
        holder.update(item);
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return mList.size();
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return false;
    }

    public static class Holder {

        public ToggleButton toggleButton;

        public TextView groupTitle;

        public TextView rowTitle;

        public ImageView arrowIcon;

        public Holder(View itemView) {
            toggleButton = (ToggleButton) itemView.findViewById(R.id.toggle);
            groupTitle = (TextView) itemView.findViewById(R.id.group_title);
            rowTitle = (TextView) itemView.findViewById(R.id.row_title);
            arrowIcon = (ImageView) itemView.findViewById(R.id.arrow_right);
        }

        void update(Row row) {
            rowTitle.setVisibility(View.GONE);
            arrowIcon.setVisibility(View.GONE);
            toggleButton.setVisibility(View.GONE);
            groupTitle.setVisibility(View.GONE);
            if (row.type == TYPE_GROUP_TITLE) {
                groupTitle.setVisibility(View.VISIBLE);
                groupTitle.setText(row.text);
            } else {
                rowTitle.setVisibility(View.VISIBLE);
                rowTitle.setText(row.text);
                if (row.hasArrow) {
                    arrowIcon.setVisibility(View.VISIBLE);
                } else if (row.hasToggle) {
                    toggleButton.setVisibility(View.VISIBLE);
                    toggleButton.setChecked(row.toggleValue);
                }
            }
        }
    }


    public static class Row {
        public String text;
        public boolean hasArrow;
        public boolean hasToggle;
        public boolean toggleValue;
        public int type = TYPE_ITEM;

        public Row(String text, boolean hasArrow, boolean hasToggle) {
            this.text = text;
            this.hasArrow = hasArrow;
            this.hasToggle = hasToggle;
        }

        public Row(String text, boolean hasArrow, boolean hasToggle, boolean toggleValue) {
            this.text = text;
            this.hasArrow = hasArrow;
            this.hasToggle = hasToggle;
            this.toggleValue = toggleValue;
        }

        public Row(String text) {
            this.text = text;
        }

        public Row(String text, int type) {
            this.text = text;
            this.type = type;
        }
    }
}
