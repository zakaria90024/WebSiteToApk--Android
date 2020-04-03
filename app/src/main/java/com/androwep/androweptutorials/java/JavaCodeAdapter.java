package com.androwep.androweptutorials.java;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;



import com.androwep.androweptutorials.R;

import com.androwep.androweptutorials.databinding.ItemCodeBinding;
import com.androwep.androweptutorials.util.local.model.CodeModel;

import java.util.List;

public class JavaCodeAdapter extends RecyclerView.Adapter<JavaCodeAdapter.JavaCodeAdapterViewHolder> {

    List<CodeModel> codeModels;
    private ItemCodeBinding mItemBinding;
    JavaCodeItemClickLister javaCodeItemClickLister;

    public JavaCodeAdapter(List<CodeModel> codeModels, JavaCodeItemClickLister javaCodeItemClickLister) {
        this.codeModels = codeModels;
        this.javaCodeItemClickLister=javaCodeItemClickLister;
    }

    @NonNull
    @Override
    public JavaCodeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mItemBinding = DataBindingUtil.inflate(inflater, R.layout.item_code,parent,false);
        View view = mItemBinding.getRoot();
        JavaCodeAdapterViewHolder holder = new JavaCodeAdapterViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JavaCodeAdapterViewHolder holder, final int position) {
        mItemBinding.numberId.setText(""+(position+1));
        mItemBinding.titlebarId.setText(codeModels.get(position).getCodeTitle());
        if(javaCodeItemClickLister!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    javaCodeItemClickLister.OnJavaItemClick(codeModels.get(position));
                }
            });
//            holder.itemView.setOnClickListener(v -> {
//                javaCodeItemClickLister.OnJavaItemClick(codeModels.get(position));
//            });
        }
        //codeModels.get(position).getCodeFilter() = codeModels.get(position).getCodeFilter().replace("\n", "");
    }

    @Override
    public int getItemCount() {
        return codeModels.size();
    }

     static class JavaCodeAdapterViewHolder extends RecyclerView.ViewHolder{

        public JavaCodeAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public interface JavaCodeItemClickLister{
        public void OnJavaItemClick(CodeModel item);
    }
}
