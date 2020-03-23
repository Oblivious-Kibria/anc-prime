package com.anc.ancprime.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.anc.ancprime.R;
import com.anc.ancprime.data.model.customer.CustomerData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.florent37.shapeofview.shapes.CircleView;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.anc.ancprime.data.networking.HttpParams.CUSTOMERS_IMAGE_BASE_URL;


public class TopCustomerListAdapter extends RecyclerView.Adapter<TopCustomerListAdapter.ViewHolder> {



    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private List<CustomerData> mArrayList;

    private final String currencySign;
    private DecimalFormat formatter = new DecimalFormat("#,###,##0");



    public TopCustomerListAdapter(Context context, List<CustomerData> arrayList, OnItemClickListener onItemClickListener) {
        mContext = context;
        this.mArrayList = arrayList;
        this.onItemClickListener = onItemClickListener;
        currencySign = context.getResources().getString(R.string.currency_sign);
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_customer, parent, false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerData customerData = mArrayList.get(position);
        holder.tvName.setText(customerData.getCustName());
        if(customerData.getCustAddress()!=null){
            holder.tvAddress.setText(customerData.getCustAddress());
        }
        else {
            holder.tvAddress.setText("------");
        }
        String purchaseValue = formatter.format(customerData.getAmount());
        holder.tvPurchaseValue.setText(purchaseValue+" "+currencySign);

        setImageWithGlide(holder.ivProfilePic, customerData.getCustOwnerProfileImage(), 100, 100);
    }



    protected void setImageWithGlide(ImageView imageView, Object imagePath, int width, int height) {
        String path = CUSTOMERS_IMAGE_BASE_URL+imagePath;
        Glide.with(mContext)
                .load(path)
                .apply(new RequestOptions().override(width, height))
                .apply(new RequestOptions().placeholder(R.drawable.user_temp).error(R.drawable.user_temp))
                .into(imageView);
    }



    @Override
    public int getItemCount() {
        return mArrayList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_name)
        AppCompatTextView tvName;
        @BindView(R.id.tv_address)
        AppCompatTextView tvAddress;
        @BindView(R.id.iv_profile_pic)
        AppCompatImageView ivProfilePic;
        @BindView(R.id.cv_profile_pic)
        CircleView cvProfilePic;
        @BindView(R.id.tv_purchase_value)
        AppCompatTextView tvPurchaseValue;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }




        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }


    }


    public interface OnItemClickListener {


        void onItemClick(View view, int position);


    }


}
