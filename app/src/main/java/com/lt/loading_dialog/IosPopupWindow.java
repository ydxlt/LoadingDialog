package com.lt.loading_dialog;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * Created by admin on 2018/5/11.
 */

public class IosPopupWindow extends PopupWindow implements View.OnClickListener {

    private Context mContext;

    public IosPopupWindow(Activity activity) {
        super(activity);
        mContext = activity;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.dialog_share, null);
        setContentView(contentView);
        int screenWidth = activity.getWindowManager().getDefaultDisplay().getWidth();
        //获取popupwindow的高度与宽度
        this.setWidth((int) (screenWidth - 2 * dp2px(mContext,12f)));
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置背景透明度
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置动画
        this.setAnimationStyle(R.style.IosDialog);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 点击外部可取消
        this.setOutsideTouchable(true);
        initView(contentView);
    }

    private void initView(View contentView) {
       contentView.findViewById(R.id.btn_share_moments).setOnClickListener(this);
       contentView.findViewById(R.id.btn_share_qq_space).setOnClickListener(this);
       contentView.findViewById(R.id.btn_share_qq).setOnClickListener(this);
       contentView.findViewById(R.id.btn_share_weixin).setOnClickListener(this);
       contentView.findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_share_qq:
                Toast.makeText(mContext, "分享到qq", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_share_weixin:
                Toast.makeText(mContext, "分享到微信", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_share_qq_space:
                Toast.makeText(mContext, "分享到qq空间", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_share_moments:
                Toast.makeText(mContext, "分享到qq空间", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 显示在屏幕的下方
     */
    public void showAtScreenBottom(View parent){
        this.showAtLocation(parent, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
        popOutShadow();
    }

    /**
     * 让popupwindow以外区域阴影显示
     */
    private void popOutShadow() {
        final Window window = ((Activity) mContext).getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.5f;//设置阴影透明度
        window.setAttributes(lp);
        setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.alpha = 1f;
                window.setAttributes(lp);
            }
        });
    }

    /**
     * dp转 px.
     *
     * @param value the value
     * @return the int
     */
    public static int dp2px(Context context, float value) {
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }
}
