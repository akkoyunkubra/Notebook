package com.kbakkoyungmail.notebook;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by recep on 2.04.2016.
 */
public class NoteAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Note> arrayList;

    public NoteAdapter(Context context, int item_listview, ArrayList<Note> products) {
        this.context = context;
        this.arrayList = products;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_row, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.textView3, viewHolder.tv1);
            convertView.setTag(R.id.textView4, viewHolder.tv2);
            convertView.setTag(R.id.textView5, viewHolder.tv3);
            convertView.setTag(R.id.textView6, viewHolder.tv4);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        Note note = arrayList.get(position);

        viewHolder.tv1.setText(note.getTitle().trim());
        viewHolder.tv2.setText(note.getUser().trim());
        viewHolder.tv3.setText(note.getDate());
        viewHolder.tv4.setText(note.getNote().trim());



        return convertView;
    }


    private class ViewHolder {

        private TextView tv1,tv2,tv3,tv4;



        public ViewHolder(View convertView) {
            tv1 = (TextView) convertView.findViewById(R.id.textView3);
            tv2 = (TextView) convertView.findViewById(R.id.textView4);
            tv3 = (TextView) convertView.findViewById(R.id.textView5);
            tv4 = (TextView) convertView.findViewById(R.id.textView6);

        }

    }


}