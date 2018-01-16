package io.github.activehouse;


import android.support.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by Ryan on 1/16/2018.
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }
    @Test
    public void testLogin() throws Exception{
        String str = "Hello";
        assertTrue(str.isEmpty());
    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }


}