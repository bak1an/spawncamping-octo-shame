package so.bak1an.octoshame.base;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import so.bak1an.octoshame.ListActivity;
import so.bak1an.octoshame.PointsMap;


public class BaseActivity extends FragmentActivity implements ActionBar.TabListener {

    private String[] tabs = { "Map", "List" };
    private ActionBar actionBar;
    protected String tabName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (String tab : tabs) {
            ActionBar.Tab t = actionBar.newTab().setText(tab)
                    .setTabListener(this);
            actionBar.addTab(t, tab.equals(this.tabName));
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        Class target;
        switch (tab.getPosition()) {
            case 0:
                target = PointsMap.class;
                break;
            case 1:
                target = ListActivity.class;
                break;
            default:
                return;
        }
        if (this.getClass().equals(target)) return;
        Intent nextActivity = new Intent(this, target);
        startActivity(nextActivity);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}

}
