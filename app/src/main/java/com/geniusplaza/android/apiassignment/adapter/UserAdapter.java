package com.geniusplaza.android.apiassignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geniusplaza.android.apiassignment.Data.Users;
import com.geniusplaza.android.apiassignment.R;
import java.util.List;

/*Custom recylcer adapter to render list data
* */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private List<Users> mItems;
    private Context mContext;

    public UserAdapter(Context context, List<Users> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            Users user = mItems.get(position);
            try {
                holder.firstName.setText(mContext.getResources().getString(R.string.fName)+user.getFirstName());
                holder.lastName.setText(mContext.getResources().getString(R.string.lName)+user.getLastName());
                String imageFile = user.getImage();

                //Using Glide library for image downloading and caching
                Glide.with(holder.imageView.getContext())
                        .load(imageFile)
                        .into(holder.imageView);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView firstName;
        public TextView lastName;
        public ImageView imageView;
        public View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            firstName = (TextView) itemView.findViewById(R.id.firstNameText);
            lastName = (TextView) itemView.findViewById(R.id.lastNameText);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            mView = itemView;
        }
    }

}
