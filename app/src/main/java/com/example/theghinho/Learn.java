package com.example.theghinho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.theghinho.DAO.CardDAO;
import com.example.theghinho.DAO.LearningLogDAO;
import com.example.theghinho.Model.Card;
import com.example.theghinho.Model.Folder;
import com.example.theghinho.Model.LearningLog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Learn extends AppCompatActivity {
    TextView txtFontCard, txtBackCard, txtKho,txtMoHo,txtVua,txtDe;
    Button btnShowDA, btnKho, btnMoHo, btnVua, btnDe, btnGoHomePage;
    List<LearningLog> learningLogs;
    Card card;
    int currentLearningLog = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        receiveData();
        bindingView();
        bindingAction();

    }

    private void receiveData() {
        Intent i = getIntent();

        Bundle bundle = getIntent().getBundleExtra("cacTuCanHoc");
        learningLogs = (List<LearningLog>) bundle.getSerializable("learningCards");
    }

    private void bindingAction() {
        btnShowDA.setOnClickListener(this::onClickShowDA);
        btnDe.setOnClickListener(this::onClickDe);
        btnMoHo.setOnClickListener(this::onClickMoHo);
        btnVua.setOnClickListener(this::onClickVua);
        btnKho.setOnClickListener(this::onClickKho);
        btnGoHomePage.setOnClickListener(this::onHomePageClick);
    }

    private void onHomePageClick(View view) {
        finish();
    }

    private void onClickKho(View view) {
        setLeared(learningLogs.get(currentLearningLog).getLogId());

        LearningLog logInserted =    recordLearningLog(1,learningLogs.get(currentLearningLog));

        learningLogs.add(logInserted);
        learningLogs.remove(learningLogs.get(currentLearningLog));
        if(learningLogs.size()==0)
        {
            txtFontCard.setText("Bạn đã học xong");
            txtBackCard.setVisibility(View.INVISIBLE);
            setInVisibleButtonDiem();
            btnShowDA.setVisibility(View.INVISIBLE);
            setEmptyTxtTime();
        }else {

            chuyenCau();

        }
    }

    private LearningLog recordLearningLog(int q, LearningLog learningLog) {
        if(learningLog == null ||  q < 0 || q>5) return null;
        float ef = learningLog.getEf();
        int interval;
        int n = learningLog.getRepeatTime();
        if(q >= 3){
            if(n ==0){
                interval = 1;
            }else if(n==1)
                interval = 6;
            else {
                interval = (int) (learningLog.getInterval()*learningLog.getEf());
            }
            n++;
        }else {
            n = 0;
            interval = 0;
        }
        ef = (float) (ef + (0.1 - (5 - q)*(0.08 + (5 - q)*0.02)));
        if(ef < 1.3)
            ef = 1.3f;

        LearningLog log = new LearningLog();
        Calendar cal = Calendar.getInstance();
        log.setCardId(learningLog.getCardId());

        Date currentDate = cal.getTime();

        log.setDate(currentDate);




        cal.add(Calendar.DAY_OF_MONTH, interval);
        Date dateLearn = cal.getTime();
        log.setDateLearn(dateLearn);

        log.setInterval(interval);
        log.setRepeatTime(n);
        log.setEf(ef);
        log.setLearned(false);
        log.setFolderId(learningLog.getFolderId());




        LearningLogDAO learningLogDAO = new LearningLogDAO(getApplicationContext());
        LearningLog logInserted =   learningLogDAO.addNewLearningLog(log);
        return logInserted;

    }

    private void setLeared(int logId) {
        LearningLogDAO learningLogDAO = new LearningLogDAO(getApplicationContext());
        learningLogDAO.updateIsLearned(learningLogs.get(currentLearningLog).getLogId());
    }

    private void onClickVua(View view) {
        setLeared(learningLogs.get(currentLearningLog).getLogId());

        recordLearningLog(4,learningLogs.get(currentLearningLog));


        learningLogs.remove(learningLogs.get(currentLearningLog));
        if(learningLogs.size()==0)
        {
            txtFontCard.setText("Bạn đã học xong");
            txtBackCard.setVisibility(View.INVISIBLE);
            setInVisibleButtonDiem();
            btnShowDA.setVisibility(View.INVISIBLE);
            setEmptyTxtTime();
        }else {

            chuyenCau();

        }



    }

    private void chuyenCau() {
        setInVisibleButtonDiem();
        btnShowDA.setVisibility(View.VISIBLE);
        txtBackCard.setVisibility(View.INVISIBLE);
        setEmptyTxtTime();
       bindingCardToScreen();




    }

    private void onClickMoHo(View view) {
        setLeared(learningLogs.get(currentLearningLog).getLogId());

        LearningLog logInserted =  recordLearningLog(2,learningLogs.get(currentLearningLog));

        learningLogs.add(logInserted);
        learningLogs.remove(learningLogs.get(currentLearningLog));
        if(learningLogs.size()==0)
        {
            txtFontCard.setText("Bạn đã học xong");
            txtBackCard.setVisibility(View.INVISIBLE);
            setInVisibleButtonDiem();
            btnShowDA.setVisibility(View.INVISIBLE);
            setEmptyTxtTime();
        }else {

            chuyenCau();

        }

    }

    private void onClickDe(View view) {
        setLeared(learningLogs.get(currentLearningLog).getLogId());

        recordLearningLog(5,learningLogs.get(currentLearningLog));


        learningLogs.remove(learningLogs.get(currentLearningLog));
        if(learningLogs.size()==0)
        {
            txtFontCard.setText("Bạn đã học xong");
            txtBackCard.setVisibility(View.INVISIBLE);
            setInVisibleButtonDiem();
            btnShowDA.setVisibility(View.INVISIBLE);
            setEmptyTxtTime();
        }else {

            chuyenCau();

        }

    }

    private void onClickShowDA(View view) {

        txtBackCard.setVisibility(View.VISIBLE);
        setVisibleButtonDiem();
        setTxtTime();
    }

    private void setTxtTime() {
        txtKho.setText("1 phút");
        txtMoHo.setText("ngày hôm nay");
        String s = "sau: ";
       int vua =  caculateInterval(4,learningLogs.get(currentLearningLog));
        txtVua.setText(s+ vua +" ngày");
        int de = caculateInterval(5,learningLogs.get(currentLearningLog));
        txtDe.setText(s+de + " ngày");

    }

    private int caculateInterval(int q, LearningLog learningLog) {
        if(learningLog == null ||  q < 0 || q>5) return -1;
        int interval = 0;
        int n;
        if(q >= 3){
            n = learningLog.getRepeatTime();
            if(n ==0){
                interval = 1;
            }else if(n==1)
                interval = 6;
            else {
                interval = (int) (learningLog.getInterval()*learningLog.getEf());
            }
        }
        return interval;
    }

    private void setVisibleButtonDiem() {
        btnDe.setVisibility(View.VISIBLE);
        btnVua.setVisibility(View.VISIBLE);
        btnMoHo.setVisibility(View.VISIBLE);
        btnKho.setVisibility(View.VISIBLE);
        btnShowDA.setVisibility(View.INVISIBLE);
    }
    private void setInVisibleButtonDiem() {
        btnDe.setVisibility(View.INVISIBLE);
        btnVua.setVisibility(View.INVISIBLE);
        btnMoHo.setVisibility(View.INVISIBLE);
        btnKho.setVisibility(View.INVISIBLE);
    }

    private void bindingView() {

        bindingButton();
        bindingTxtCard();
        bindingTxtTime();















    }

    private void bindingTxtTime() {
        txtDe = findViewById(R.id.txtDe);
        txtKho = findViewById(R.id.txtKho);
        txtMoHo = findViewById(R.id.txtMoHo);
        txtVua = findViewById(R.id.txtVua);
        setEmptyTxtTime();


    }

    private void setEmptyTxtTime() {
        txtKho.setText("");
        txtVua.setText("");
        txtDe.setText("");
        txtMoHo.setText("");
    }


    private void bindingTxtCard() {
        txtBackCard = findViewById(R.id.txtBackLearn);
        txtFontCard = findViewById(R.id.txtFontLearn);
        txtBackCard.setVisibility(View.INVISIBLE);
        bindingCardToScreen();


    }

    private void bindingCardToScreen() {
        CardDAO cardDAO = new CardDAO(getApplicationContext());
        card = cardDAO.getCardById(learningLogs.get(currentLearningLog).getCardId());

        txtFontCard.setText(card.getFontCard());
        txtBackCard.setText(card.getBackCard());
    }


    private void bindingButton() {
        btnShowDA = findViewById(R.id.btnShowDA);
        btnDe = findViewById(R.id.btnDe);
        btnKho = findViewById(R.id.btnKho);
        btnMoHo = findViewById(R.id.btnMoHo);
        btnVua = findViewById(R.id.btnVua);
        btnGoHomePage = findViewById(R.id.btnGoHomePage);
        setInVisibleButtonDiem();



    }


}