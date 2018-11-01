package com.eysale.zonelee.app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eysale.zonelee.R;
import com.eysale.zonelee.bean.FoundPageDetailData;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FoundPageListAdapter extends BaseAdapter {

    private List<FoundPageDetailData> pageDatas;
    private LayoutInflater inflater;

    public FoundPageListAdapter(List<FoundPageDetailData> datas, LayoutInflater i) {
        if(datas == null) {
            pageDatas = new ArrayList<>();
        } else {
            pageDatas = datas;
        }
        inflater = i;
    }

    public void addData(List<FoundPageDetailData> datas) {
        pageDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pageDatas.size();
    }

    @Override
    public FoundPageDetailData getItem(int position) {
        return pageDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.foundpage_list_item, null);
            vh = new ViewHolder();
            vh.tvTitle = convertView.findViewById(R.id.tv_article_title);
            vh.tvAuthor = convertView.findViewById(R.id.tv_article_author);
            vh.tvTime = convertView.findViewById(R.id.tv_article_time);
            convertView.setTag(vh);
        } else
            vh = (ViewHolder) convertView.getTag();

        FoundPageDetailData data = getItem(position);
        vh.tvTitle.setText(data.title);
        vh.tvAuthor.setText(data.author);
        vh.tvTime.setText(data.time);
        return convertView;
    }

    private class ViewHolder {

        public TextView tvTitle;
        public TextView tvAuthor;
        public TextView tvTime;

    }

}
