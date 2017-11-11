package com.example.babyv20.atha.Controller.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.babyv20.atha.Controller.tabs.TabDemographic;
import com.example.babyv20.atha.Controller.tabs.TabMedHist;
import com.example.babyv20.atha.Controller.tabs.TabVisits;
import com.example.babyv20.atha.Model.Patients.Patient;

/**
 * Created by BABY v2.0 on 2/27/2017.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private String[] tabTitles = {"Demographic", "Visits", "Medical History"};
    private Bundle pName;

    public TabPagerAdapter(FragmentManager fm, Bundle name) {
        super(fm);
        this.pName = name;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabDemographic tab1 = new TabDemographic();
                tab1.setArguments(pName);
                return tab1;
            case 1:
                TabVisits tab2 = new TabVisits();
                tab2.setArguments(pName);
                return tab2;
            case 2:
                TabMedHist tab3 = new TabMedHist();
                tab3.setArguments(pName);
                return tab3;
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       return tabTitles[position];
    }

}
