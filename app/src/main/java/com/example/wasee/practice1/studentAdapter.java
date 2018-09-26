package com.example.wasee.practice1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class studentAdapter extends BaseAdapter {
    private Context activity;
    private ArrayList<userinfo> allStudent = new ArrayList<>();
    private LayoutInflater layoutInflater = null;

    public studentAdapter(Context activity, ArrayList<userinfo> allStudent) {
        this.activity = activity;
        this.allStudent = allStudent;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder{
        private TextView name, email, password, mobileNumber,gender,date;
    }
    private ViewHolder viewHolder = null;

    @Override
    public int getCount() {
        return allStudent.size();
    }

    @Override
    public userinfo getItem(int position) {
        return allStudent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        final int pos = position;
        if(view == null){
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.row_cell_student,null);
            viewHolder.name = view.findViewById(R.id.name);
            viewHolder.email = view.findViewById(R.id.email);
            viewHolder.password= view.findViewById(R.id.pass);
            viewHolder.mobileNumber = view.findViewById(R.id.phn);
            viewHolder.gender=view.findViewById(R.id.gender);
            viewHolder.date=view.findViewById(R.id.date);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(allStudent.get(pos).getName());
        viewHolder.email.setText(allStudent.get(pos).getEmail());
        viewHolder.password.setText(allStudent.get(pos).getPassword());
        viewHolder.mobileNumber.setText(allStudent.get(pos).getMobileNo());
        viewHolder.gender.setText(allStudent.get(pos).getGender());
        viewHolder.date.setText(allStudent.get(pos).getDob());
        return view;
    }


}
