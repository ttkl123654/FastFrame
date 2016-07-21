package earlll.com.testdemoall.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import butterknife.ButterKnife;
import earlll.com.testdemoall.R;
import earlll.com.testdemoall.bean.LoginBean;
import earlll.com.testdemoall.aosp.eventbus.bean.MessageEvent;
import earlll.com.testdemoall.ui.base.BaseFragmentActivity;
import earlll.com.testdemoall.ui.fragment.FriendFragment;
import earlll.com.testdemoall.ui.fragment.HomeFragment;
import earlll.com.testdemoall.ui.fragment.bar.DynamicTabBottomBarFragment;
import earlll.com.testdemoall.ui.fragment.dialog.AlertDialogFragment;
import earlll.com.testdemoall.ui.fragment.interfaces.IDialogCallBack;
import earlll.com.testdemoall.ui.fragment.interfaces.IFragmentDataPass;
import earlll.com.testdemoall.ui.fragment.interfaces.ITabBottomBarClickListener;
import earlll.com.testdemoall.ui.fragment.utils.TabBottomBarUtils;

/**
 * Fragment 使用示例:<br>
 *  包含底部动态栏的使用
 */
public class SimpleFragmentActivity extends BaseFragmentActivity implements IFragmentDataPass,ITabBottomBarClickListener,IDialogCallBack {

    private HomeFragment mWeixin;
    private FriendFragment mFriend;

    private DynamicTabBottomBarFragment tabBottomBarFragment;


    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_fragment_simple;
    }

    @Override
    public void initContentView(View view) {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        mWeixin = new HomeFragment();// 设置默认的Fragment
        setTargetFragment(mFriend);
        initFragmentTitleBar();
    }

    private void initFragmentTitleBar() {
        if(titleBarFragment!=null){
            titleBarFragment.setRightVisibility(true);
            titleBarFragment.setRightDrawable(R.mipmap.ic_launcher,0);
        }

        tabBottomBarFragment =  (DynamicTabBottomBarFragment)getFragmentManager().findFragmentById(R.id.fragment_bottombar);
        if(tabBottomBarFragment!=null){
            tabBottomBarFragment.initTabView(TabBottomBarUtils.getTestTabItemList());
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void customClickEvent(View v) {

    }
    /**标题栏   -  左边按钮触发事件*/
    public void onClickTitleLeft(View v) {
        showToast("ibtn_left");
        finish();
    }
    /**标题栏   -  右边按钮触发事件*/
    public void onClickTitleRight(View v) {
        showToast("ibtn_right");
    }
    @Override
    public void onTitleBarClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
    }

    public void onTabBottomBarClick(View view) {
        switch (view.getId()) {
            case DynamicTabBottomBarFragment.BASE_TAB_BOTTOM_ID:
                if(mWeixin==null){
                    mWeixin = new HomeFragment();
                }
                setTargetFragment(mWeixin);
                break;
            case DynamicTabBottomBarFragment.BASE_TAB_BOTTOM_ID+1:
                if(mFriend==null){
                    mFriend = new FriendFragment();
                }
                setTargetFragment(mFriend);
                break;
            case DynamicTabBottomBarFragment.BASE_TAB_BOTTOM_ID+2:
                //showToast("tab_bottom_layout_bbs");
                showExitAlert();
                break;
            case DynamicTabBottomBarFragment.BASE_TAB_BOTTOM_ID+3:
                //showToast("tab_bottom_layout_article");
                showAlert();
                break;
            case DynamicTabBottomBarFragment.BASE_TAB_BOTTOM_ID+4:
                showToast("tab_bottom_layout_more");
                break;
        }
    }

    public AlertDialogFragment mAlertDialog;
    private void showAlert() {
        if (mAlertDialog==null) {
            mAlertDialog = new AlertDialogFragment();
        }
        mAlertDialog.setTitle("提示");
        mAlertDialog.setContent("确认退出应用？");
        mAlertDialog.setBtn("a","b");
        showDialogFragment(mAlertDialog,"AlertDialogFragment");
    }

    private Fragment oldSourceFragment;
    private void setTargetFragment(Fragment targetFragment){
        if (targetFragment!=null&&targetFragment!=oldSourceFragment) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();//开启Fragment事务
            // 先判断是否被add过
            if(oldSourceFragment!=null){
                transaction.hide(oldSourceFragment);
            }
            if (!targetFragment.isAdded()) {
                // 隐藏当前的fragment，add下一个到Activity中
                transaction.add(R.id.fragment_content, targetFragment);
            } else {
                // 隐藏当前的fragment，显示下一个
                transaction.show(targetFragment);
            }
            oldSourceFragment = targetFragment;
            transaction.commit();
        }
    }

    @Subscribe
    public void onEvent(MessageEvent event) {//用于接收EventBus数据传递
        showToast(event.message);
    }
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void receiveFragmentSendData(String tag,Object obj) {
        if(obj instanceof LoginBean){
            LoginBean bean= (LoginBean)obj;
            showToast(bean.getUserName()+bean.getPassWord());
        }else if(obj instanceof String ){
            showToast(obj.toString());
        }
    }

    @Override
    public void btnCallBack(String tag, int type) {
        switch (type){
            case IDialogCallBack.DIALOGCALLBACK_BTN_LEFT:
                if("AlertDialogFragment".equals(tag)){
                    showToast("DIALOGCALLBACK_BTN_LEFT");
                }
                break;
            case IDialogCallBack.DIALOGCALLBACK_BTN_RIGHT:
                if("AlertDialogFragment".equals(tag)){
                    showToast("DIALOGCALLBACK_BTN_RIGHT");
                }
                break;
            default:
                break;
        }
    }
}