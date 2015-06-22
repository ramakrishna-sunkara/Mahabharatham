package ch.swissonid.design_lib_sample.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ch.swissonid.design_lib_sample.util.ParvamObj;

public class RVArrayAdapter extends RecyclerView.Adapter<RVArrayAdapter.ViewHolder> {

    private ArrayList<ParvamObj> mData;
    private Context context;

    public RVArrayAdapter(Context context, ArrayList<ParvamObj> data) {
        mData = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(android.R.layout.simple_list_item_1,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Typeface faceShreeTelugu = Typeface.createFromAsset(context.getAssets(),
                "SHREETEL0900.ttf");

        viewHolder.mTextView.setTypeface(faceShreeTelugu);
        viewHolder.mTextView.setText(mData.get(position).getParvamName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView)itemView;
        }
    }
}
