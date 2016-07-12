package com.ywq.ydu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ywq.ydu.R;

/**
 * @author yanwenqiang
 * @Date 15-9-15
 * @description 自定义ListView 下拉刷新、加载更多
 * @other 设置分页的大小，默认值：10
 */
public class MeListView  extends FrameLayout {

    /**
     * 刷新监听<br>
     * {@link #downRefresh()} 下拉刷新<br>
     * {@link #upRefresh()} 加载更多
     */
    public interface OnRefreshCallBack {

        /**
         * 下拉刷新
         */
        void downRefresh();

        /**
         * 加载更多
         */
        void upRefresh();

    }

    /**
     * 刷新类型<br>
     * {@link #DOWN} 下拉 <br>
     * {@link #UP} 上拉 <br>
     * {@link #DONE} 刷新完成 <br>
     */
    public enum RefreshType {
        DOWN, UP, DONE
    }

    private PullRefreshLayout mPullRefreshLayout;
    private ListView mListView;
    private View footerView;
    private LinearLayout lay_more;
    private ProgressWheel mProgressWheel;
    private TextView mNetError;
    private ImageView img_default_list;

    private BaseAdapter mBaseAdapter;

    private OnRefreshCallBack mOnRefreshCallBack;
    private RefreshType mRefreshType = RefreshType.DONE;
    private boolean canRefreshMore = true;// 可加载更多
    /**
     * 每页大小，默认10 *
     */
    private int mPageSize = 10;

    public MeListView(Context context) {
        this(context, null);
    }

    public MeListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_listview, this,
                true);

        mPullRefreshLayout = (PullRefreshLayout) findViewById(R.id.mPullRefreshLayout);
        mListView = (ListView) findViewById(R.id.libListView);
        img_default_list = (ImageView) findViewById(R.id.img_default_list);
        footerView = LayoutInflater.from(context).inflate(
                R.layout.layout_footerview, null);
        mListView.addFooterView(footerView);
        lay_more = (LinearLayout) footerView.findViewById(R.id.lay_more);
        mProgressWheel = (ProgressWheel) footerView
                .findViewById(R.id.mProgressWheel);
        mNetError = (TextView) footerView.findViewById(R.id.mNetError);

        mPullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callBack(RefreshType.DOWN);
            }
        });

        mNetError.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                lay_more.setVisibility(View.VISIBLE);
                mNetError.setVisibility(View.GONE);
                callBack(RefreshType.UP);
            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (canRefreshMore
                        && (firstVisibleItem + visibleItemCount) == totalItemCount
                        && visibleItemCount != totalItemCount && isLoading()) {
                    /* 开始上拉刷新 */
                    callBack(RefreshType.UP);
                }
            }
        });
    }

    /**
     * 是否加载中
     */
    private boolean isLoading() {
        return !(mRefreshType == RefreshType.DOWN || mRefreshType == RefreshType.UP);
    }

    private void callBack(RefreshType RefreshType) {
        mRefreshType = RefreshType;
        if (mOnRefreshCallBack == null)
            return;
        switch (RefreshType) {
            case DOWN:
                mOnRefreshCallBack.downRefresh();
                break;
            case UP:
                mPullRefreshLayout.setEnabled(false);
                mOnRefreshCallBack.upRefresh();
                break;
            case DONE:
                mPullRefreshLayout.setEnabled(true);
            default:
                break;
        }
    }

    private void footerViewEnable() {
        footerView.setVisibility(VISIBLE);
        if (canRefreshMore) {
            if (mBaseAdapter != null && mBaseAdapter.getCount() > 0
                    && mBaseAdapter.getCount() % mPageSize == 0) {// 隐藏加载更多
                lay_more.setVisibility(View.VISIBLE);
                mNetError.setVisibility(GONE);
                mProgressWheel.spin();
            } else {
                lay_more.setVisibility(View.GONE);
                mNetError.setVisibility(GONE);
                mNetError.setText("没有更多数据");
            }
        } else {
            if (mBaseAdapter == null || mBaseAdapter.getCount() < 4) {
                footerView.setVisibility(GONE);
            } else {
                lay_more.setVisibility(View.GONE);
                mNetError.setVisibility(VISIBLE);
                mNetError.setText("没有更多数据");
            }
        }
    }

    private void notifyDefaultUI() {
        if (mBaseAdapter == null) {
            img_default_list.setVisibility(VISIBLE);
            footerView.setVisibility(GONE);
        } else {
            if (mBaseAdapter.getCount() > 0) {
                img_default_list.setVisibility(GONE);
                footerView.setVisibility(VISIBLE);
            } else {
                img_default_list.setVisibility(VISIBLE);
                footerView.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置刷新监听<br>
     */
    public void setOnRefreshCallBack(OnRefreshCallBack onRefreshCallBack) {
        mOnRefreshCallBack = onRefreshCallBack;
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        mBaseAdapter = baseAdapter;
        notifyDefaultUI();
        footerViewEnable();
        mListView.setAdapter(mBaseAdapter);
    }

    public BaseAdapter getAdapter() {
        return mBaseAdapter;
    }

    public void notifyDataSetChanged() {
        if (mBaseAdapter != null) {
            notifyDefaultUI();
            footerViewEnable();
            mBaseAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 设置刷新<br>
     *
     * @param refreshing true：刷新状态
     */
    public void setRefresh(boolean refreshing) {
        mPullRefreshLayout.setRefreshing(refreshing);
        callBack(RefreshType.DONE);
    }

    /**
     * 设置加载更多<br>
     * 在{@link #setAdapter(BaseAdapter)} or {@link #notifyDataSetChanged()}前调用
     */
    public void setCanRefreshMore(boolean canRefreshMore) {
        this.canRefreshMore = canRefreshMore;
    }

    /**
     * 设置每页大小， 默认值为10
     */
    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
    }

    /**
     * 设置加载更多出错
     */
    public void setLoadMoreError(boolean isError) {
        setCanRefreshMore(!isError);
        callBack(RefreshType.DONE);
        if (isError) {
            lay_more.setVisibility(View.GONE);
            mNetError.setText(getResources().getString(R.string.net_error));
            mNetError.setVisibility(View.VISIBLE);
        } else {
            lay_more.setVisibility(View.VISIBLE);
            mNetError.setVisibility(View.GONE);
        }
    }

    /**
     * 获取当前刷新类型
     */
    public RefreshType getRefreshType() {
        return mRefreshType;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mListView.setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(
            AdapterView.OnItemLongClickListener onItemLongClickListener) {
        mListView.setOnItemLongClickListener(onItemLongClickListener);
    }

    public void smoothScrollToPosition(int position) {
        mListView.smoothScrollToPosition(position);
    }

}
