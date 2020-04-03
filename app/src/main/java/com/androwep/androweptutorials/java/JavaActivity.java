package com.androwep.androweptutorials.java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.androwep.androweptutorials.R;
import com.androwep.androweptutorials.codedetails.CodeDetailsActivity;


import com.androwep.androweptutorials.databinding.ActivityJavaBinding;
import com.androwep.androweptutorials.home.HomeActivity;
import com.androwep.androweptutorials.util.local.AppConstraints;
import com.androwep.androweptutorials.util.local.model.CodeModel;


import java.util.ArrayList;
import java.util.List;

public class JavaActivity extends AppCompatActivity implements JavaCodeAdapter.JavaCodeItemClickLister {
ActivityJavaBinding mBinding;
private JavaCodeAdapter mAdapter;
private List<CodeModel> codeModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_java);

        codeModels = new ArrayList<>();
        mAdapter = new JavaCodeAdapter(codeModels, this);
        mBinding.coderecycleId.setLayoutManager(new LinearLayoutManager(this));
        //decoration between to item recycler
        mBinding.coderecycleId.addItemDecoration(new DividerItemDecoration(mBinding.coderecycleId.getContext(), DividerItemDecoration.VERTICAL));
        mBinding.coderecycleId.setAdapter(mAdapter);

        initAdapterItems();

    }

    private void initAdapterItems() {
        CodeModel codeModel;

        codeModel = new CodeModel(1, "Java Compiler", "class Simple{  \n" +
                "    public static void main(String args[]){  \n" +
                "     System.out.println(\"Hello Java\");  \n" +
                "    }  \n" +
                "}  ", "public class HelloWorld{ public static void main(String []args){ System.out.println(\"Hello Java\");}}");
        codeModels.add(codeModel);

        codeModel = new CodeModel(2, "PHP Compiler", "<?php\n" +
                "echo \"Hello World!\";\n" +
                "?>", "<?php echo \"Hello World!\";?>");
        codeModels.add(codeModel);


//        codeModel = new CodeModel(2, "If-Else Example", "public class IfExample {  \n" +
//                "public static void main(String[] args) {  \n" +
//
//                "    int age=20;  \n" +
//                "    //checking the age  \n" +
//                "    if(age>18){  \n" +
//                "        System.out.print(\"Age is greater than 18\");  \n" +
//                "    }  \n" +
//                "}  \n" +
//                "}  ",
//
//                "public class IfExample {  \n" +
//                        "public static void main(String[] args) {  \n" +
//
//                        "    int age=20;  \n" +
//
//                        "    if(age>18){  \n" +
//                        "        System.out.print(\"Age is greater than 18\");  \n" +
//                        "    }  \n" +
//                        "}  \n" +
//                        "}  ");
//        codeModels.add(codeModel);
//
//        codeModel = new CodeModel(3, "Java Switch", "public class SwitchExample {  \n" +
//                "public static void main(String[] args) {  \n" +
//                "    //Declaring a variable for switch expression  \n" +
//                "    int number=20;  \n" +
//                "    //Switch expression  \n" +
//                "    switch(number){  \n" +
//                "    //Case statements  \n" +
//                "    case 10: System.out.println(\"10\");  \n" +
//                "    break;  \n" +
//                "    case 20: System.out.println(\"20\");  \n" +
//                "    break;  \n" +
//                "    case 30: System.out.println(\"30\");  \n" +
//                "    break;  \n" +
//                "    //Default case statement  \n" +
//                "    default:System.out.println(\"Not in 10, 20 or 30\");  \n" +
//                "    }  \n" +
//                "}  \n" +
//                "}  ",
//                "public class SwitchExample {  \n" +
//                        "public static void main(String[] args) {  \n" +
//
//                        "    int number=20;  \n" +
//
//                        "    switch(number){  \n" +
//
//                        "    case 10: System.out.println(\"10\");  \n" +
//                        "    break;  \n" +
//                        "    case 20: System.out.println(\"20\");  \n" +
//                        "    break;  \n" +
//                        "    case 30: System.out.println(\"30\");  \n" +
//                        "    break;  \n" +
//
//                        "    default:System.out.println(\"Not in 10, 20 or 30\");  \n" +
//                        "    }  \n" +
//                        "}  \n" +
//                        "}  ");
//        codeModels.add(codeModel);
//
//        codeModel = new CodeModel(4, "For Loop", "//Java Program to demonstrate the example of for loop  \n" +
//                "//which prints table of 1  \n" +
//                "public class ForExample {  \n" +
//                "public static void main(String[] args) {  \n" +
//                "    //Code of Java for loop  \n" +
//                "    for(int i=1;i<=10;i++){  \n" +
//                "        System.out.println(i);  \n" +
//                "    }  \n" +
//                "}  \n" +
//                "}",
//                "public class ForExample {  \n" +
//                        "public static void main(String[] args) {  \n" +
//
//                        "    for(int i=1;i<=10;i++){  \n" +
//                        "        System.out.println(i);  \n" +
//                        "    }  \n" +
//                        "}  \n" +
//                        "}");
//
//        codeModel = new CodeModel(5, "Nested For Loop", "public class NestedForExample {  \n" +
//                "public static void main(String[] args) {  \n" +
//                "//loop of i  \n" +
//                "for(int i=1;i<=3;i++){  \n" +
//                "//loop of j  \n" +
//                "for(int j=1;j<=3;j++){  \n" +
//                "        System.out.println(i+\" \"+j);  \n" +
//                "}//end of i  \n" +
//                "}//end of j  \n" +
//                "}  \n" +
//                "}  ", "public class NestedForExample {  public static void main(String[] args) {  for(int i=1;i<=3;i++){  for(int j=1;j<=3;j++){  System.out.println(i+\" \"+j);  }  }}  }  ");
//        codeModels.add(codeModel);

        mAdapter.notifyDataSetChanged();
    }

    public void WhenBackClick(View view) {
        Intent intent = new Intent(JavaActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(JavaActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void OnJavaItemClick(CodeModel item) {

        Intent intent = new Intent(JavaActivity.this, CodeDetailsActivity.class);
        intent.putExtra(AppConstraints.IntentConstrants.CODE_TITLE, item.getCodeTitle());
        intent.putExtra(AppConstraints.IntentConstrants.CODE_DETAILS, item.getCodeDetails());
        intent.putExtra(AppConstraints.IntentConstrants.CODE_Filter, item.getCodeFilter());
        startActivity(intent);

    }
}
