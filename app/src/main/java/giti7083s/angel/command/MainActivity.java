package giti7083s.angel.command;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import giti7083s.angel.command.command.CambiarContraste;
import giti7083s.angel.command.invoker.Invoker;
import giti7083s.angel.command.receiver.ImageReceiver;


public class MainActivity extends AppCompatActivity {




        @Bind(R.id.iv_utng)
        ImageView ivUtng;
        @Bind(R.id.btnBajarAlpha)
        Button btnBajarAlpha;
        @Bind(R.id.btnSubirAlpha)
        Button btnSubirAlpha;
        @Bind(R.id.section_left)
        RelativeLayout sectionLeft;
        @Bind(R.id.btnRedo)
        Button btnRedo;
        @Bind(R.id.btnUndo)
        Button btnUndo;
        @Bind(R.id.section_hacer)
        LinearLayout sectionDo;
        @Bind(R.id.rvHistory)
        RecyclerView rvHistory;
        @Bind(R.id.container)
        LinearLayout container;
        @Bind(R.id.tv_alpha)
        TextView tvAlpha;
        @Bind(R.id.section_cambiar_contraste)
        LinearLayout sectionCambiarAlphaAlpha;

        static final float ALPHA_STEP = 0.1f;

        HistoryAdapter adapter;
        ArrayList<String> textHistorys;

        ImageReceiver imageReceiver;
        Invoker invoker = new Invoker();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);

            //list history
            rvHistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            textHistorys = invoker.getHistoryList();
            adapter = new HistoryAdapter(textHistorys);
            rvHistory.setAdapter(adapter);

            //image receiver
            imageReceiver = new ImageReceiver(ivUtng);

            //callback when history changed
            invoker.setInvokerListener(new Invoker.InvokerListener() {
                @Override
                public void commandAdded() {
                    adapter.notifyItemInserted(0);
                }

                @Override
                public void commandRemoved() {
                    adapter.notifyItemRemoved(0);
                }
            });
        }
        @OnClick({R.id.btnSubirAlpha, R.id.btnBajarAlpha, R.id.btnRedo, R.id.btnUndo})
        void sayHi(View view) {
            switch (view.getId()) {
                case R.id.btnUndo:
                    invoker.undo();
                    break;
                case R.id.btnRedo:
                    invoker.redo();
                    break;
                case R.id.btnBajarAlpha:
                    doChangeAlpha(-ALPHA_STEP);
                    break;
                case R.id.btnSubirAlpha:
                    doChangeAlpha(ALPHA_STEP);
                    break;
            }

            tvAlpha.setText(String.format("%.1f", ivUtng.getAlpha()));
        }

        void doChangeAlpha(float alpha) {
            CambiarContraste command = new CambiarContraste(imageReceiver, alpha);
            invoker.addCommand(command);
        }
}
