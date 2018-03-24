package com.example.hp.oralpractice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.hp.oralpractice.helper.BottomNavigationViewHelper;
import com.example.hp.oralpractice.ui.fragment.InformFragment;
import com.example.hp.oralpractice.ui.fragment.MainFragment;
import com.example.hp.oralpractice.ui.fragment.MyselfFragment;
import com.example.hp.oralpractice.ui.fragment.PracticeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.frame)
    FrameLayout frame;

    private static final String TAG = "MainActivity";
    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "bottomNavigationSelectItem";
    private static final int FRAGMENT_MAIN = 0;
    private static final int FRAGMENT_PRACTICE = 1;
    private static final int FRAGMENT_INFORM = 2;
    private static final int FRAGMENT_MYSELF = 3;
    private int position;
    private long exitTime = 0;

    private MainFragment mainFragment;
    private PracticeFragment practiceFragment;
    private InformFragment informFragment;
    private MyselfFragment myselfFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        if (savedInstanceState != null){
            mainFragment =(MainFragment)getSupportFragmentManager().findFragmentByTag(MainFragment.class.getName());
            practiceFragment =(PracticeFragment)getSupportFragmentManager().findFragmentByTag(PracticeFragment.class.getName());
            informFragment =(InformFragment) getSupportFragmentManager().findFragmentByTag(InformFragment.class.getName());
            myselfFragment =(MyselfFragment) getSupportFragmentManager().findFragmentByTag(MyselfFragment.class.getName());
            showFragment(savedInstanceState.getInt(POSITION));
            bottomNavigationView.setSelectedItemId(savedInstanceState.getInt(SELECT_ITEM));
        }else{
            showFragment(FRAGMENT_MAIN);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(POSITION, position);
        outState.putInt(SELECT_ITEM, bottomNavigationView.getSelectedItemId());
    }

    //加载主界面
    private void initView() {

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        showFragment(FRAGMENT_MAIN);

                        break;
                    case R.id.menu_item_practice:
                        showFragment(FRAGMENT_PRACTICE);

                        break;
                    case R.id.menu_item_inform:
                        showFragment(FRAGMENT_INFORM);

                        break;
                    case R.id.menu_item_mine:
                        showFragment(FRAGMENT_MYSELF);

                        break;
                }
                return true;
            }
        });


    }


   private void showFragment(int index){
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       hideFragment(transaction);
        position = index;
       switch (index){
           case FRAGMENT_MAIN:
                if (mainFragment==null){
                    mainFragment = MainFragment.getInstance();
                    transaction.add(R.id.frame,mainFragment,MainFragment.class.getName());
                }else{
                    transaction.show(mainFragment);
                }
                break;
           case FRAGMENT_PRACTICE:
               if (practiceFragment==null){
                   practiceFragment = PracticeFragment.getInstance();
                   transaction.add(R.id.frame,practiceFragment,PracticeFragment.class.getName());
               }else{
                   transaction.show(practiceFragment);
               }
               break;
           case FRAGMENT_INFORM:
               if (informFragment==null){
                   informFragment = InformFragment.getInstance();
                   transaction.add(R.id.frame,informFragment,InformFragment.class.getName());
               }else{
                   transaction.show(informFragment);
               }
               break;
           case FRAGMENT_MYSELF:
               if (myselfFragment==null){
                   myselfFragment = MyselfFragment.getInstance();
                   transaction.add(R.id.frame,myselfFragment,MyselfFragment.class.getName());
               }else{
                   transaction.show(myselfFragment);
               }
               break;
       }
        transaction.commit();
     }

     private void hideFragment(FragmentTransaction ft){
         // 如果不为空，就先隐藏起来
         if(mainFragment!=null){
             ft.hide(mainFragment);
            }
         if(practiceFragment!=null){
             ft.hide(practiceFragment);
         }
         if(informFragment!=null){
             ft.hide(informFragment);
         }
         if(myselfFragment!=null){
             ft.hide(myselfFragment);
         }
     }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - exitTime) < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this,"再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }

}
