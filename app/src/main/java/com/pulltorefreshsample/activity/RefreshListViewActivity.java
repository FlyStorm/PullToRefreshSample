package com.pulltorefreshsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.it.refreshlistview.RefreshListView;

public class RefreshListViewActivity extends Activity {
    //声明控件
    private RefreshListView mListView;

    private MyListViewAdapter mMyListViewAdapter;

    //定义一个数组
    int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

    /**
     * 下一页的联网路径
     */
    private String mMoreUrl;

    private MyOnRefreshListener mMyOnRefreshListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_list_view);

        //实例化控件
        mListView = (RefreshListView) findViewById(R.id.lv_refresh);

        mMyListViewAdapter = new MyListViewAdapter();

        mMyOnRefreshListener=new MyOnRefreshListener();
        //设置监听下拉刷新(调用自定义的接口)
        mListView.setOnRefreshListener(mMyOnRefreshListener);

        mListView.setAdapter(mMyListViewAdapter);
    }

    class MyListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arr.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder viewHolder;
            if (convertView == null) {
                view = View.inflate(RefreshListViewActivity.this, R.layout.item_listview, null);
                viewHolder=new ViewHolder();
                viewHolder.tv= (TextView) view.findViewById(R.id.tv_list);
                view.setTag(viewHolder);
            }else {
                view=convertView;
                viewHolder= (ViewHolder) view.getTag();
            }
            viewHolder.tv.setText(arr[position]+"");
            return view;
        }
    }

    class ViewHolder {
        private TextView tv;
    }

    class MyOnRefreshListener implements RefreshListView.OnRefreshListener {

        @Override
        public void onPullDownRefresh() {
//            Toast.makeText(RefreshListViewActivity.this, "下拉刷新被回调了", Toast.LENGTH_SHORT).show();

            //联网请求数据
//            getDataFromNet();

            //做一个延时的操作
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //重新加载数据
                    //Log.e("TAG1", "去加载数据，加载数据完成，下拉刷新");
                    mListView.onRefreshFinish(true);
                    Toast.makeText(RefreshListViewActivity.this, "下拉刷新：已经成功加载完成最新消息!", Toast.LENGTH_SHORT).show();
                }
            }, 2000);
        }

        @Override
        public void onLoadMore() {
            /*if (TextUtils.isEmpty(mMoreUrl)) {
                Toast.makeText(RefreshListViewActivity.this, "没有更多数据", Toast.LENGTH_SHORT).show();
                mListView.onRefreshFinish(false);
            } else {
                //联网请求更多数据
//                getMoreDataFromNet();

            }*/
            //做一个延时的操作
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //重新加载数据
                    //Log.e("TAG1", "去加载数据，加载数据完成,上拉加载");
                    Toast.makeText(RefreshListViewActivity.this, "上拉加载更多：已经成功加载完成!", Toast.LENGTH_SHORT).show();

                    //状态重置，隐藏底部
                    mListView.onRefreshFinish(true);
                }
            }, 2000);
        }
    }
}
