package com.outboxedu.savingdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;

import java.util.List;

/**
 * Created by Zed on 9/18/2015.
 */
public class MinionAdapter extends ArrayAdapter<Minion> {
    ImageOptions options;
    Context cxt;

    public MinionAdapter(Context context, int resource, List<Minion> objects) {
        super(context, resource, objects);
        options = new ImageOptions();
        options.targetWidth = 200;
        options.fileCache = true;
        options.memCache = true;

        cxt = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)cxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.minion_row, null);
        }

        Minion m = getItem(position);

        AQuery aq = new AQuery(view);
        aq.id(R.id.txt_title).text(m.title);
        aq.id(R.id.txt_description).text(m.description);
        aq.id(R.id.img_avatar).image(m.image, options);

        return view;
    }
}
