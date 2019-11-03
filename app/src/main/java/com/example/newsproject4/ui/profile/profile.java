package com.example.newsproject4.ui.profile;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.newsproject4.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends Fragment {
    private CircleImageView circleImageView;
    private ImageView imgv_back, imgv_edit_name, imgv_edit_gender_age, imgv_edt_city, imgv_edt_country;
    private TextView tv_name, tv_edit_avatar, tv_gender, tv_age, tv_city, tv_country, tv_time, tv_logout;
    private String accName, gender, age, city, country, currentTime;

    public profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        circleImageView=(CircleImageView) v.findViewById(R.id.profile_photo);
        //imgv_back = (ImageView) v.findViewById(R.id.back);
        imgv_edit_gender_age = (ImageView) v.findViewById(R.id.edit_gender_age);
        imgv_edit_name = (ImageView) v.findViewById(R.id.edit_name);
        imgv_edt_city= (ImageView) v.findViewById(R.id.edit_locate);
        imgv_edt_country = (ImageView) v.findViewById(R.id.edit_country);
        tv_name=(TextView) v.findViewById(R.id.name);
        tv_edit_avatar=(TextView) v.findViewById(R.id.edit_avt);
        tv_gender=(TextView) v.findViewById(R.id.show_gender);
        tv_age=(TextView) v.findViewById(R.id.show_age);
        tv_city=(TextView) v.findViewById(R.id.show_city);
        tv_country=(TextView) v.findViewById(R.id.show_country);
        tv_time=(TextView) v.findViewById(R.id.show_time);
        tv_logout=(TextView) v.findViewById(R.id.log_out);


        //
        imgv_edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEditAccName();
            }
        });
        imgv_edit_gender_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEditGenderAndAge();
            }
        });
        imgv_edt_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEditCity();
            }
        });
        imgv_edt_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEditCountry();
            }
        });

        SetLocalTime();

        return v;
    }
    private void ClickEditAccName()
    {
        final Dialog dialog = new Dialog(getContext());
        dialog.setCancelable(false); //Phải hoàn thành xong trong hộp thoại mới được đóng
        dialog.setContentView(R.layout.change_account_name); //truyền vào layout đã tạo

        //Ánh xạ các view trong hộp thoại change_account_name
        final EditText edt_name = (EditText) dialog.findViewById(R.id.change_name);
        TextView tv_ok = (TextView) dialog.findViewById(R.id.submit);
        TextView tv_cancel = (TextView) dialog.findViewById(R.id.cancel);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accName = edt_name.getText().toString();
                if (accName.length()!=0)
                {
                    tv_name.setText(accName);
                }
                else
                {
                    dialog.cancel();
                }
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private void ClickEditGenderAndAge(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.change_gender_age);

        //Ánh xạ các view trong hộp thoại change_gender_age
        final RadioGroup rdg_gender = (RadioGroup) dialog.findViewById(R.id.rd_gender);
        final EditText edt_age = (EditText) dialog.findViewById(R.id.change_age);
        final RadioButton rd_male = (RadioButton) dialog.findViewById(R.id.male);
        final RadioButton rd_female = (RadioButton) dialog.findViewById(R.id.female);

        TextView tv_ok = (TextView) dialog.findViewById(R.id.submit);
        TextView tv_cancel = (TextView) dialog.findViewById(R.id.cancel);

        if(rd_female.isChecked() || rd_male.isChecked())
        {
            if (rd_female.isChecked())
            {
                rd_male.setChecked(false);
            }
            else {
                rd_male.setChecked(true);
            }

            if (rd_male.isChecked())
            {
                rd_female.setChecked(false);
            }
            else  {
                rd_female.setChecked(true);
            }
        }
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // get selected radio button from radioGroup
                int selectedId = rdg_gender.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                RadioButton rdb_sex = (RadioButton) dialog.findViewById(selectedId);
                gender = rdb_sex.getText().toString();
                tv_gender.setText(gender);
                age = edt_age.getText().toString();
                tv_age.setText(age);
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private void ClickEditCity(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setCancelable(false); //Phải hoàn thành xong trong hộp thoại mới được đóng
        dialog.setContentView(R.layout.change_city); //truyền vào layout đã tạo

        //Ánh xạ các view trong hộp thoại change_account_name
        final EditText edt_city = (EditText) dialog.findViewById(R.id.change_city);
        TextView tv_ok = (TextView) dialog.findViewById(R.id.submit);
        TextView tv_cancel = (TextView) dialog.findViewById(R.id.cancel);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                city = edt_city.getText().toString();
                if (city.length()!=0)
                {
                    tv_city.setText(city);
                }
                else
                {
                    dialog.cancel();
                }
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private void ClickEditCountry()
    {
        final Dialog dialog = new Dialog(getContext());
        dialog.setCancelable(false); //Phải hoàn thành xong trong hộp thoại mới được đóng
        dialog.setContentView(R.layout.change_country); //truyền vào layout đã tạo

        //Ánh xạ các view trong hộp thoại change_account_name
        final EditText edt_country = (EditText) dialog.findViewById(R.id.change_country);
        TextView tv_ok = (TextView) dialog.findViewById(R.id.submit);
        TextView tv_cancel = (TextView) dialog.findViewById(R.id.cancel);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                country = edt_country.getText().toString();
                if (country.length()!=0)
                {
                    tv_country.setText(country);
                }
                else
                {
                    dialog.cancel();
                }
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private void SetLocalTime(){
        currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        tv_time.setText(currentTime);
    }
}

