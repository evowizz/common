package com.evo.commonlib;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.evo.common.view.DimensUtils;
import com.evo.common.view.NavigationBar;
import com.evo.common.view.StatusBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        float sBarSize = StatusBar.getHeight(this, null);
        float nBarSize = NavigationBar.getHeight(this, null);
        float sBarSizePx = DimensUtils.dp2px(this, StatusBar.getHeight(this, getWindow()));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("StatusBar = ").append(sBarSize).append("dp");
        stringBuilder.append("\nNavigationBar = ").append(nBarSize).append("dp");
        stringBuilder.append("\nStatusBar in Px = ").append(sBarSizePx).append("px");

        Toast.makeText(this, stringBuilder, Toast.LENGTH_LONG).show();
    }
}
