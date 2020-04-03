package com.androwep.androweptutorials.codedetails;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;



import com.androwep.androweptutorials.R;

import com.androwep.androweptutorials.databinding.ActivityCodeDetailsBinding;
import com.androwep.androweptutorials.java.JavaActivity;
import com.androwep.androweptutorials.util.local.AppConstraints;
import com.androwep.androweptutorials.util.remote.model.CompileResponse;
import com.androwep.androweptutorials.util.remote.retrofit.RemoteApiInterface;
import com.androwep.androweptutorials.util.remote.retrofit.RemoteApiProvider;


import io.github.kbiakov.codeview.adapters.Options;
import io.github.kbiakov.codeview.classifier.CodeProcessor;
import io.github.kbiakov.codeview.highlight.ColorTheme;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class   CodeDetailsActivity extends AppCompatActivity {
    ActivityCodeDetailsBinding mBinding;
    private RemoteApiInterface mService;
    Dialog dialog;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_code_details);
        //for api call
        mService= RemoteApiProvider.getInstance().getRemoteApi();

        final String codetitel = getIntent().getStringExtra(AppConstraints.IntentConstrants.CODE_TITLE);
        mBinding.toolbarTitle.setText(codetitel);
        String codeDetails = getIntent().getStringExtra(AppConstraints.IntentConstrants.CODE_DETAILS);
        final String codeFilter = getIntent().getStringExtra(AppConstraints.IntentConstrants.CODE_Filter);

//        mService = RemoteApiProvider.getInstance().getRemoteApi();
//        mService = RemoteApiProvider.getInstance().getRemoteApi();

// train classifier on app start
        CodeProcessor.init(this);

//        mBinding.codeview.setOptions(Options.Default.get(this).withLanguage(AppConstraints.Language.JAVA_LAN).withCode("class Simple{  \n" +
//                "    public static void main(String args[]){  \n" +
//                "     System.out.println(\"Hello Java\");  \n" +
//                "    }  \n" +
//                "}  ").withTheme(ColorTheme.MONOKAI));

          mBinding.codeview.setOptions(Options.Default.get(this).withLanguage(AppConstraints.Language.JAVA_LAN).withCode(codeDetails).withTheme(ColorTheme.SOLARIZED_LIGHT));

          mBinding.editText2.setText(codeFilter);

          mBinding.btnRun.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String editedCode =  mBinding.editText2.getText().toString();
//                         runJavaCode(editedstring);
                  mBinding.editText2.setText(editedCode);

                  if(codetitel.equals("Java Compiler")){
                      runJavaCode(editedCode);
                  }

                  if(codetitel.equals("PHP Compiler")){
                      runPHPcode(editedCode);
                  }

                  progressDialog = new ProgressDialog(CodeDetailsActivity.this);
                  progressDialog.setMessage("Loading..."); // Setting Message
//                progressDialog.setTitle("Please Wait"); // Setting Title
                  progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                  progressDialog.show(); // Display Progress Dialog
                  progressDialog.setCancelable(true);
                  new Thread(new Runnable() {
                      public void run() {
                          try {
                              Thread.sleep(5000);
                          } catch (Exception e) {
                              e.printStackTrace();
                          }
                          progressDialog.dismiss();
                      }
                  }).start();
              }
          });

          mBinding.icBack.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(CodeDetailsActivity.this, JavaActivity.class);
                  startActivity(intent);
                  finish();
              }
          });



//          mBinding.button.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View v) {
//                  final AlertDialog dialogBuilder = new AlertDialog.Builder(CodeDetailsActivity.this).create();
//                  LayoutInflater inflater = CodeDetailsActivity.this.getLayoutInflater();
//                  getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
//                  View dialogView = inflater.inflate(R.layout.custom_dialog, null);
//
//
//                  final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
//                  editText.setText(codeFilter);
//
//
//                  Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
//                  Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);
//
//
//
//                  button2.setOnClickListener(new View.OnClickListener() {
//                      @Override
//                      public void onClick(View view) {
//                          dialogBuilder.dismiss();
//                      }
//                  });
//
//                  button1.setOnClickListener(new View.OnClickListener() {
//                      @Override
//                      public void onClick(View view) {
//                          // DO SOMETHINGS
//                         String editedstring =  editText.getText().toString();
//                         runJavaCode(editedstring);
//                          dialogBuilder.dismiss();
//                      }
//                  });
//
//                  dialogBuilder.setView(dialogView);
//                  dialogBuilder.show();
//
//              }
//          });

        if(codetitel.equals("Java Compiler")){
            runJavaCode(codeFilter);
        }


//        mBinding.btnRun.setOnClickListener(v -> {
//
//        });
        if(codetitel.equals("PHP Compiler")){
            runPHPcode(codeFilter);
        }
    }

    private void runPHPcode(String codeFilter) {
        codeFilter = codeFilter.replace("\n", "");
        codeFilter = codeFilter.replace("\"", "\\\"");
        mBinding.editText2.setText(codeFilter);
        String string = "{\n" +
                "\t\"clientId\":\"a7e213b2d1bd70f88b593fedcc1df27\",\n" +
                "\t\"clientSecret\":\"163ef8dfba087a65851334090c9588e8a0c43b4f483e12410c950a13cbaf933\",\n" +
                "\t\"script\":\"" + codeFilter + "\",\n" +
                "\t\"stdin\":\"\",\n" +
                "\t\"language\":\"php\",\n" +
                "\t\"versionIndex\":\"0\"\n" +
                "}";

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (string));

        mService.executeCode(body).enqueue(new Callback<CompileResponse>() {

            @Override
            public void onResponse(Call<CompileResponse> call, Response<CompileResponse> response) {
                if(response.isSuccessful()){
                    CompileResponse response1 =response.body();

                    //Toast.makeText(getBaseContext(), response1.toString(),Toast.LENGTH_LONG).show();

                    StringBuilder stringBuilder=new StringBuilder();
                    stringBuilder.append(response1.getOutput()+"\n");
                    stringBuilder.append("\n"+"----------"+"\n");
                    stringBuilder.append("CPU Time:"+response1.getCpuTime()+"\n");
                    stringBuilder.append("Memory:"+response1.getMemory());
                    Log.d("chk","compile success");

                    mBinding.outputtext.setText(stringBuilder);

                }
                else {
                    Log.d("chk","faild"+response.code());
                }
            }

            @Override
            public void onFailure(Call<CompileResponse> call, Throwable t) {
                Log.d("chk","faild"+t.getMessage());
            }
        });
    }

    private void runJavaCode(String codeFilter) {
        codeFilter = codeFilter.replace("\n", "");
        codeFilter = codeFilter.replace("\"", "\\\"");

        String string = "{\n" +
                "\t\"clientId\":\"a7e213b2d1bd70f88b593fedcc1df27\",\n" +
                "\t\"clientSecret\":\"163ef8dfba087a65851334090c9588e8a0c43b4f483e12410c950a13cbaf933\",\n" +
                "\t\"script\":\"" + codeFilter + "\",\n" +
                "\t\"stdin\":\"\",\n" +
                "\t\"language\":\"java\",\n" +
                "\t\"versionIndex\":\"0\"\n" +
                "}";

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (string));

        mService.executeCode(body).enqueue(new Callback<CompileResponse>() {

            @Override
            public void onResponse(Call<CompileResponse> call, Response<CompileResponse> response) {
                if(response.isSuccessful()){
                    CompileResponse response1 =response.body();

                    //Toast.makeText(getBaseContext(), response1.toString(),Toast.LENGTH_LONG).show();

                    StringBuilder stringBuilder=new StringBuilder();
                    stringBuilder.append(response1.getOutput()+"\n");
                    stringBuilder.append("\n"+"----------"+"\n");
                    stringBuilder.append("CPU Time:"+response1.getCpuTime()+"\n");
                    stringBuilder.append("Memory:"+response1.getMemory());
                    Log.d("chk","compile success");

                    mBinding.outputtext.setText(stringBuilder);

                }
                else {
                    Log.d("chk","faild"+response.code());
                }
            }

            @Override
            public void onFailure(Call<CompileResponse> call, Throwable t) {
                Log.d("chk","faild"+t.getMessage());
            }
        });
    }

}
