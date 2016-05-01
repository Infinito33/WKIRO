package com.wkiro.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wkiro.R;

/**
 * Created by tomasz.huchro on 2016-04-27.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return Consts.ICONS.length;
    }

    @Override
    public LauncherIcon getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        public ImageView icon;
        public TextView text;
    }

    // Create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            v = vi.inflate(R.layout.dashboard_icon, null);
            holder = new ViewHolder();
            holder.text = (TextView) v.findViewById(R.id.dashboard_icon_text);
            holder.icon = (ImageView) v.findViewById(R.id.dashboard_icon_img);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.icon.setImageResource(Consts.ICONS[position].imgId);
        holder.text.setText(Consts.ICONS[position].text);

        return v;
    }
}