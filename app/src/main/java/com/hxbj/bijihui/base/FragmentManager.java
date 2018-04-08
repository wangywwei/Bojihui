package com.hxbj.bijihui.base;

import android.support.v4.app.FragmentTransaction;

import com.hxbj.bijihui.global.MyApp;

public class FragmentManager {
    //记录上一个Fragment
    private static BaseFragment mLastFragment;

    // add, hide,show        replace (容器ID,Fragment实例对象, Fragment的类名(Tag))

    /**
     * Fragment之间的切换
     *
     * @param fragmentClass 要切换的Fragment的类
     * @param containerId   容器ID
     * @param isUseAdd      true 代表使用add(), hide(),show()切换Fragment false代表使用replace方法切换Fragment
     * @param isBack        是否加入回退栈
     * @return
     */
    public static BaseFragment changeFragment(Class<? extends BaseFragment> fragmentClass, int containerId, boolean isUseAdd, boolean isBack) {
        android.support.v4.app.FragmentManager manager = MyApp.mContext.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        String fragmentName = fragmentClass.getSimpleName();
        BaseFragment currentFragment = (BaseFragment) manager.findFragmentByTag(fragmentName);
        if (currentFragment == null) {
            try {
                currentFragment = fragmentClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (isUseAdd) {
            transaction.add(containerId, currentFragment, fragmentName);
            if (mLastFragment != null && isBack) {
                transaction.hide(mLastFragment).show(currentFragment);
            }
        } else {
            transaction.replace(containerId, currentFragment, fragmentName);
        }

        if (isBack)
            transaction.addToBackStack(fragmentName);
        mLastFragment = currentFragment;
        transaction.commit();
        return mLastFragment;
    }


}
