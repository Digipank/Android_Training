package com.layoutdemo.adpter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.layoutdemo.Activity.DisplayAllRecordsActivtity;
import com.layoutdemo.Activity.FromFillActivity;
import com.layoutdemo.R;
import com.layoutdemo.Utils.Utils;
import com.layoutdemo.database.GetData;
import com.layoutdemo.database.StudentDB;

import java.util.ArrayList;

public class DisplaRecordAdpter extends RecyclerView.Adapter<DisplaRecordAdpter.ViewHolder> {
    private ArrayList<GetData> listdata;
    private Context context;
    private Utils utils;

    public DisplaRecordAdpter(ArrayList<GetData> list, Context applicationContext, DisplayAllRecordsActivtity displayAllRecordsActivtity) {
        this.listdata = list;
        this.context = displayAllRecordsActivtity;
        this.utils=new Utils(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_db_recoreds, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        try {

                final GetData studentDataDO=listdata.get(position);

                //set the text box field
            holder.txtName.setText(studentDataDO.getFname().toString()+ " "+ studentDataDO.getLname().toString());
            holder.txtMobile.setText(studentDataDO.getMobile());
            holder.txtEmail.setText(studentDataDO.getEmail().toString());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.ToastMessage("Edit click",context);


                Intent callEdit=new Intent(context, FromFillActivity.class);
                callEdit.putExtra("btnTitle","Edit Reocrd");
                callEdit.putExtra("namef",studentDataDO.getFname());
                callEdit.putExtra("namel",studentDataDO.getLname());
                callEdit.putExtra("gender",studentDataDO.getGender());
                callEdit.putExtra("pass",studentDataDO.getPass());
                callEdit.putExtra("mobile",studentDataDO.getMobile());
                callEdit.putExtra("bod",studentDataDO.getBod());
                callEdit.putExtra("email",studentDataDO.getEmail());
                context.startActivity(callEdit);

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int getCurrentPosition=position;
                GetData getCurrentDataClickDO= studentDataDO;

                callDelteDataDBdReocrds(getCurrentDataClickDO,position);

            }
        });



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callDelteDataDBdReocrds(GetData getCurrentDataClickDO, int position) {

        try{
            StudentDB studentDB = new StudentDB(context);

            int deleterecord=studentDB.DelteReocrd(getCurrentDataClickDO.getEmail());
            Log.i("delete","delete log reocrds :"+deleterecord);

            if(deleterecord>0)
            {
                utils.ToastMessage("Delete record successfully",context);
                //aray list also fresh after the delete success
                ((DisplayAllRecordsActivtity)context).removeArrayListIndex(getCurrentDataClickDO);
//                DisplayAllRecordsActivtity.list.remove(getCurrentDataClickDO);
            }
            else{
                utils.ToastMessage("Delete record unsuccessfully",context);

            }
            studentDB.close();
            notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//own name object
        TextView txtName,txtEmail,txtMobile;
        LinearLayout lyDispaly;
        TextView btnEdit,btnDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            txtEmail=itemView.findViewById(R.id.txt_row_stud_email);
            txtMobile=itemView.findViewById(R.id.txt_row_stud_mobile);
            txtName=itemView.findViewById(R.id.txt_row_stud_name);
            lyDispaly=itemView.findViewById(R.id.ly_row_display);
            btnEdit=itemView.findViewById(R.id.row_btn_edit);
            btnDelete=itemView.findViewById(R.id.row_btn_delte);
        }
    }
}
