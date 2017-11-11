package com.example.babyv20.atha.Controller.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.babyv20.atha.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by BABY v2.0 on 3/17/2017.
 */

public class PatientFormPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragList;
    private Fragment contactFrag, whoFrag, guardianFrag, employerFrag, insuranceFrag,
            miscFrag, choiceFrag, statFrag;
   private String[] pageTitles;

    public PatientFormPageAdapter(FragmentManager fm, String[] pageTitles) {
        super(fm);
        fragList = new LinkedList<>();
        initializeFragments ();
        this.pageTitles = pageTitles;

    }

    @Override
    public Fragment getItem(int position) {
      return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }

    private void initializeFragments (){
        whoFrag = new Child_Who();
        fragList.add(whoFrag);
        contactFrag = new Child_Contact();
        fragList.add(contactFrag);
        choiceFrag = new Child_Choices();
        fragList.add(choiceFrag);
        statFrag = new Child_Stat();
        fragList.add(statFrag);
        employerFrag = new Child_Employer();
        fragList.add(employerFrag);
        guardianFrag = new Child_Guardian();
        fragList.add(guardianFrag);
        insuranceFrag = new Child_Insurance();
        fragList.add(insuranceFrag);
        miscFrag = new Child_Misc();
        fragList.add(miscFrag);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }

}
