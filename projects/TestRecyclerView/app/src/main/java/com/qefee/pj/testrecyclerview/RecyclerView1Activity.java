package com.qefee.pj.testrecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RecyclerView1Activity extends AppCompatActivity {

    /**
     * 数据集合.
     */
    private List<String> dataList;
    /**
     * RecyclerView的适配器.
     */
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view1);
        initActionBar();
        initFloatingActionButton();

        initDataList();
        initRecyclerView();
    }

    private void initFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // 布局管理器
        adapter = new MyAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); // 分割线
        recyclerView.setItemAnimator(new DefaultItemAnimator()); // 动画效果
        MyItemTouchHelperCallback callback = new MyItemTouchHelperCallback(adapter); // 监听touch事件，拖拽和滑动等
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView); // 绑定到RecyclerView
    }

    private void initDataList() {
        dataList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            dataList.add(String.format("item %d", i));
        }
    }

    class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

        MyAdapter adapter;

        MyItemTouchHelperCallback(MyAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN; // 拖拽方向
            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END; // 滑动方向
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int from = viewHolder.getAdapterPosition();
            int to = target.getAdapterPosition();
            Collections.swap(adapter.dataList, from, to); // 移动item后要交换数据
            adapter.notifyItemMoved(from, to); // 通知适配器数据已经移动
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            switch (direction) {
                case ItemTouchHelper.START:
                case ItemTouchHelper.END:
                    int adapterPosition = viewHolder.getAdapterPosition();
                    adapter.dataList.remove(adapterPosition); // 滑动item后删除数据
                    adapter.notifyItemRemoved(adapterPosition); // 通知适配器数据已经删除
                    break;
                default:
                    break;
            }
        }
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        Context context;
        List<String> dataList;

        MyAdapter(Context context, List<String> dataList) {
            this.context = context;
            this.dataList = dataList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            MyViewHolder vh = new MyViewHolder(view);

            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.text1.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView text1;

            MyViewHolder(View itemView) {
                super(itemView);

                text1 = (TextView) itemView;

                text1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(RecyclerView1Activity.this, String.format("text1 on click. index = %d", getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    }
                });

                text1.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Toast.makeText(RecyclerView1Activity.this, String.format("text1 on long click. index = %d", getAdapterPosition()), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler_view_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.itemAdd:


                if (dataList.size() > 0) {
                    Random random = new Random();
                    int nextInt = random.nextInt(dataList.size());
                    dataList.add(nextInt, String.format("这里是添加项 %d", nextInt));

                    adapter.notifyItemInserted(nextInt);
                    return true;
                } else {
                    int i = 0;
                    dataList.add(i, String.format("这里是添加项 %d", i));

                    adapter.notifyItemInserted(i);
                }

                return true;
            case R.id.itemDelete:

                if (dataList.size() > 0) {
                    Random random1 = new Random();
                    int nextInt1 = random1.nextInt(dataList.size());
                    dataList.remove(nextInt1);

                    adapter.notifyItemRemoved(nextInt1);
                    return true;
                } else {
                    Toast.makeText(this, "没有可删的了", Toast.LENGTH_SHORT).show();
                }
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
